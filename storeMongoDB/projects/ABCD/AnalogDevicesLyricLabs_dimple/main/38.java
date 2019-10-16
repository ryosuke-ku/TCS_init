		public void release()
		{
			if (ValueIterator.reusableInstance.get() == null)
			{
				this.reset(null);
				ValueIterator.reusableInstance.set(this);
			}
		}
