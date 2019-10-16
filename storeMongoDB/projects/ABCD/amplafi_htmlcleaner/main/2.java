    public TagNode clean(Reader reader, final CleanTimeValues cleanTimeValues) throws IOException {
        pushNesting(cleanTimeValues);
        cleanTimeValues._headOpened = false;
        cleanTimeValues._bodyOpened = false;
        cleanTimeValues._headTags.clear();
        cleanTimeValues.allTags.clear();
        cleanTimeValues.pruneTagSet = new HashSet<ITagNodeCondition>(this.properties.getPruneTagSet());
        cleanTimeValues.allowTagSet = new HashSet<ITagNodeCondition>(this.properties.getAllowTagSet());
        this.transformations = this.properties.getCleanerTransformations();
        cleanTimeValues.pruneNodeSet.clear();

        cleanTimeValues.htmlNode = this.newTagNode("html");
        cleanTimeValues.bodyNode = this.newTagNode("body");
        cleanTimeValues.headNode = this.newTagNode("head");
        cleanTimeValues.rootNode = null;
        cleanTimeValues.htmlNode.addChild(cleanTimeValues.headNode);
        cleanTimeValues.htmlNode.addChild(cleanTimeValues.bodyNode);

        HtmlTokenizer htmlTokenizer = new HtmlTokenizer(this, reader, cleanTimeValues);

		htmlTokenizer.start();

        List nodeList = htmlTokenizer.getTokenList();
        closeAll(nodeList, cleanTimeValues);

        createDocumentNodes(nodeList, cleanTimeValues);
        calculateRootNode( cleanTimeValues, htmlTokenizer.getNamespacePrefixes() );

        // Some transitions on resulting html require us to have the tag tree structure.
        // i.e. if we want to clear insignificant <br> tags. Thus this place is best for
        // marking nodes to be pruned.
        while(markNodesToPrune(nodeList, cleanTimeValues)) {
			// do them all
		}

        // if there are some nodes to prune from tree
        if (cleanTimeValues.pruneNodeSet != null && !cleanTimeValues.pruneNodeSet.isEmpty() ) {
            Iterator<TagNode> iterator = cleanTimeValues.pruneNodeSet.iterator();
            while (iterator.hasNext()) {
                TagNode tagNode = iterator.next();
                TagNode parent = tagNode.getParent();
                if (parent != null) {
                    parent.removeChild(tagNode);
                }
            }
        }

        cleanTimeValues.rootNode.setDocType( htmlTokenizer.getDocType() );
        popNesting(cleanTimeValues);
        return cleanTimeValues.rootNode;
    }
