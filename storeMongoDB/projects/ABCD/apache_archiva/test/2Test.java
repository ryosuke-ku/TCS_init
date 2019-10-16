    public void testLegacyArtifactPathWithoutClassifierResolution()
    {
        legacyArtifactPath.setArtifact( "groupId:artifactId:version::type" );

        assertEquals( "groupId", legacyArtifactPath.getGroupId() );
        assertEquals( "artifactId", legacyArtifactPath.getArtifactId() );
        assertEquals( "version", legacyArtifactPath.getVersion() );
        assertNull( legacyArtifactPath.getClassifier() );
        assertEquals( "type", legacyArtifactPath.getType() );
    }
