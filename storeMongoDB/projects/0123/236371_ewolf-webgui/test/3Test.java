	public void itShouldSerializeASignedObjectWithoutPrivKey() throws Exception {
		X x = new X("abcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		KeyPairGenerator gen = KeyPairGenerator.getInstance("DSA");
		KeyPair keyPair = gen.generateKeyPair();
		
		x.setPrvSigKey(keyPair.getPrivate());
		x.setPubSigKey(keyPair.getPublic());
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oout = new ObjectOutputStream(bout);
		
		oout.writeObject(x);
		oout.close();
		bout.close();
		
		ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
		ObjectInputStream oin = new ObjectInputStream(bin);
		
		X otherX = (X)oin.readObject();
		
		Assert.assertEquals("x was not encoded properly", x, otherX);
		Assert.assertNull("private key was encoded !!", otherX.getPrvSigKey());
		Assert.assertNotNull("could not encode x due to missing private key", x.getPrvSigKey());
		Assert.assertNotNull("could not verify other x due to missing pub key", otherX.getPubSigKey());
		Assert.assertNotNull("other x must have a signature", otherX.getSignature());
		Assert.assertArrayEquals("both objects signatures' must be the same", x.getSignature(), otherX.getSignature());
		
		
		ByteArrayOutputStream bout2 = new ByteArrayOutputStream();
		ObjectOutputStream oout2 = new ObjectOutputStream(bout2);
		
		oout2.writeObject(otherX);
		oout2.close();
		bout2.close();
		
		ByteArrayInputStream bin2 = new ByteArrayInputStream(bout.toByteArray());
		ObjectInputStream oin2 = new ObjectInputStream(bin2);
		
		X otherX2 = (X)oin2.readObject();
		
		Assert.assertEquals("x was not encoded properly", otherX, otherX2);
		Assert.assertNull("private key was encoded !!", otherX2.getPrvSigKey());
		Assert.assertArrayEquals("both objects signatures' must be the same", otherX.getSignature(), otherX2.getSignature());
		
		//System.out.println(x.getSignature().length);
		//System.out.println(Base64.encodeBase64String(x.getSignature()));
		
	}
