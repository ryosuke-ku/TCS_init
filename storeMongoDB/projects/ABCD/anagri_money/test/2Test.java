    public void shouldNotAddDifferentCurrency() throws Exception, BadCurrencyException {
        Money money1 = new Money(100, Money.Currency.USD);
        Money money2 = new Money(200, Money.Currency.EUR);
        Money total = money1.add(money2);
    }
