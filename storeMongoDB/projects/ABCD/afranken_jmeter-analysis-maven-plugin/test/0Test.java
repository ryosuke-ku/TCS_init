    public void testCheckSuccessJmeter() throws Exception {
        check("JMeterResultParserTest-success.xml", 1);
    }
etClass().getResource("test.txt").getFile());

    String actual = out.toString();

    assertThat("output does not match: ",
            normalizeFileContents(actual),
            is(equalTo(normalizeFileContents(expected))));

  }
