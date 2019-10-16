    public Money convertToBase(){
        return new Money(amount*currency.conversionFactor, Currency.USD);
    }
