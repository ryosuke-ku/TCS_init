	protected void readMessage(InputStream in, int msgLength) throws IOException {
		super.readMessage(in, msgLength);
		DataInputStream dis=new DataInputStream(in);
		int pos=2;
		while(pos < msgLength){
			topics.add(dis.readUTF());
			pos+=FormatUtil.toMQttString(topics.get(topics.size()-1)).length;
		}
	}
