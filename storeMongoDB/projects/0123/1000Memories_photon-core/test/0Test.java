    public void testRotate() throws Exception {
        BufferedImage initialImage = ImageIO.read(getImage("mf.jpg"));
        int initialHeight = initialImage.getHeight();
        int initialWidth = initialImage.getWidth();

        when(photoProvider.getPhotoInputStream("mf.jpg")).thenReturn(getImage("mf.jpg"));

        ClientResponse response = client().resource("/mf.jpg;r=90").get(ClientResponse.class);
        assertThat(response.getType().toString(), is("image/jpeg"));
        BufferedImage result = ImageIO.read(response.getEntity(InputStream.class));
        assertThat(result.getWidth(), is(initialHeight));
        assertThat(result.getHeight(), is(initialWidth));
    }
