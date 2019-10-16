            public void run() {
                try {
                    
                    final Socket sock = ss.accept();
                    final InputStream is = sock.getInputStream();
                    final Document doc = XmlUtils.toDoc(is, "</message>");
                    final String xmlString = XmlUtils.toString(doc);
                    System.out.println(xmlString);
                    ref.set(xmlString);
                    synchronized (ref) {
                        ref.notifyAll();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
