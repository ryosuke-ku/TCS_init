    public Money add(Money money2) throws Exception, BadCurrencyException {
      if(currency!=money2.currency)  {
          throw new BadCurrencyException();
      }
        return new Money(amount+money2.amount, currency);
    }
