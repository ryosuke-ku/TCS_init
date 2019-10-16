  public static Resource generateFromJson(String json) throws ContractException, IllegalArgumentException {
    BaseResource resource = null;
    if (json.indexOf("rendering_job") > -1 && json.indexOf("directing_and_rendering_job") == -1) {
      resource = new RenderingJob();
    }
    else if (json.indexOf("directing_job") > -1 && json.indexOf("directing_and_rendering_job") == -1) {
      resource = new DirectingJob();
    }
    else if (json.indexOf("directing_and_rendering_job") > -1) {
      resource = new DirectingAndRenderingJob();
    }
    else {
      throw new IllegalArgumentException("Expecting either a rendering_job, directing_job, or directing_and_rendering_job.");
    }
    resource.fromJson(json);
    return resource;
  }
