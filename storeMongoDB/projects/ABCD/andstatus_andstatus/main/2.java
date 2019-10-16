    public void startEditingMessage(MessageEditorData data) {
        if (!data.isValid()) {
            MyLog.v(MessageEditorData.TAG, "Not a valid data " + data);
            return;
        }
        if (!data.status.mayBeEdited()) {
            MyLog.v(MessageEditorData.TAG, "Cannot be edited " + data);
            return;
        }
        data.status = DownloadStatus.DRAFT;
        updateDataFromScreen();
        MessageEditorCommand command = new MessageEditorCommand(data, editorData);
        command.showAfterSave = true;
        command.beingEdited = true;
        saveData(command);
        if (data.getMyAccount().getConnection().isApiSupported(ApiRoutineEnum.ACCOUNT_RATE_LIMIT_STATUS)) {
            MyServiceManager.sendForegroundCommand(
                    CommandData.newAccountCommand(CommandEnum.RATE_LIMIT_STATUS, data.getMyAccount()));
        }
    }
