    public void compareToShouldReturnZero() {
        int result = new Money(1, Money.Currency.USD).compareTo(new Money(1, Money.Currency.USD));
        assertEquals(0, result);
    }
new Money(200, Money.Currency.EUR));
        moneys.add(new Money(150, Money.Currency.GBP));
        Money leastExpensive = new Expense(moneys).getLeastExpensive();
        assertEquals(new Money(100, Money.Currency.USD), leastExpensive);

    }
