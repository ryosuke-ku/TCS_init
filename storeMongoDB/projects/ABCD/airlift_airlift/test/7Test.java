    public void testBasic()
    {
        TimedStat stat = new TimedStat();
        List<Double> values = new ArrayList<Double>(VALUES);
        for (int i = 0; i < VALUES; i++) {
            values.add((double) i);
        }
        Collections.shuffle(values);
        for (Double value : values) {
            stat.addValue(value, TimeUnit.MILLISECONDS);
        }
        Collections.sort(values);

        assertEquals(stat.getCount(), values.size());
        assertEquals(stat.getMax(), values.get(values.size() - 1));
        assertEquals(stat.getMin(), values.get(0));
        assertEquals(stat.getMean(), (values.get(0) + values.get(values.size() - 1)) / 2.0);


        assertPercentile("tp50", stat.getTP50(), values, 0.50);
        assertPercentile("tp90", stat.getTP90(), values, 0.90);
        assertPercentile("tp99", stat.getTP99(), values, 0.99);
        assertPercentile("tp999", stat.getTP999(), values, 0.999);

        assertPercentile("tp80", stat.getPercentile(0.80), values, 0.80);
        assertPercentile("tp20", stat.getPercentile(0.20), values, 0.20);
    }
