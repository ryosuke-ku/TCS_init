        public TristateState getState() {
            return state;
        }
         // TODO: Log a message?
          // But how to find the name of the offending GUI element in the case of a TestBean?
          super.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        } else {
          final String style = languageProperties.getProperty(language);
          if (style == null) {
              super.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
          } else {
              super.setSyntaxEditingStyle(style);
          }
        }
    }
