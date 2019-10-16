        public void run() {
            // http://developer.android.com/guide/topics/text/copy-paste.html
            ClipboardManager clipboard = (ClipboardManager) MyContextHolder.get().context()
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            clip = clipboard.getPrimaryClip();
        }
