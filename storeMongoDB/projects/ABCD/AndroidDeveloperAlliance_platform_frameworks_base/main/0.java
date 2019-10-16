    private Uri getUriFor(Uri tableUri, ContentValues values, long rowId) {
        if (tableUri.getPathSegments().size() != 1) {
            throw new IllegalArgumentException("Invalid URI: " + tableUri);
        }
        String table = tableUri.getPathSegments().get(0);
        if ("system".equals(table) || "secure".equals(table)) {
            String name = values.getAsString(Settings.NameValueTable.NAME);
            return Uri.withAppendedPath(tableUri, name);
        } else {
            return ContentUris.withAppendedId(tableUri, rowId);
        }
    }
ke = "%" + selectionArgs[0] + "%";
                if (mTwoLineDisplay) {
                    myArgs = new String [] { like, like };
                } else {
                    myArgs = new String [] { like };
                }
                suggestSelection = mSuggestSuggestionClause;
            }
            // Suggestions are always performed with the default sort order
            Cursor c = db.query(sSuggestions, mSuggestionProjection,
                    suggestSelection, myArgs, null, null, ORDER_BY, null);
            c.setNotificationUri(getContext().getContentResolver(), uri);
            return c;
        }

        // otherwise process arguments and perform a standard query
        int length = uri.getPathSegments().size();
        if (length != 1 && length != 2) {
            throw new IllegalArgumentException("Unknown Uri");
        }

        String base = uri.getPathSegments().get(0);
        if (!base.equals(sSuggestions)) {
            throw new IllegalArgumentException("Unknown Uri");
        }

        String[] useProjection = null;
        if (projection != null && projection.length > 0) {
            useProjection = new String[projection.length + 1];
            System.arraycopy(projection, 0, useProjection, 0, projection.length);
            useProjection[projection.length] = "_id AS _id";
        }

        StringBuilder whereClause = new StringBuilder(256);
        if (length == 2) {
            whereClause.append("(_id = ").append(uri.getPathSegments().get(1)).append(")");
        }

        // Tack on the user's selection, if present
        if (selection != null && selection.length() > 0) {
            if (whereClause.length() > 0) {
                whereClause.append(" AND ");
            }

            whereClause.append('(');
            whereClause.append(selection);
            whereClause.append(')');
        }
        
        // And perform the generic query as requested
        Cursor c = db.query(base, useProjection, whereClause.toString(),
                selectionArgs, null, null, sortOrder,
                null);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
