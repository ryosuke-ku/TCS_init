    public void testCompetencyRelationships() {
        Competency competency1 = new Competency("Competency 1", "Competency 1 description", group, level1, "Competency 1 resources");
        competency1.save();

        Competency competency2 = new Competency("Competency 2", "Competency 2 description", group, level2, "Competency 2 resources");
        competency2.prerequisites.add(competency1);
        competency2.save();

        Competency competency3 = new Competency("Competency 3", "Competency 3 description", group, level3, "Competency 3 resources");
        competency3.prerequisites.add(competency2);
        competency3.save();

        Competency found = Competency.findByTitle("Competency 2");
        Assert.assertNotNull(found);
        Assert.assertEquals("Competency 2",found.title);
        Assert.assertNotNull(competency2.prerequisites);
        Assert.assertEquals(1, competency2.prerequisites.size());
        Assert.assertEquals("Competency 1", competency2.prerequisites.iterator().next().title);

        found = Competency.findByTitle("Competency 3");
        Assert.assertNotNull(found);
        Assert.assertEquals("Competency 3",found.title);
        Assert.assertNotNull(competency3.prerequisites);
        Assert.assertEquals(1, competency3.prerequisites.size());
        Assert.assertEquals("Competency 2", competency3.prerequisites.iterator().next().title);
    }
