    public void setPositions(Iterable<? extends LatLon> inPositions, double altitude)
    {
        this.reset();
        this.positions = new ArrayList<Position>();
        this.extents.clear();
        if (inPositions != null)
        {
            for (LatLon position : inPositions)
            {
                this.positions.add(new Position(position, altitude));
            }
            this.measurer.setPositions(this.positions);
        }

        if (this.filled && this.positions.size() < 3)
        {
            String msg = Logging.getMessage("generic.InsufficientPositions");
            Logging.logger().severe(msg);
            throw new IllegalArgumentException(msg);
        }
    }
