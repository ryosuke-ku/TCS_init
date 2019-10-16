  public static String substitute(String input, TokenHandler tokenHandler) {
    if (tokenHandler == null) {
      throw new IllegalArgumentException("The tokenHandler input is not allowed to be null");
    }

    if (input == null) {
      return null;
    }


    StringBuilder result = new StringBuilder();
    int length = input.length();
    int tokenStart = 0;
    ParsingState state = ParsingState.Text;
    for (int i = 0; i < length; i++) {
      char currentChar = input.charAt(i);

      if (state == ParsingState.Text) {
        switch (currentChar) {
          case ':':
            if (i < length - 1 && Character.isJavaIdentifierPart(input.charAt(i + 1))) {
              state = ParsingState.Token;
              tokenStart = i;
            } else {
              result.append(currentChar);
            }
            break;
          case '\\':
            if (i < length - 1 && input.charAt(i + 1) == ':') {
              // Swallow the \, append the :, and advance the token counter so we don't see it next time through
              result.append(':');
              i++;
            } else {
              result.append(currentChar);
            }
            break;
          default:
            result.append(currentChar);
            break;
        }
      } else if (state == ParsingState.Token) {
        if (!Character.isJavaIdentifierPart(currentChar)) {
          result.append(tokenHandler.tokenValue(input.substring(tokenStart + 1, i)));
          state = ParsingState.Text;
          i--; // Push the character back on the stack, effectively
        }
      }
    }

    // If we end with the parsing state still on "token", then we need to add it in
    if (state == ParsingState.Token) {
      result.append(tokenHandler.tokenValue(input.substring(tokenStart + 1)));
    }

    return result.toString();
  }
