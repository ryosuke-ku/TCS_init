  public void add(int id, float priority) {
    float lowestPriority = Float.NEGATIVE_INFINITY;

    if (priorities.size() > 0) {
      lowestPriority = priorities.get(priorities.size() - 1);
    }

    if ((priority == lowestPriority) ||
        (priority < lowestPriority && ids.size() < preferredMaximumSize)) {
      // simply add the new entry at the lowest priority end
      ids.add(id);
      priorities.add(priority);
    } else if (priority > lowestPriority) {
      if (ids.size() >= preferredMaximumSize) {
        int lowestPriorityIndex = ids.size() - 1;
        while ((lowestPriorityIndex - 1 >= 0) &&
               (priorities.get(lowestPriorityIndex - 1) == lowestPriority)) {
          lowestPriorityIndex--;
        }

        if (lowestPriorityIndex >= preferredMaximumSize - 1) {
          ids.remove(lowestPriorityIndex, ids.size() - lowestPriorityIndex);
          priorities.remove(lowestPriorityIndex, priorities.size() - lowestPriorityIndex);
        }
      }

      // put the new entry in the correct position. Could do a binary search here if the
      // preferredMaximumSize was large.
      int insertPosition = ids.size();
      while (insertPosition - 1 >= 0 && priority > priorities.get(insertPosition - 1)) {
        insertPosition--;
      }

      ids.insert(insertPosition, id);
      priorities.insert(insertPosition, priority);
    }
  }
