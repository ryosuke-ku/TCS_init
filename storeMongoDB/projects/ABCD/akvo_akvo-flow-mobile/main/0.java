    public void load() {
        mLoaded = true;
        loadGroup();
    }
          mValue = data.getString(ConstantUtil.CADDISFLY_RESPONSE);

            // Get optional image and store it as part of the response
            String image = data.getString(ConstantUtil.CADDISFLY_IMAGE);

            Log.d(TAG, "caddisflyTestComplete - Response: " + mValue + ". Image: " + image);

            File src = !TextUtils.isEmpty(image) ? new File(image) : null;
            if (src != null && src.exists()) {
                // Move the image into the FLOW directory
                File dst = new File(FileUtil.getFilesDir(FileUtil.FileType.MEDIA), src.getName());

                if (!src.renameTo(dst)) {
                    Log.e(TAG, String.format("Could not move file %s to %s",
                            src.getAbsoluteFile(), dst.getAbsoluteFile()));
                } else {
                    mImage = dst.getAbsolutePath();
                }
            }

            displayResponseView();
        }
        captureResponse();
    }
