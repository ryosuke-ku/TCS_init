    public List<TabletLocation> lookupTablets(ClientContext context, String tserver, Map<KeyExtent,List<Range>> map, TabletLocator parent)
        throws AccumuloSecurityException {

      ArrayList<TabletLocation> list = new ArrayList<>();

      Map<KeyExtent,SortedMap<Key,Value>> tablets = tservers.get(tserver);

      if (tablets == null) {
        parent.invalidateCache(context.getInstance(), tserver);
        return list;
      }

      TreeMap<Key,Value> results = new TreeMap<>();

      Set<Entry<KeyExtent,List<Range>>> es = map.entrySet();
      List<KeyExtent> failures = new ArrayList<>();
      for (Entry<KeyExtent,List<Range>> entry : es) {
        SortedMap<Key,Value> tabletData = tablets.get(entry.getKey());

        if (tabletData == null) {
          failures.add(entry.getKey());
          continue;
        }
        List<Range> ranges = entry.getValue();
        for (Range range : ranges) {
          SortedMap<Key,Value> tm;
          if (range.getStartKey() == null)
            tm = tabletData;
          else
            tm = tabletData.tailMap(range.getStartKey());

          for (Entry<Key,Value> de : tm.entrySet()) {
            if (range.afterEndKey(de.getKey())) {
              break;
            }

            if (range.contains(de.getKey())) {
              results.put(de.getKey(), de.getValue());
            }
          }
        }
      }

      if (failures.size() > 0)
        parent.invalidateCache(failures);

      return MetadataLocationObtainer.getMetadataLocationEntries(results).getLocations();

    }
