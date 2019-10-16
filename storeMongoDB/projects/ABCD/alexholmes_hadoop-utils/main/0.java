    public int run(final String[] args) throws Exception {

        SortConfig sortConfig = new SortConfig(getConf());

        Integer numMapTasks = null;
        Integer numReduceTasks = null;

        List<String> otherArgs = new ArrayList<String>();
        InputSampler.Sampler<K, V> sampler = null;
        Class<? extends CompressionCodec> codecClass = null;
        Class<? extends CompressionCodec> mapCodecClass = null;
        boolean createLzopIndex = false;
        for (int i = 0; i < args.length; ++i) {
            try {
                if ("-m".equals(args[i])) {
                    numMapTasks = Integer.parseInt(args[++i]);
                } else if ("-r".equals(args[i])) {
                    numReduceTasks = Integer.parseInt(args[++i]);
                } else if ("-f".equals(args[i]) || "--ignore-case".equals(args[i])) {
                    sortConfig.setIgnoreCase(true);
                } else if ("-u".equals(args[i]) || "--unique".equals(args[i])) {
                    sortConfig.setUnique(true);
                } else if ("-k".equals(args[i]) || "--key".equals(args[i])) {
                    String[] parts = StringUtils.split(args[++i], ",");
                    sortConfig.setStartKey(Integer.valueOf(parts[0]));
                    if (parts.length > 1) {
                        sortConfig.setEndKey(Integer.valueOf(parts[1]));
                    }
                } else if ("-t".equals(args[i]) || "--field-separator".equals(args[i])) {
                    sortConfig.setFieldSeparator(args[++i]);
                } else if ("--total-order".equals(args[i])) {
                    double pcnt = Double.parseDouble(args[++i]);
                    int numSamples = Integer.parseInt(args[++i]);
                    int maxSplits = Integer.parseInt(args[++i]);
                    if (0 >= maxSplits) {
                        maxSplits = Integer.MAX_VALUE;
                    }
                    sampler = new InputSampler.RandomSampler<K, V>(pcnt, numSamples, maxSplits);
                } else if ("--map-codec".equals(args[i])) {
                    mapCodecClass = (Class<? extends CompressionCodec>) Class.forName(args[++i]);
                } else if ("--codec".equals(args[i])) {
                    codecClass = (Class<? extends CompressionCodec>) Class.forName(args[++i]);
                } else if ("--lzop-index".equals(args[i])) {
                    createLzopIndex = true;
                } else {
                    otherArgs.add(args[i]);
                }
            } catch (NumberFormatException except) {
                System.out.println("ERROR: Integer expected instead of " + args[i]);
                return printUsage();
            } catch (ArrayIndexOutOfBoundsException except) {
                System.out.println("ERROR: Required parameter missing from "
                        + args[i - 1]);
                return printUsage(); // exits
            }
        }

        // Make sure there are exactly 2 parameters left.
        if (otherArgs.size() != 2) {
            System.out.println("ERROR: Wrong number of parameters: "
                    + otherArgs.size() + " instead of 2.");
            return printUsage();
        }

        if (runJob(new JobConf(sortConfig.getConfig()), numMapTasks, numReduceTasks, sampler,
                codecClass, mapCodecClass, createLzopIndex, otherArgs.get(0), otherArgs.get(1))) {
            return 0;
        }
        return 1;
    }
