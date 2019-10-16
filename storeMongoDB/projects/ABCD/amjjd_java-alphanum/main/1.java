	public int compare(String str1, String str2)
	{
		int[] pos = {0, 0};

		if(str1.length() == 0)
			return str2.length() == 0 ? 0 : -1;
		else if(str2.length() == 0)
			return 1;

		while(pos[0] < str1.length() && pos[1] < str2.length())
		{
			int ch1 = str1.codePointAt(pos[0]);
			int ch2 = str2.codePointAt(pos[1]);

			int result = 0;

			if(isDigit(ch1))
				result = isDigit(ch2) ? compareNumbers(str1, str2, pos) : -1;
			else
				result = isDigit(ch2) ? 1 : compareNonNumeric(str1, str2, pos);

			if(result != 0)
				return result;
		}

		return str1.length() - str2.length();
	}
