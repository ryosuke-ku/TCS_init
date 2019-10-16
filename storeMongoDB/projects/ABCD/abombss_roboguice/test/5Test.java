    public void testInheritanceMethodCalling() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ClassOne one = new ClassOne();

        Method baseMethod = ClassTwo.class.getDeclaredMethod("bar", null);

        baseMethod.invoke(one, null);
    }
