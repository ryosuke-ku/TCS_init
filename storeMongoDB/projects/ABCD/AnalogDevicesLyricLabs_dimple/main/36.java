		public @Nullable V nextValue()
		{
			final SkipMap<K,V> map2 = this.map;
			@Nullable V result = null;
			if (map2 != null)
			{
				@Nullable Object[] node = this.advance();
				result =  node == null ? null : map2.getNodeValue(node);
			}
			return result;
		}
