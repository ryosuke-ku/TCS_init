    private int offset(String word, String[] words) {
        for (int i = 0; i < words.length; i++) {
            int index = word.indexOf(words[i]);
            if (index != -1) {
                return index + 1 + words[i].length();
            }
        }
        return -1;
    }
