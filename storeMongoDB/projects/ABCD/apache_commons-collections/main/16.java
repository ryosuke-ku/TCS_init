    public static <I, O> Transformer<I, O> switchMapTransformer(
            final Map<I, Transformer<I, O>> objectsAndTransformers) {

        if (objectsAndTransformers == null) {
            throw new NullPointerException("The object and transformer map must not be null");
        }
        final Transformer<? super I, ? extends O> def = objectsAndTransformers.remove(null);
        final int size = objectsAndTransformers.size();
        final Transformer<? super I, ? extends O>[] trs = new Transformer[size];
        final Predicate<I>[] preds = new Predicate[size];
        int i = 0;
        for (final Map.Entry<I, Transformer<I, O>> entry : objectsAndTransformers.entrySet()) {
            preds[i] = EqualPredicate.<I>equalPredicate(entry.getKey());
            trs[i++] = entry.getValue();
        }
        return TransformerUtils.switchTransformer(preds, trs, def);
    }
