	public void setModules(Modules modules) {
		this.mappers = new ArrayList<ExceptionMapper>();
		this.mappers.addAll(modules.getExceptionMappers());
	}
