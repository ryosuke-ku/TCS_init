   public void register() throws Exception {
      log.info("Registering " + newMember.getName());
      em.persist(newMember);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      memberEventSrc.fire(newMember);
      initNewMember();
   }
