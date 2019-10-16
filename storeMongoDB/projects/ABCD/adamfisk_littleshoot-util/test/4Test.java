    public void testSomeObjectWithNone
            ()
        {
        final Optional<Object> none = new NoneImpl<Object> ();

        boolean exceptionThrown = false;

        try
            {
            optionalUtils ().someObject (none);
            }
        catch (final IllegalArgumentException illegalArgumentException)
            {
            // We expect that when 'someObject' is called with a None object,
            // an illegal argument exception is thrown.
            exceptionThrown = true;
            }

        assertTrue (exceptionThrown);
        }
