  public void testSomeMethod() throws IOException {
    Schema schema = new Schema.Parser().parse(SCHEMA);

    Symbol root = Symbol.root(new ResolvingGrammarGenerator()
        .generate(schema, schema, new HashMap<ValidatingGrammarGenerator.LitS, Symbol>()));
    validateNonNull(root, new HashSet<Symbol>());
  }
idValue(
        new Field("f", unionWithoutNull, null, null), new Object()));
    Assert.assertFalse(RecordBuilderBase.isValidValue(
        new Field("f", unionWithoutNull, null, null), null));

    // Verify that null values are valid for a union with a null type:
    Schema unionWithNull = Schema.createUnion(Arrays.asList(new Schema[] {
        Schema.create(Type.STRING), Schema.create(Type.NULL)
    }));

    Assert.assertTrue(RecordBuilderBase.isValidValue(
        new Field("f", unionWithNull, null, null), new Object()));
    Assert.assertTrue(RecordBuilderBase.isValidValue(
        new Field("f", unionWithNull, null, null), null));
  }
