    public synchronized Uri insert(Uri url, ContentValues initialValues)
    {
        Uri result = null;
        int subId = SubscriptionManager.getDefaultSubscriptionId();

        checkPermission();

        SQLiteDatabase db = getWritableDatabase();
        int match = s_urlMatcher.match(url);
        boolean notify = false;
        switch (match)
        {
            case URL_TELEPHONY_USING_SUBID:
            {
                String subIdString = url.getLastPathSegment();
                try {
                    subId = Integer.parseInt(subIdString);
                } catch (NumberFormatException e) {
                    loge("NumberFormatException" + e);
                    return result;
                }
                if (DBG) log("subIdString = " + subIdString + " subId = " + subId);
            }
            //intentional fall through from above case

            case URL_TELEPHONY:
            {
                ContentValues values;
                if (initialValues != null) {
                    values = new ContentValues(initialValues);
                } else {
                    values = new ContentValues();
                }

                values = DatabaseHelper.setDefaultValue(values);
                if (!values.containsKey(EDITED)) {
                    values.put(EDITED, USER_EDITED);
                }

                try {
                    // Replace on conflict so that if same APN is present in db with edited
                    // as UNEDITED or USER/CARRIER_DELETED, it is replaced with
                    // edited USER/CARRIER_EDITED
                    long rowID = db.insertWithOnConflict(CARRIERS_TABLE, null, values,
                            SQLiteDatabase.CONFLICT_REPLACE);
                    if (rowID >= 0) {
                        result = ContentUris.withAppendedId(CONTENT_URI, rowID);
                        notify = true;
                    }
                    if (VDBG) log("insert: inserted " + values.toString() + " rowID = " + rowID);
                } catch (SQLException e) {
                    log("insert: exception " + e);
                    // Insertion failed which could be due to a conflict. Check if that is the case
                    // and merge the entries
                    Cursor oldRow = DatabaseHelper.selectConflictingRow(db, CARRIERS_TABLE, values);
                    if (oldRow != null) {
                        ContentValues mergedValues = new ContentValues();
                        DatabaseHelper.mergeFieldsAndUpdateDb(db, CARRIERS_TABLE, oldRow, values,
                                mergedValues, false, getContext());
                        oldRow.close();
                        notify = true;
                    }
                }

                break;
            }

            case URL_CURRENT_USING_SUBID:
            {
                String subIdString = url.getLastPathSegment();
                try {
                    subId = Integer.parseInt(subIdString);
                } catch (NumberFormatException e) {
                    loge("NumberFormatException" + e);
                    return result;
                }
                if (DBG) log("subIdString = " + subIdString + " subId = " + subId);
                // FIXME use subId in the query
            }
            //intentional fall through from above case

            case URL_CURRENT:
            {
                // zero out the previous operator
                db.update(CARRIERS_TABLE, s_currentNullMap, CURRENT + "!=0", null);

                String numeric = initialValues.getAsString(NUMERIC);
                int updated = db.update(CARRIERS_TABLE, s_currentSetMap,
                        NUMERIC + " = '" + numeric + "'", null);

                if (updated > 0)
                {
                    if (VDBG) log("Setting numeric '" + numeric + "' to be the current operator");
                }
                else
                {
                    loge("Failed setting numeric '" + numeric + "' to the current operator");
                }
                break;
            }

            case URL_PREFERAPN_USING_SUBID:
            case URL_PREFERAPN_NO_UPDATE_USING_SUBID:
            {
                String subIdString = url.getLastPathSegment();
                try {
                    subId = Integer.parseInt(subIdString);
                } catch (NumberFormatException e) {
                    loge("NumberFormatException" + e);
                    return result;
                }
                if (DBG) log("subIdString = " + subIdString + " subId = " + subId);
            }
            //intentional fall through from above case

            case URL_PREFERAPN:
            case URL_PREFERAPN_NO_UPDATE:
            {
                if (initialValues != null) {
                    if(initialValues.containsKey(COLUMN_APN_ID)) {
                        setPreferredApnId(initialValues.getAsLong(COLUMN_APN_ID), subId);
                    }
                }
                break;
            }

            case URL_SIMINFO: {
               long id = db.insert(SIMINFO_TABLE, null, initialValues);
               result = ContentUris.withAppendedId(SubscriptionManager.CONTENT_URI, id);
               break;
            }
        }

        if (notify) {
            getContext().getContentResolver().notifyChange(CONTENT_URI, null,
                    true, UserHandle.USER_ALL);
        }

        return result;
    }
