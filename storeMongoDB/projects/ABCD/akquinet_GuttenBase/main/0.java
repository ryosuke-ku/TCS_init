  public List<String> parse()
  {
    final List<String> result = new ArrayList<String>();
    final StringBuilder builder = new StringBuilder();

    while (hasNext())
    {
      final SQLTokenType nextToken = nextToken();

      switch (nextToken)
      {
      case END_OF_LINE:
      case WHITESPACE:
        read();
        builder.append(' ');

        while (nextToken() == SQLTokenType.WHITESPACE)
        {
          read();
        }

        break;

      case END_OF_STATEMENT:
        read();
        result.add(builder.toString().trim());
        builder.setLength(0);
        break;

      case SINGLE_LINE_COMMENT_START:
        seekToken(SQLTokenType.END_OF_LINE);
        break;

      case MULTI_LINE_COMMENT_START:
        seekToken(SQLTokenType.MULTI_LINE_COMMENT_END);
        break;

      case MULTI_LINE_COMMENT_END:
        read();
        read();
        break;

      case ESCAPED_STRING_DELIMITER:
        builder.append((char) read());
        builder.append((char) read());
        break;

      case EOF:
        read();
        break;

      case OTHER:
        builder.append((char) read());
        break;

      case STRING_DELIMITER_START:
        builder.append((char) read());
        _withinString = true;
        break;

      case STRING_DELIMITER_END:
        builder.append((char) read());
        _withinString = false;
        break;

      default:
        throw new IllegalStateException("unhandled case: " + nextToken);
      }
    }

    return result;
  }
