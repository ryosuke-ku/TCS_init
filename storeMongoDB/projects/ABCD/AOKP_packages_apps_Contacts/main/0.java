    /*package*/ void addAdapter(ListAdapter adapter) {
        if (mSize >= mAdapters.length) {
            int newCapacity = mSize + 2;
            ListAdapter[] newAdapters = new ListAdapter[newCapacity];
            System.arraycopy(mAdapters, 0, newAdapters, 0, mSize);
            mAdapters = newAdapters;

            int[] newCounts = new int[newCapacity];
            System.arraycopy(mCounts, 0, newCounts, 0, mSize);
            mCounts = newCounts;

            int[] newViewTypeCounts = new int[newCapacity];
            System.arraycopy(mViewTypeCounts, 0, newViewTypeCounts, 0, mSize);
            mViewTypeCounts = newViewTypeCounts;
        }

        adapter.registerDataSetObserver(mDataSetObserver);

        int count = adapter.getCount();
        int viewTypeCount = adapter.getViewTypeCount();

        mAdapters[mSize] = adapter;
        mCounts[mSize] = count;
        mCount += count;
        mAllItemsEnabled &= adapter.areAllItemsEnabled();
        mViewTypeCounts[mSize] = viewTypeCount;
        mViewTypeCount += viewTypeCount;
        mSize++;

        notifyDataChanged();
    }
uilder, quoteSpans[i], new StreamItemQuoteSpan(color, width));
            }
        }

        final ImageSpan[] imageSpans = builder.getSpans(0, length, ImageSpan.class);
        if (imageSpans != null) {
            for (int i = 0; i < imageSpans.length; i++) {
                ImageSpan span = imageSpans[i];
                replaceSpan(builder, span, new ImageSpan(span.getDrawable(),
                        ImageSpan.ALIGN_BASELINE));
            }
        }

        // Trim the trailing new line characters at the end of the text (which can be added
        // when HTML block quote tags are turned into new line characters).
        int end = length;
        for (int i = builder.length() - 1; i >= 0; i--) {
            if (builder.charAt(i) != '\n') {
                break;
            }
            end = i;
        }

        // If there's no trailing newlines, just return it.
        if (end == length) {
            return builder;
        }

        // Otherwise, Return a substring of the original {@link Spanned} text
        // from the start index (inclusive) to the end index (exclusive).
        return new SpannableStringBuilder(builder, 0, end);
    }
