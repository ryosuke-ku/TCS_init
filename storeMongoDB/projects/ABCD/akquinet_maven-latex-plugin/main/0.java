    File getTargetDirectory( File sourceFile, File sourceBaseDir, File targetBaseDir )
        throws MojoExecutionException, MojoFailureException
    {
        String filePath;
        String tempPath;
        try
        {
            filePath = sourceFile.getParentFile().getCanonicalPath();
            tempPath = sourceBaseDir.getCanonicalPath();
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error getting canonical path", e );
        }

        if ( !filePath.startsWith( tempPath ) )
        {
            throw new MojoFailureException( "File " + sourceFile
                + " is expected to be somewhere under the following directory: " + tempPath );
        }

        File targetDir = new File( targetBaseDir, filePath.substring( tempPath.length() ) );
        return targetDir;
    }
