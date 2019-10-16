    private String readToEnd(FileInputStream inputStream) {
        try {
            System.out.println("DECLARED INPUT STREAM LENGTH: " + inputStream.available());
            int ch;
            StringBuilder stringBuilder = new StringBuilder();
            int index = 0;
            while (true) {
                ch = inputStream.read();
                System.out.println("READ CHARACTER: " + index + " " + ch);
                if (ch == -1) {
                    break;
                }
                stringBuilder.append((char)ch);
                index++;
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            return null;
        }
    }
