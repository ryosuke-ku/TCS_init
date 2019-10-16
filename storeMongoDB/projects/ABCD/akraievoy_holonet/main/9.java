    public boolean hasNext() {
      while (pos >= uptoExcl && from < leads.length) {
        from += 1; // outer loop for from
        if (from < leads.length) {
          pos = fromIncl = leads[from][0];
          uptoExcl = leads[from][1];
        } else {
          pos = leads.length;
          uptoExcl = leads.length;
        }
      }

      return from() < leads.length;
    }
