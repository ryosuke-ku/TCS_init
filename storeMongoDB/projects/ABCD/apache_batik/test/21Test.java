    public TestReport runImpl() throws Exception {
        DefaultTestReport report = new DefaultTestReport(this);

        int lineNo=0;
        try {
            BufferedReader reader;
            reader = new BufferedReader
                (new InputStreamReader(rects.openStream()));

            // Now write a canidate reference/variation file...
            if (can.exists())
                can.delete();

            FileOutputStream fos = new FileOutputStream(can);
            PrintStream ps = new PrintStream(fos);

            Map rlms = new HashMap();
            RectListManager currRLM = null;
            String          currID  = null;
            String line;
            while ((line = reader.readLine()) != null) {
                lineNo++;
                line = line.toLowerCase();
                StringTokenizer st = new StringTokenizer(line);

                // Check blank line...
                if (!st.hasMoreTokens()) continue;

                // Get first token
                String pref = st.nextToken();

                // Check for comment.
                if (pref.startsWith("#")) continue;

                if        (RECT_PREF.equals(pref)) {
                    if (st.countTokens() != 4) continue;
                    if (currRLM == null) continue;

                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());
                    int h = Integer.parseInt(st.nextToken());
                    currRLM.add(new Rectangle(x, y, w, h));
                }
                else if (RLM_PREF.equals(pref)) {
                    String id = st.nextToken();
                    Object o = rlms.get(id);
                    if (o == null) {
                        o = new RectListManager();
                        rlms.put(id, o);
                    }
                    currRLM = (RectListManager)o;
                    currID  = id;
                }
                else if (MERGE_PREF.equals(pref)) {
                    if (currRLM == null) continue;
                    int overhead     = Integer.parseInt(st.nextToken());
                    int lineOverhead = Integer.parseInt(st.nextToken());
                    currRLM.mergeRects(overhead, lineOverhead);
                }
                else if (ADD_PREF.equals(pref)) {
                    if (currRLM == null) continue;
                    String id = st.nextToken();
                    Object o = rlms.get(id);
                    if (o == null) continue;
                    currRLM.add((RectListManager)o);
                }
                else if (SUBTRACT_PREF.equals(pref)) {
                    if (currRLM == null) continue;
                    String id = st.nextToken();
                    Object o = rlms.get(id);
                    if (o == null) continue;
                    int overhead = Integer.parseInt(st.nextToken());
                    int lineOverhead = Integer.parseInt(st.nextToken());
                    currRLM.subtract((RectListManager)o,
                                     overhead, lineOverhead);
                }
                else if (CONTAINS_ALL_PREF.equals(pref)) {
                    if (currRLM == null) continue;
                    String id = st.nextToken();
                    Object o = rlms.get(id);
                    if (o == null) continue;
                    RectListManager rlm = (RectListManager)o;
                    ps.println("ID: " + currID + " Sz: " + currRLM.size());

                    if (currRLM.containsAll(rlm)) {
                        ps.println("  Contains all: " + id +
                                   " Sz: " + rlm.size());
                    } else {
                        ps.println("  Does not contain all: " + id +
                                   " Sz: " + rlm.size());
                    }
                    ps.println();
                }
                else if (REMOVE_ALL_PREF.equals(pref)) {
                    if (currRLM == null) continue;
                    String id = st.nextToken();
                    Object o = rlms.get(id);
                    if (o == null) continue;
                    currRLM.removeAll((RectListManager)o);
                }
                else if (RETAIN_ALL_PREF.equals(pref)) {
                    if (currRLM == null) continue;
                    String id = st.nextToken();
                    Object o = rlms.get(id);
                    if (o == null) continue;
                    currRLM.retainAll((RectListManager)o);
                }
                else if (PRINT_PREF.equals(pref)) {
                    if (currRLM == null) continue;

                    Iterator i = currRLM.iterator();
                    ps.println("ID: " + currID + " Sz: " + currRLM.size());
                    while (i.hasNext()) {
                        ps.println("  " + i.next());
                    }
                    ps.println();
                }
            }

            ps.close();
            fos.close();
        } catch(Exception e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            report.setErrorCode(ERROR_READING_RECTS);
            report.setDescription(new TestReport.Entry[] {
                new TestReport.Entry
                    (Messages.formatMessage(ENTRY_KEY_ERROR_DESCRIPTION, null),
                     Messages.formatMessage
                     (ERROR_READING_RECTS,
                      new String[]{ ""+lineNo, rects.toString(), 
                                   trace.toString()}))
                    });
            report.setPassed(false);
            return report;
        }

        InputStream refIS = null;
        try {
            refIS = var.openStream();
        } catch(Exception e) {
            try {
                refIS = ref.openStream();
            } catch(Exception ex) {
                StringWriter trace = new StringWriter();
                e.printStackTrace(new PrintWriter(trace));
                report.setErrorCode(ERROR_CANNOT_READ_REF_URL);
                report.setDescription
                    (new TestReport.Entry[] {
                        new TestReport.Entry
                            (Messages.formatMessage
                             (ENTRY_KEY_ERROR_DESCRIPTION, null),
                             Messages.formatMessage
                             (ERROR_CANNOT_READ_REF_URL,
                              new String[]{ref.toString(), trace.toString()}))
                            });
                report.setPassed(false);
            }
        }

        int mismatch = -2;
        if (refIS != null) {
            InputStream canIS = new FileInputStream(can);
            Checker check = new Checker(canIS, refIS);
            check.start();
            mismatch = check.getMismatch();

        }

        if (mismatch == -1) {
          report.setPassed(true);
          can.delete();
          return report;
        }

        if (mismatch == -2) {
            report.setErrorCode(ERROR_NO_REFERENCE);
            report.setDescription(new TestReport.Entry[] {
                new TestReport.Entry
                    (Messages.formatMessage(ENTRY_KEY_ERROR_DESCRIPTION, null),
                     Messages.formatMessage(ERROR_NO_REFERENCE,
                                            new String[]{ref.toString()}))
                    });
        } else {
            report.setErrorCode(ERROR_WRONG_RESULT);
            report.setDescription(new TestReport.Entry[] {
                new TestReport.Entry
                    (Messages.formatMessage(ENTRY_KEY_ERROR_DESCRIPTION, null),
                     Messages.formatMessage(ERROR_WRONG_RESULT,
                                            new String[]{""+mismatch}))
                    });
        }
        report.setPassed(false);

        return report;
    }
