        public boolean equals(Object o)
        {
            if (this == o)
                return true;

            if (!(o instanceof DroppedColumn))
                return false;

            DroppedColumn dc = (DroppedColumn) o;

            return name.equals(dc.name) && type.equals(dc.type) && droppedTime == dc.droppedTime;
        }
