    public static HashMap<String, Object> decompressAll(Map<String, Object> m) {
        HashMap<String, Object> all = new HashMap(getGlobalValues(m));
        HashMap<String, String> translate = new HashMap();
        translate.put("initial_conditions", "soilLayer");
        translate.put("soil", "soilLayer");
        translate.put("weather", "dailyWeather");
        translate.put("management", "events");
        translate.put("observed", "timeSeries");

        ArrayList<String> buckets = listBucketNames(m);
        for(String bucket : buckets) {
            BucketEntry b = getBucket(m, bucket);
            HashMap<String, Object> sub = new HashMap<String, Object>(b.getValues());
            String nestedKey = translate.get(bucket);
            if (nestedKey == null) {
                nestedKey = "data";
            }
            sub.put(nestedKey, b.getDataList());
            all.put(bucket, sub);
        }
        return all;
    }
     if (separators.size() > 2) {
            throw new NumberFormatException();
        } 
        LOG.debug("separators: "+separators.toString());
        LOG.debug("pieces: "+pieces.toString());
        if (separators.size() == 0) {
            // There should only be one entry anyways.
            finalVal.append(pieces.get(0).toString());
            finalVal.append(".0");
        } else {
            // Handle thousands only separators (no decimal piece)
            if (separators.size() == 1 && ((pieces.size() > 2) || pieces.size() == 1)) {
                for (StringBuilder integerPiece: pieces) {
                    finalVal.append(integerPiece.toString());
                }
                finalVal.append(".0");
            } else {
                String decimalPiece = (pieces.remove(pieces.size()-1)).toString();
                for (StringBuilder integerPiece: pieces) {
                    finalVal.append(integerPiece.toString());
                }
                finalVal.append(".");
                finalVal.append(decimalPiece);
            }
        }
        LOG.debug("Final value: "+finalVal.toString());
        return Double.parseDouble(finalVal.toString());
	}
