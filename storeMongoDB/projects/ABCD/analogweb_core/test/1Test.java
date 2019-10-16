    public void testInvoke() {
        StubResource instance = new StubResource();
        when(metadata.getInvocationClass()).thenReturn((Class) instance.getClass());
        when(metadata.getMethodName()).thenReturn("doSomething");
        when(metadata.getArgumentTypes()).thenReturn(new Class<?>[] { String.class });
        invocation = new DefaultInvocation(instance, metadata, context, response);
        invocation.putInvocationArgument(0, "foo");
        Object actual = invocation.invoke();
        assertThat(actual.toString(), is("foo is something!!"));
    }
