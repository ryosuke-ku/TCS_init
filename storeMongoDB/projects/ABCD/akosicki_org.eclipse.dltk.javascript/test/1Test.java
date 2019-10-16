	public void testStaticAndInstanceMembers() {
		final Type type = eINSTANCE.createType();
		type.setName("Test");
		final Method instanceMethod = eINSTANCE.createMethod();
		instanceMethod.setName("run");
		type.getMembers().add(instanceMethod);
		final Method staticMethod = eINSTANCE.createMethod();
		staticMethod.setName("run");
		staticMethod.setStatic(true);
		type.getMembers().add(staticMethod);
		final List<Member> unique = new ArrayList<Member>();
		for (Member member : new TypeMemberQuery(type).ignoreDuplicates()) {
			unique.add(member);
		}
		assertEquals(2, unique.size());
	}
