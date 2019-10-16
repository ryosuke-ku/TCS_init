	public void testExtractSourceFromEmbedded() {
		String src = "http://www.youtube.com/v/NmmELsWBscM&hl=en&fs=1";
		String html = "<object width=\"425\" height=\"344\"><param name=\"movie\" value=\""
				+ src
				+ "\"></param><param name=\"allowFullScreen\" value=\"true\"></param><param name=\"allowscriptaccess\" value=\"always\"></param><embed src=\""
				+ src
				+ "\" type=\"application/x-shockwave-flash\" allowscriptaccess=\"always\" allowfullscreen=\"true\" width=\"425\" height=\"344\"></embed></object>";
		assertEquals(src, HTMLUtils.extractSourceFromEmbeddedTag(html));
	}
