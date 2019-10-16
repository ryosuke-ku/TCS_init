    public <T> T someObject
            (final Optional<T> optional) throws IllegalArgumentException
        {
        final ObjectRef objectRef = new ObjectRefImpl ();

        final OptionalVisitor<T,T> visitor = new OptionalVisitor<T,T> ()
            {
            public T visitNone
                    (final None<T> none)
                {
                throw new IllegalArgumentException ("Must be Some");
                }

            public T visitSome
                    (final Some<T> some)
                {
                return some.object ();
                }
            };

        return optional.accept (visitor);
        }
