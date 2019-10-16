	public String getTableClassName(String tableName)
	{
		return StringUtils.toString(config.getTableClassPrefix(), "")
			+ deriveClassName(tableName)
			+ StringUtils.toString(config.getTableClassSuffix(),"");
	}
tion e) 
        {
            throw new RuntimeException("Unable to read database metadata: " + e.getMessage(), e);
        }
        finally 
        {
            DBUtil.close(con, log);
        }
        return db;
	}
