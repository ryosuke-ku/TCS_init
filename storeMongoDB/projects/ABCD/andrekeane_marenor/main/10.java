    public void setFollowTerrain(boolean followTerrain)
    {
        this.reset();
        this.followTerrain = followTerrain;
        this.measurer.setFollowTerrain(followTerrain);
        this.extents.clear();
    }
