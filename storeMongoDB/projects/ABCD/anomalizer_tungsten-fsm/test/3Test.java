            public void doAction(Event ev, Entity entity,
                    Transition transition, int actionType)
            {
                EntityAdapter ea = (EntityAdapter) entity;
                int current = ((Integer) ea.getEntity()).intValue();
                Integer next = new Integer(current + 1);
                ea.setEntity(next);
            }
