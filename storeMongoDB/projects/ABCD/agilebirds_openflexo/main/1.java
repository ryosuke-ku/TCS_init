	public static String extractSourceFromEmbeddedTag(String htmlCode) {
		if (htmlCode == null || htmlCode.length() < 7) {
			return null;
		}
		if (!htmlCode.substring(0, 7).toLowerCase().startsWith("<html>")) {
			htmlCode = "<html>" + htmlCode + "</html>";
		}
		// 1. Let's try with XML parsers (it works most of the time and it is a lot more reliable as a parser)
		final String embeddedVideoCode = htmlCode;
		Reader reader = new StringReader(embeddedVideoCode.replaceAll("&", "&amp;"));
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reader);
			Iterator objectIterator = document.getDescendants(new ElementFilter("object"));
			while (objectIterator.hasNext()) {
				Element e = (Element) objectIterator.next();
				for (Object param : e.getChildren("param")) {
					String paramName = ((Element) param).getAttributeValue("name");
					if (paramName != null && paramName.equals("movie")) {
						return ((Element) param).getAttributeValue("value");
					}
				}
			}
			Iterator embedIterator = document.getDescendants(new ElementFilter("embed"));
			while (embedIterator.hasNext()) {
				Element e = (Element) embedIterator.next();
				if (e.getAttributeValue("src") != null) {
					return e.getAttributeValue("src");
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 2. Ok XML parsers failed, let's see HTML ones
		final StringBuilder sb = new StringBuilder();
		HTMLEditorKit.ParserCallback callback = new HTMLEditorKit.ParserCallback() {
			@Override
			public void handleStartTag(Tag t, MutableAttributeSet a, int pos) {
				if (sb.length() > 0) {
					return;
				}
				if (t == Tag.OBJECT) {
					int indexOfParamMovie = embeddedVideoCode.indexOf("<param name=\"movie\"", pos);
					if (indexOfParamMovie > -1) {
						int indexOfMovieValue = embeddedVideoCode.indexOf("value=\"", indexOfParamMovie);
						if (indexOfMovieValue > -1) {
							int endIndexOfMovieValue = embeddedVideoCode.indexOf('"', indexOfMovieValue + 7);
							if (endIndexOfMovieValue > -1) {
								sb.append(embeddedVideoCode.substring(indexOfMovieValue + 7, endIndexOfMovieValue));
							}
						}
					}
				}
			}
		};
		reader = new StringReader(embeddedVideoCode);
		try {
			new ParserDelegator().parse(reader, callback, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (sb.length() > 0) {
			return sb.toString();
		}
		// 3. Last resort: Manual parsing
		int indexOfEmbed = embeddedVideoCode.indexOf("<embed");
		if (indexOfEmbed > -1) {
			int indexOfSrc = embeddedVideoCode.indexOf("src=\"", indexOfEmbed);
			if (indexOfSrc > -1) {
				int endIndexOfSrc = embeddedVideoCode.indexOf('"', indexOfSrc + 5);
				if (endIndexOfSrc > -1) {
					return embeddedVideoCode.substring(indexOfSrc + 5, endIndexOfSrc);
				}
			}
		}
		return null;
	}
