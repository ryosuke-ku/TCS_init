		public void reset(@Nullable SkipMap<K,V> newMap)
		{
			this.map = newMap;
			this.reset();
		}
