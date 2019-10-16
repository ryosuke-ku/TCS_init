	public static Map<CategoryTO, List<SlideShowTO>> convertSlideShowsByCategory(Map<Category,List<SlideShow>> slideShowsByCategory) {
		//SlideShowPrintUtils.printSlideShowByCategory(slideShowsByCategory);
		Map<CategoryTO, List<SlideShowTO>> retVal = new TreeMap<CategoryTO, List<SlideShowTO>>();
		Set<Category> categories = slideShowsByCategory.keySet();
		for(Category category : categories) {
			CategoryTO categoryTo = new CategoryTO(category);
			List<SlideShow> slideShows = slideShowsByCategory.get(category);
			//Using TreeSet to get sorting for free
			Set<SlideShowTO> slideShowTOs = new TreeSet<SlideShowTO>();
			for(SlideShow slideShow : slideShows) {
				SlideShowTO slideShowTO = new SlideShowTO(slideShow);
				slideShowTOs.add(slideShowTO);
			}
			retVal.put(categoryTo, new ArrayList<SlideShowTO>(slideShowTOs));
		}
		//SlideShowPrintUtils.printSlideShowTOByCategory(retVal);
		return retVal;
	}
