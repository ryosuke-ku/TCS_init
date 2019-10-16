    public void openDrawer(@EdgeGravity int gravity, boolean animate) {
        final View drawerView = findDrawerWithGravity(gravity);
        if (drawerView == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " +
                    gravityToString(gravity));
        }
        openDrawer(drawerView, animate);
    }
