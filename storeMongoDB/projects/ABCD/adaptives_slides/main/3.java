	public void initDb() {
        Category ejc = new Category("Enterprise Java");
        ejc.placement = 1;
        em.persist(ejc);
        Category jc = new Category("Java");
        jc.placement = 2;
        em.persist(jc);

        SlideShow slideShow1 = new SlideShow();
        slideShow1.title = "Introduction to EJB";
        slideShow1.createdBy = "Parag";
        slideShow1.category = ejc;
        Slide slide11 = new Slide();
        slide11.slideShow = slideShow1;
        slide11.title = "Agenda";
        slide11.contents = "Contents for the agenda";
        slideShow1.slides.add(slide11);
        Slide slide12 = new Slide();
        slide12.slideShow = slideShow1;
        slide12.title = "Summary";
        slide12.contents = "Contents for summary";
        slideShow1.slides.add(slide12);
        em.persist(slideShow1);

        SlideShow slideShow2 = new SlideShow();
        slideShow2.title = "Effective Java";
        slideShow2.createdBy = "Kalpak";
        slideShow2.category = jc;
        Slide slide21 = new Slide();
        slide21.slideShow = slideShow2;
        slide21.title = "Agenda";
        slide21.contents = "Agenda for the Effective Java session";
        slideShow2.slides.add(slide21);
        Slide slide22 = new Slide();
        slide22.slideShow = slideShow2;
        slide22.title = "Effective Equals and HashCode";
        slide22.contents = "Effective Equals and HashCode contents";
        slideShow2.slides.add(slide22);
        em.persist(slideShow2);
        em.flush();
	}
