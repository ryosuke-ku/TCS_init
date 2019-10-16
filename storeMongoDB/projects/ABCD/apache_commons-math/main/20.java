    public Field<SparseGradient> getField() {
        return new Field<SparseGradient>() {

            /** {@inheritDoc} */
            @Override
            public SparseGradient getZero() {
                return createConstant(0);
            }

            /** {@inheritDoc} */
            @Override
            public SparseGradient getOne() {
                return createConstant(1);
            }

            /** {@inheritDoc} */
            @Override
            public Class<? extends FieldElement<SparseGradient>> getRuntimeClass() {
                return SparseGradient.class;
            }

        };
    }
