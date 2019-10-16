		public void remove()
		{
			final SkipMap<K,V> map2 = map;
			if (map2 == null)
			{
				throw new IllegalStateException();
			}
			map2.removeNode(this.lastKey);
			this.lastKey = null;
		}
