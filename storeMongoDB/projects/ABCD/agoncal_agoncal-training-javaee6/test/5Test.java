    public void shouldCreateACD() throws Exception {
        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Counts all the cds in the database
        int nbCDs = itemEJB.findAllCDs().size();

        // Creates a cd
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the cd to the database
        cd = itemEJB.createCD(cd);
        assertNotNull("ID should not be null", cd.getId());

        // Finds the cd by primary key
        cd = itemEJB.findCD(cd.getId());
        assertEquals(cd.getTitle(), "St Pepper");

        // Checks that there is an extra cd in the database
        assertEquals("Should have an extra cd", itemEJB.findAllCDs().size(), nbCDs + 1);

        // Removes the cd
        itemEJB.removeCD(cd);

        // Checks that the extra cd has been removed
        assertEquals("Should have got rid of the extra cd", itemEJB.findAllCDs().size(), nbCDs);
    }
