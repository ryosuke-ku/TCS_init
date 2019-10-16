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
ata> metadatas = Maps.newEmptyHashMap();
        metadatas.put(path1, meta1);
        metadatas.put(path2, meta2);
        metadatas = Collections.unmodifiableMap(metadatas);
        RequestPath requestPath1 = new DefaultRequestPath(URI.create("/"), URI.create("/path"),
                "GET");
        when(meta2.getDefinedPath()).thenReturn(path2);
        when(context.getRequestPath()).thenReturn(requestPath1);
        InvocationMetadata actual = finder.find(metadatas, context);
        assertThat(actual, is(meta1));
        RequestPath requestPath2 = new DefaultRequestPath(URI.create("/"), URI.create("/path"),
                "POST");
        when(context.getRequestPath()).thenReturn(requestPath2);
        actual = finder.find(metadatas, context);
        assertThat(actual, is(meta2));
    }
