	public int getMax() {
		cache = new int[7];
		db.openDatabase();
		int max = 0;
		for (int i = 0; i < 7; i++) {
			int drunkOnDay = db.getDrunkOnNDaysAgo(i);
			cache[i] = drunkOnDay;
			if (drunkOnDay > max)
				max = drunkOnDay;
		}
		db.closeDatabase();
		return max;
	}
git = 2;
		else if (firstDigit > 1 && firstDigit < 5)
			resultFirstDigit = 5;
		else
			resultFirstDigit = 1;

		int result = (int) (resultFirstDigit * Math.pow(10,places));

		return result;
	}
