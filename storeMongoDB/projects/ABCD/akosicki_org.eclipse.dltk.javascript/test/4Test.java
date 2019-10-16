	public void testAbstractAndNormalMembers() {
		final Type superClass = eINSTANCE.createType();
		superClass.setName("SuperClass");
		final Method normalMethod = eINSTANCE.createMethod();
		normalMethod.setName("method");
		{
			final Parameter param = eINSTANCE.createParameter();
			param.setName("normalMethodParam");
			normalMethod.getParameters().add(param);
		}
		superClass.getMembers().add(normalMethod);

		final Type middleClass = eINSTANCE.createType();
		middleClass.setName("MiddleClass");
		middleClass.setSuperType(superClass);

		final Type trait = eINSTANCE.createType();
		trait.setName("Trait");
		final Method abstractMethod = eINSTANCE.createMethod();
		abstractMethod.setName("method");
		abstractMethod.setAbstract(true);
		{
			final Parameter param = eINSTANCE.createParameter();
			param.setName("abstractMethodParam");
			abstractMethod.getParameters().add(param);
		}
		trait.getMembers().add(abstractMethod);

		final Type subClass = eINSTANCE.createType();
		subClass.setName("SubClass");
		subClass.setSuperType(middleClass);
		subClass.getTraits().add(trait);

		final Iterator<Member> it = new TypeMemberQuery(subClass)
				.ignoreDuplicates().iterator();
		assertTrue(it.hasNext());
		final Member member = it.next();
		assertFalse(it.hasNext());
		assertSame(normalMethod, member);
	}
