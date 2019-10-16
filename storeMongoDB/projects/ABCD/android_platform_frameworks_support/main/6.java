    public void setDrawerLockMode(@LockMode int lockMode, View drawerView) {
        if (!isDrawerView(drawerView)) {
            throw new IllegalArgumentException("View " + drawerView + " is not a " +
                    "drawer with appropriate layout_gravity");
        }
        final int gravity = ((LayoutParams) drawerView.getLayoutParams()).gravity;
        setDrawerLockMode(lockMode, gravity);
    }
