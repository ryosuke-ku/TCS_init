		public @Nullable V next()
		{
			V value = null;
			final SkipMap<K,V> map2 = map;
			if (map2 != null)
			{
				K key = null;
				Object[] n = this.nextNode;
				if (n != null)
				{
					value = map2.getNodeValue(n);
					key = map2.getNodeKey(n);
					this.nextNode = map2.getNextNode(n);
				}
				this.lastKey = key;
			}
			return value;
		}
