    public static String newUrl(final String baseUrl, 
        final Map<String, String> paramMap)
        {
        final StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);
        sb.append(getUrlParameters(paramMap, true));
        return sb.toString();
        }
uilder sb = new StringBuilder();
        if (hoursRemaining > 0.0)
            {
            sb.append((int)hoursRemaining);
            sb.append(" hrs, ");
            }
        if (minutesRemaining > 0.0)
            {
            sb.append((int)minutesRemaining);
            sb.append(" mins, ");
            }
        sb.append((int)secondsRemaining);
        sb.append(" secs");
        return sb.toString();
        }
