	public void testGetNaturalNumber() {
		Log.log("Testing bytes to number conversion ...");
		byte[] data = null;
		
		// test null
		boolean inputChecking;
		inputChecking = false;
		try { 
			Convert.toInt(data); 
		}
		catch (IllegalArgumentException e){ inputChecking = true;}
		finally { assertTrue(inputChecking);}
		
		// test garbage data
		inputChecking = false;
		data = "2be?".getBytes();
		try { 
			Convert.toInt(data); 
		}
		catch (IllegalArgumentException e){ inputChecking = true;}
		finally { assertTrue(inputChecking);}
		
		// test big data
		// this is bigger than an bit 
		inputChecking = false;
		data = "1234567890123456".getBytes();
		try { Convert.toInt(data); }
		catch (IllegalArgumentException e){ inputChecking = true; }
		finally { assertTrue(inputChecking);}
		
		{
		// test 0
		data = "0".getBytes();
		int value = Convert.toInt(data);
		assertEquals(0, value);
		// test -0
		data = "-0".getBytes();
		// test +0
		data = "+0".getBytes();
		assertEquals(0, Convert.toInt(data));
		// test -00000
		data = "-0000".getBytes();
		assertEquals(0, Convert.toInt(data));
		// test 00000
		data = "0000".getBytes();
		// test +00000
		data = "+0000".getBytes();
		assertEquals(0, Convert.toInt(data));
		// test 00001
		data = "00001".getBytes();
		assertEquals(1, Convert.toInt(data));
		// test 0000100
		data = "0000100".getBytes();
		assertEquals(100, Convert.toInt(data));
		// test 00001
		data = "+00001".getBytes();
		assertEquals(1, Convert.toInt(data));
		// test +0000100
		data = "+0000100".getBytes();
		assertEquals(100, Convert.toInt(data));
		// test 00001
		data = "-00001".getBytes();
		assertEquals(-1, Convert.toInt(data));
		// test +0000100
		data = "-0000100".getBytes();
		assertEquals(-100, Convert.toInt(data));
		// test 1
		data = "1".getBytes();
		assertEquals(1, Convert.toInt(data));
		// test -1
		data = "-1".getBytes();
		assertEquals(-1, Convert.toInt(data));
		// test +1
		data = "+1".getBytes();
		assertEquals(1, Convert.toInt(data));
		
		// test $+1
		data = "$+1".getBytes();
		assertEquals(1, Convert.toInt(data, 1, data.length-1));
		
		// test $-1
		data = "$-1".getBytes();
		assertEquals(-1, Convert.toInt(data, 1, data.length-1));
		
		// test $+1GARBAGEDATA
		data = "$+1GARBAGEDATA".getBytes();
		assertEquals(1, Convert.toInt(data, 1, 2));
		
		// test $-1GARAGEDATA
		data = "$-1GARAGEDATA".getBytes();
		assertEquals(-1, Convert.toInt(data, 1, 2));
		
		// do a sensible range
		int java ;
		for(int i=-50000; i<50000; i++) {
			data = Convert.toBytes(i);
			java = Integer.parseInt(new String(data), 10);
			assertEquals (java, Convert.toInt(data));
		}
		
		// now lets go to the limit
		//
		data = Integer.toString(Integer.MAX_VALUE).getBytes();
		assertEquals(Integer.MAX_VALUE, Convert.toInt(data, 0, data.length));

		data = Integer.toString(Integer.MIN_VALUE).getBytes();
		assertEquals(Integer.MIN_VALUE, Convert.toInt(data, 0, data.length));
		
		}
		
		// now lets do the longs

		{
		// test big data
		// this is bigger than an bit  but fine for a long
		inputChecking = false;
		data = "1234567890123456".getBytes();
		assertEquals (1234567890123456L, Convert.toLong(data));
		
		// test 0
		data = "0".getBytes();
		long value = Convert.toLong(data);
		assertEquals(0, value);
		// test -0
		data = "-0".getBytes();
		// test +0
		data = "+0".getBytes();
		assertEquals(0, Convert.toLong(data));
		// test -00000
		data = "-0000".getBytes();
		assertEquals(0, Convert.toLong(data));
		// test 00000
		data = "0000".getBytes();
		// test +00000
		data = "+0000".getBytes();
		assertEquals(0, Convert.toLong(data));
		// test 00001
		data = "00001".getBytes();
		assertEquals(1, Convert.toLong(data));
		// test 0000100
		data = "0000100".getBytes();
		assertEquals(100, Convert.toLong(data));
		// test 00001
		data = "+00001".getBytes();
		assertEquals(1, Convert.toLong(data));
		// test +0000100
		data = "+0000100".getBytes();
		assertEquals(100, Convert.toLong(data));
		// test 00001
		data = "-00001".getBytes();
		assertEquals(-1, Convert.toLong(data));
		// test +0000100
		data = "-0000100".getBytes();
		assertEquals(-100, Convert.toLong(data));
		// test 1
		data = "1".getBytes();
		assertEquals(1, Convert.toLong(data));
		// test -1
		data = "-1".getBytes();
		assertEquals(-1, Convert.toLong(data));
		// test +1
		data = "+1".getBytes();
		assertEquals(1, Convert.toLong(data));
		
		// test $+1
		data = "$+1".getBytes();
		assertEquals(1, Convert.toLong(data, 1, data.length-1));
		
		// test $-1
		data = "$-1".getBytes();
		assertEquals(-1, Convert.toLong(data, 1, data.length-1));
		
		// test $+1GARBAGEDATA
		data = "$+1GARBAGEDATA".getBytes();
		assertEquals(1, Convert.toLong(data, 1, 2));
		
		// test $-1GARAGEDATA
		data = "$-1GARAGEDATA".getBytes();
		assertEquals(-1, Convert.toLong(data, 1, 2));
		
		// now lets go to the limit
		//
		data = Long.toString(Long.MAX_VALUE).getBytes();
		assertEquals(Long.MAX_VALUE, Convert.toLong(data, 0, data.length));

		data = Long.toString(Long.MIN_VALUE).getBytes();
		assertEquals(Long.MIN_VALUE, Convert.toLong(data, 0, data.length));
		
		}  // just for scoping  ..
	}
