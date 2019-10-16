    static String stripLeadingHyphens(String str)
    {
        if (str == null)
        {
            return null;
        }
        if (str.startsWith("--"))
        {
            return str.substring(2, str.length());
        }
        else if (str.startsWith("-"))
        {
            return str.substring(1, str.length());
        }

        return str;
    }
// details about it
            if (!isValueCode(ch))
            {
                if (opt != ' ')
                {
                    final Option option = Option.builder(String.valueOf(opt))
                        .hasArg(type != null)
                        .required(required)
                        .type(type)
                        .build();
                    
                    // we have a previous one to deal with
                    options.addOption(option);
                    required = false;
                    type = null;
                    opt = ' ';
                }

                opt = ch;
            }
            else if (ch == '!')
            {
                required = true;
            }
            else
            {
                type = (Class<?>) getValueClass(ch);
            }
        }

        if (opt != ' ')
        {
            final Option option = Option.builder(String.valueOf(opt))
                .hasArg(type != null)
                .required(required)
                .type(type)
                .build();
            
            // we have a final one to deal with
            options.addOption(option);
        }

        return options;
    }
