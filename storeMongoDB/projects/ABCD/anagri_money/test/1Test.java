    public void shouldConvertPoundToDollar() {
        Money result = new Money(1, Money.Currency.GBP);
        assertEquals(new Money(1.26, Money.Currency.USD), result.convertToBase());

    }
