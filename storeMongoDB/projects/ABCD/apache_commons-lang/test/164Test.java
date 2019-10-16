     public void testToPrimitive_long() {
         final Long[] b = null;
         assertEquals(null, ArrayUtils.toPrimitive(b));
         
         assertSame(ArrayUtils.EMPTY_LONG_ARRAY, 
            ArrayUtils.toPrimitive(new Long[0]));
         
         assertTrue(Arrays.equals(
             new long[] {Long.MIN_VALUE, Long.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Long[] {Long.valueOf(Long.MIN_VALUE), 
                 Long.valueOf(Long.MAX_VALUE), Long.valueOf(9999999)}))
         );

         try {
             ArrayUtils.toPrimitive(new Long[] {Long.valueOf(Long.MIN_VALUE), null});
             fail();
         } catch (final NullPointerException ex) {}
     }
