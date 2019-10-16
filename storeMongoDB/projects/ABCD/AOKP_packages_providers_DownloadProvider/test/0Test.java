    public void testGetFullPath() throws Exception {
      String hint = "file:///com.android.providers.downloads/test";

      // Test that we never change requested filename.
      String fileName = Helpers.getFullPath(
          hint,
          "video/mp4", // MIME type corresponding to file extension .mp4
          Downloads.Impl.DESTINATION_FILE_URI,
          null);
      assertEquals(hint, fileName);
    }
