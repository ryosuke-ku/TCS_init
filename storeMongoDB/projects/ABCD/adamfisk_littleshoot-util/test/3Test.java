    public void testIsSome
            ()
        {
        final Optional<Object> none = new NoneImpl<Object> ();

        assertFalse (optionalUtils ().isSome (none));

        final Optional<Object> some = new SomeImpl<Object> (new Object ());

        assertTrue (optionalUtils ().isSome (some));
        }
