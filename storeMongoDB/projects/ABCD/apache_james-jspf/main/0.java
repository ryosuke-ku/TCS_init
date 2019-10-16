    public String getMacroIpAddress() {
        
        if (IPAddr.isIPV6(ipAddress)) {
            try {
                return IPAddr.getAddress(ipAddress).getNibbleFormat();
            } catch (PermErrorException e) {
            }
        } 
        
        return ipAddress;

    }
          }
        } catch (RequireClientDomainException e) {
            return null;
        }
    }
