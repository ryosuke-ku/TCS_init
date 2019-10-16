	public void testConvertSlideByCategory() throws Exception {
		Map<CategoryTO, List<SlideShowTO>> slideShowsByCatTO = ModelTOConverter.convertSlideShowsByCategory(this.slideShows);
		assertEquals(2, slideShowsByCatTO.keySet().size());
		for(CategoryTO category : slideShowsByCatTO.keySet()) {
			List<SlideShowTO> slideShowsInCat = slideShowsByCatTO.get(category);
			if(category.getName().equals("Enterprise Java")) {
				assertEquals(1, slideShowsInCat.size());
				SlideShowTO slideShowTo = slideShowsInCat.get(0);
				assertEquals("Introduction to EJB", slideShowTo.getTitle());
			} else if(category.getName().equals("Java")) {
				assertEquals(1, slideShowsInCat.size());
				SlideShowTO slideShowTo = slideShowsInCat.get(0);
				assertEquals("Effective Java", slideShowTo.getTitle());
			} else {
				fail("Unknown category");
			}
		}
	}
