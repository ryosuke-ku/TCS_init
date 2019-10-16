    public void crud() throws Exception {
        Long random = getRandom();
        Long updateRandom = getRandom();
        Order order = new Order();
        Customer customer = new Customer();

        // Gets all the objects from the database
        int firstFindAll = findAll();

        // Creates an object and persists it into the database
        customer = getMockCustomerValues(customer, random);
        order = getMockOrderValues(order, random);
        order.setCustomer(customer);
        tx.begin();
        em.persist(order);
        em.persist(customer);
        tx.commit();
        Long id = order.getId();

        // Check that the object has been created with the right values
        order = em.find(Order.class, id);
        assertNotNull("Object should exist", order);
        checkMockOrderValues(order, random);

        // Updates the object with new values
        order = getMockOrderValues(order, updateRandom);
        tx.begin();
        em.merge(order);
        tx.commit();

        // Checks the object has been updated with the new values
        order = em.find(Order.class, id);
        assertNotNull("Object should exist", order);
        checkMockOrderValues(order, updateRandom);

        // Gets all the objects from the database...
        int secondFindAll = findAll();

        // ...checks there is an extra object in the database
        if (firstFindAll + 1 != secondFindAll) fail("The collection size should have increased by 1");

        // Deletes the object from the database
        tx.begin();
        em.remove(order);
        tx.commit();

        // Checks the object has been deleted
        order = em.find(Order.class, id);
        assertNull("Object should not exist", order);

        // Gets all the objects from the database...
        int thirdFindAll = findAll();

        // ... checks there is the initial number of objects in the database
        if (firstFindAll != thirdFindAll) fail("The collection size should have be the same as original");
    }
