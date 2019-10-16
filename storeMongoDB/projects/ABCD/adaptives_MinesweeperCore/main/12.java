	public void uncover() throws UncoveredMineException {
		this.currentState = this.currentState.uncover();
		//TODO: Should this logic be here or in the state machine ?
		if(isMine()) {
			throw new UncoveredMineException();
		}
	}
