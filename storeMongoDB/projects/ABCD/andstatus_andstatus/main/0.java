    Intent intentToViewAndShare(boolean share) {
        StringBuilder subject = new StringBuilder();
        String msgBody = MyQuery.msgIdToStringColumnValue(MsgTable.BODY, messageId);
        String msgBodyPlainText = msgBody;
        if (origin.isHtmlContentAllowed()) {
            msgBodyPlainText = MyHtml.fromHtml(msgBody);
        }

        subject.append(MyContextHolder.get().context()
                .getText(origin.alternativeTermForResourceId(R.string.message)));
        subject.append(" - " + msgBodyPlainText);

        Intent intent = new Intent(share ? android.content.Intent.ACTION_SEND : Intent.ACTION_VIEW);
        if (TextUtils.isEmpty(imageFilename)) {
            intent.setType("text/*");
        } else {
            intent.setDataAndType(FileProvider.downloadFilenameToUri(imageFilename),"image/*");
            intent.putExtra(Intent.EXTRA_STREAM, FileProvider.downloadFilenameToUri(imageFilename));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, I18n.trimTextAt(subject.toString(), 80));
        intent.putExtra(Intent.EXTRA_TEXT, buildBody(origin, msgBodyPlainText, false));
        if (origin.isHtmlContentAllowed() && MyHtml.hasHtmlMarkup(msgBody) ) {
            intent.putExtra(Intent.EXTRA_HTML_TEXT, buildBody(origin, msgBody, true));
        }
        return intent;
    }
