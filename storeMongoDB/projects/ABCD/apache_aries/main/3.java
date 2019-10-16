  public VersionRange getVersion() {
    String deployedVersion = _content.getAttribute(AppConstants.DEPLOYMENT_BUNDLE_VERSION);
    VersionRange vr = null;
    if (deployedVersion != null && deployedVersion.length() > 0) {
      vr = ManifestHeaderProcessor.parseVersionRange(deployedVersion, true);
    }
    return vr;
  }
