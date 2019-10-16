    public static IPNetwork merge(final IPNetwork[] networks) {
        if(networks == null) {
            return null;
        }

        final int n = networks.length;
        int x = 1;

        while((1<<x) < n) {
            x++;
        }
        
        if(Math.pow(2D, (double) x) != (double) n) {
            throw new IllegalArgumentException("Unable to merge " + n
                    + " networks into one: invalid number of sub-networks");
        }

        IPNetwork last = networks[0];
        final int masklen = last.getNetmask() - x;

        if(masklen < 1) {
            throw new IllegalArgumentException("Unable to merge " + n
                    + " networks into one: any address");
        }

        for(int k=1; k<networks.length; k++) {
            if(!last.next().equals(networks[k])) {
                // networks not in order or invalid...
                throw new IllegalArgumentException("Unable to merge " + n
                        + " networks into one: arguments not in order or invalid");
            }
            last = networks[k];
        }
        
        // get possible super-network address...
        byte[] low = new byte[last.isV6() ? 16 : 4];
        int i = masklen / 8;
        int s = 8 - (masklen % 8);
        System.arraycopy(last.getAddr() , 0, low, 0, i);

        // unset least significant bits...
        if(s > 0 && s < 8) {
            byte b = (byte) last.getAddr()[i];
            for(int j=0; j<s; j++) {
                b = (byte) (b & ~(1 << j));
            }
            low[i] = b;
        }
        
        // try to create the super-network...
        IPNetwork net = null;
        try {
            InetAddress inet = InetAddress.getByAddress(low);
            net = new IPNetwork(inet.getHostAddress() + "/" + masklen);
            net.setEmbeddedIPv4(last.hasEmbeddedIPv4());
        } catch(UnknownHostException ex) {
            throw new IllegalStateException("Unable to calculate merged network", ex);
        }
        
        return net;
    }
