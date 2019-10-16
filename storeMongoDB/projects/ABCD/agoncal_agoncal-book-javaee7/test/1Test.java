  public void shouldUnmarshallACreditCard() throws JAXBException {
    StringReader reader = new StringReader(creditCardXML);
    JAXBContext context = JAXBContext.newInstance(CreditCard11.class);
    Unmarshaller u = context.createUnmarshaller();
    CreditCard11 creditCard = (CreditCard11) u.unmarshal(reader);

    assertEquals("12345678", creditCard.getNumber());
    assertEquals("10/14", creditCard.getExpiryDate());
    assertEquals((Object) 566, creditCard.getControlNumber());
    assertEquals("Visa", creditCard.getType());
  }
