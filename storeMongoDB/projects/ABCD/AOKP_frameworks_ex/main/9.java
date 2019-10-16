    protected View getView(int partition, Cursor cursor, int position, View convertView,
            ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = newView(mContext, partition, cursor, position, parent);
        }
        bindView(view, partition, cursor, position);
        return view;
    }
