    public synchronized int delete(Uri url, String where, String[] whereArgs)
    {
        int count = 0;
        int subId = SubscriptionManager.getDefaultSubscriptionId();
        String userOrCarrierEdited = ") and (" +
                EDITED + "=" + USER_EDITED +  " or " +
                EDITED + "=" + CARRIER_EDITED + ")";
        String notUserOrCarrierEdited = ") and (" +
                EDITED + "!=" + USER_EDITED +  " and " +
                EDITED + "!=" + CARRIER_EDITED + ")";
        ContentValues cv = new ContentValues();
        cv.put(EDITED, USER_DELETED);

        checkPermission();

        SQLiteDatabase db = getWritableDatabase();
        int match = s_urlMatcher.match(url);
        switch (match)
        {
            case URL_TELEPHONY_USING_SUBID:
            {
                 String subIdString = url.getLastPathSegment();
                 try {
                     subId = Integer.parseInt(subIdString);
                 } catch (NumberFormatException e) {
                     loge("NumberFormatException" + e);
                     throw new IllegalArgumentException("Invalid subId " + url);
                 }
                 if (DBG) log("subIdString = " + subIdString + " subId = " + subId);
                // FIXME use subId in query
            }
            //intentional fall through from above case

            case URL_TELEPHONY:
            {
                // Delete user/carrier edited entries
                count = db.delete(CARRIERS_TABLE, "(" + where + userOrCarrierEdited, whereArgs);
                // Otherwise mark as user deleted instead of deleting
                count += db.update(CARRIERS_TABLE, cv, "(" + where + notUserOrCarrierEdited,
                        whereArgs);
                break;
            }

            case URL_CURRENT_USING_SUBID: {
                String subIdString = url.getLastPathSegment();
                try {
                    subId = Integer.parseInt(subIdString);
                } catch (NumberFormatException e) {
                    loge("NumberFormatException" + e);
                    throw new IllegalArgumentException("Invalid subId " + url);
                }
                if (DBG) log("subIdString = " + subIdString + " subId = " + subId);
                // FIXME use subId in query
            }
            //intentional fall through from above case

            case URL_CURRENT:
            {
                // Delete user/carrier edited entries
                count = db.delete(CARRIERS_TABLE, "(" + where + userOrCarrierEdited, whereArgs);
                // Otherwise mark as user deleted instead of deleting
                count += db.update(CARRIERS_TABLE, cv, "(" + where + notUserOrCarrierEdited,
                        whereArgs);
                break;
            }

            case URL_ID:
            {
                // Delete user/carrier edited entries
                count = db.delete(CARRIERS_TABLE,
                        "(" + _ID + "=?" + userOrCarrierEdited,
                        new String[] { url.getLastPathSegment() });
                // Otherwise mark as user deleted instead of deleting
                count += db.update(CARRIERS_TABLE, cv,
                        "(" + _ID + "=?" + notUserOrCarrierEdited,
                        new String[]{url.getLastPathSegment() });
                break;
            }

            case URL_RESTOREAPN_USING_SUBID: {
                String subIdString = url.getLastPathSegment();
                try {
                    subId = Integer.parseInt(subIdString);
                } catch (NumberFormatException e) {
                    loge("NumberFormatException" + e);
                    throw new IllegalArgumentException("Invalid subId " + url);
                }
                if (DBG) log("subIdString = " + subIdString + " subId = " + subId);
                // FIXME use subId in query
            }
            case URL_RESTOREAPN: {
                count = 1;
                restoreDefaultAPN(subId);
                break;
            }

            case URL_PREFERAPN_USING_SUBID:
            case URL_PREFERAPN_NO_UPDATE_USING_SUBID: {
                String subIdString = url.getLastPathSegment();
                try {
                    subId = Integer.parseInt(subIdString);
                } catch (NumberFormatException e) {
                    loge("NumberFormatException" + e);
                    throw new IllegalArgumentException("Invalid subId " + url);
                }
                if (DBG) log("subIdString = " + subIdString + " subId = " + subId);
            }
            //intentional fall through from above case

            case URL_PREFERAPN:
            case URL_PREFERAPN_NO_UPDATE:
            {
                setPreferredApnId((long)INVALID_APN_ID, subId);
                if ((match == URL_PREFERAPN) || (match == URL_PREFERAPN_USING_SUBID)) count = 1;
                break;
            }

            case URL_SIMINFO: {
                count = db.delete(SIMINFO_TABLE, where, whereArgs);
                break;
            }

            case URL_UPDATE_DB: {
                updateApnDb();
                count = 1;
                break;
            }

            default: {
                throw new UnsupportedOperationException("Cannot delete that URL: " + url);
            }
        }

        if (count > 0) {
            getContext().getContentResolver().notifyChange(CONTENT_URI, null,
                    true, UserHandle.USER_ALL);
        }

        return count;
    }
