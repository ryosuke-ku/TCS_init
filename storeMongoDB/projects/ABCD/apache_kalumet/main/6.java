    public static Kalumet digeste( String path )
        throws KalumetException
    {
        if ( !path.startsWith( "http:" ) && !path.startsWith( "HTTP:" ) && !path.startsWith( "file:" )
            && !path.startsWith( "FILE:" ) )
        {
            path = "file:" + path;
        }
        Kalumet kalumet = null;
        try
        {
            lock.readLock().lock();

            // init the digester with no validation on the XML file (no DTD)
            Digester digester = new Digester();
            digester.setValidating( false );

            // kalumet tag rules
            digester.addObjectCreate( "kalumet", "org.apache.kalumet.model.Kalumet" );
            digester.addSetProperties( "kalumet" );

            // properties/property tag rules
            digester.addObjectCreate( "kalumet/properties/property", "org.apache.kalumet.model.Property" );
            digester.addSetProperties( "kalumet/properties/property" );

            // add property in the kalumet tag rule
            digester.addSetNext( "kalumet/properties/property", "addProperty", "org.apache.kalumet.model.Property" );

            // security tag rules
            digester.addObjectCreate( "kalumet/security", "org.apache.kalumet.model.Security" );
            digester.addSetProperties( "kalumet/security" );

            // user tag rules
            digester.addObjectCreate( "kalumet/security/users/user", "org.apache.kalumet.model.User" );
            digester.addSetProperties( "kalumet/security/users/user" );

            // add user to security tag rule
            digester.addSetNext( "kalumet/security/users/user", "addUser", "org.apache.kalumet.model.User" );

            // group tag rules
            digester.addObjectCreate( "kalumet/security/groups/group", "org.apache.kalumet.model.Group" );
            digester.addSetProperties( "kalumet/security/groups/group" );

            // user group tag rules
            digester.addObjectCreate( "kalumet/security/groups/group/users/user", "org.apache.kalumet.model.User" );
            digester.addSetProperties( "kalumet/security/groups/group/users/user" );

            // add user in group tag rule
            digester.addSetNext( "kalumet/security/groups/group/users/user", "addUser",
                                 "org.apache.kalumet.model.User" );

            // add group to security tag rule
            digester.addSetNext( "kalumet/security/groups/group", "addGroup", "org.apache.kalumet.model.Group" );

            // add security to kalumet tag rule
            digester.addSetNext( "kalumet/security", "setSecurity", "org.apache.kalumet.model.Security" );

            // agent tag rules
            digester.addObjectCreate( "kalumet/agents/agent", "org.apache.kalumet.model.Agent" );
            digester.addSetProperties( "kalumet/agents/agent" );

            // add agent to kalumet tag rule
            digester.addSetNext( "kalumet/agents/agent", "addAgent", "org.apache.kalumet.model.Agent" );

            // environment tag rules
            digester.addObjectCreate( "kalumet/environments/environment", "org.apache.kalumet.model.Environment" );
            digester.addSetProperties( "kalumet/environments/environment" );

            // variables tag rules
            digester.addObjectCreate( "kalumet/environments/environment/variables/variable",
                                      "org.apache.kalumet.model.Variable" );
            digester.addSetProperties( "kalumet/environments/environment/variables/variable" );

            // add variable to environment tag rule
            digester.addSetNext( "kalumet/environments/environment/variables/variable", "addVariable",
                                 "org.apache.kalumet.model.Variable" );

            // freefield tag rules
            digester.addObjectCreate( "kalumet/environments/environment/freefields/freefield",
                                      "org.apache.kalumet.model.FreeField" );
            digester.addSetProperties( "kalumet/environments/environment/freefields/freefield" );
            // add freefield content
            digester.addCallMethod( "kalumet/environments/environment/freefields/freefield", "setContent", 0 );

            // add freefield to environment tag rule
            digester.addSetNext( "kalumet/environments/environment/freefields/freefield", "addFreeField",
                                 "org.apache.kalumet.model.FreeField" );

            // access tag rules
            digester.addObjectCreate( "kalumet/environments/environment/accesses/access",
                                      "org.apache.kalumet.model.Access" );
            digester.addSetProperties( "kalumet/environments/environment/accesses/access" );

            // access properties rules
            digester.addObjectCreate( "kalumet/environments/environment/accesses/access/properties/property",
                                      "org.apache.kalumet.model.Property" );
            digester.addSetProperties( "kalumet/environments/environment/accesses/access/properties/property" );

            // add property in access tag rule
            digester.addSetNext( "kalumet/environments/environment/accesses/access/properties/property", "addProperty",
                                 "org.apache.kalumet.model.Property" );

            // add access to environment tag rule
            digester.addSetNext( "kalumet/environments/environment/accesses/access", "addAccess",
                                 "org.apache.kalumet.model.Access" );

            // environment notes and weblinks tag rules
            digester.addCallMethod( "kalumet/environments/environment/notes", "setNotes", 0 );
            digester.addCallMethod( "kalumet/environments/environment/weblinks", "setWeblinks", 0 );

            // jeeapplicationservers tag rules
            digester.addObjectCreate( "kalumet/environments/environment/jeeapplicationservers",
                                      "org.apache.kalumet.model.JEEApplicationServers" );
            digester.addSetProperties( "kalumet/environments/environment/jeeapplicationservers" );

            // jeeapplicationserver tag rules
            digester.addObjectCreate( "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver",
                                      "org.apache.kalumet.model.JEEApplicationServer" );
            digester.addSetProperties( "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver" );

            // jeeapplicationserver startupcommand and shutdowncommand tag rules
            digester.addCallMethod(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/startupcommand",
                "setStartupcommand", 0 );
            digester.addCallMethod(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/shutdowncommand",
                "setShutdowncommand", 0 );

            // jdbcconnectionpool tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jdbcconnectionpools/jdbcconnectionpool",
                "org.apache.kalumet.model.JDBCConnectionPool" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jdbcconnectionpools/jdbcconnectionpool" );

            // add jdbcconnectionpool to jeeapplicationserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jdbcconnectionpools/jdbcconnectionpool",
                "addJDBCConnectionPool", "org.apache.kalumet.model.JDBCConnectionPool" );

            // jdbcdatasource tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jdbcdatasources/jdbcdatasource",
                "org.apache.kalumet.model.JDBCDataSource" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jdbcdatasources/jdbcdatasource" );

            // add jdbcdatasource to jeeapplicationserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jdbcdatasources/jdbcdatasource",
                "addJDBCDataSource", "org.apache.kalumet.model.JDBCDataSource" );

            // jmsconnectionfactory tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsconnectionfactories/jmsconnectionfactory",
                "org.apache.kalumet.model.JMSConnectionFactory" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsconnectionfactories/jmsconnectionfactory" );

            // add jmsconnectionfactory to jeeapplicationserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsconnectionfactories/jmsconnectionfactory",
                "addJMSConnectionFactory", "org.apache.kalumet.model.JMSConnectionFactory" );

            // jmsserver tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsservers/jmsserver",
                "org.apache.kalumet.model.JMSServer" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsservers/jmsserver" );

            // jmsqueue tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsservers/jmsserver/jmsqueues/jmsqueue",
                "org.apache.kalumet.model.JMSQueue" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsservers/jmsserver/jmsqueues/jmsqueue" );

            // add jmsqueue to jmsserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsservers/jmsserver/jmsqueues/jmsqueue",
                "addJMSQueue", "org.apache.kalumet.model.JMSQueue" );

            // jmstopic tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsservers/jmsserver/jmstopics/jmstopic",
                "org.apache.kalumet.model.JMSTopic" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsservers/jmsserver/jmstopics/jmstopic" );

            // add jmstopic to jmsserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsservers/jmsserver/jmstopics/jmstopic",
                "addJMSTopic", "org.apache.kalumet.model.JMSTopic" );

            // add jmsserver to jeeapplicationserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jmsservers/jmsserver",
                "addJMSServer", "org.apache.kalumet.model.JMSServer" );

            // jndibinding tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jndibindings/jndibinding",
                "org.apache.kalumet.model.JNDIBinding" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jndibindings/jndibinding" );

            // add jndibinding to jeeapplicationserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jndibindings/jndibinding",
                "addJNDIBinding", "org.apache.kalumet.model.JNDIBinding" );

            // sharedlibrary tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/sharedlibrairies/sharedlibrary",
                "org.apache.kalumet.model.SharedLibrary" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/sharedlibrairies/sharedlibrary" );

            // add sharedlibrary to jeeapplicationserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/sharedlibrairies/sharedlibrary",
                "addSharedLibrary", "org.apache.kalumet.model.SharedLibrary" );

            // application tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication",
                "org.apache.kalumet.model.JEEApplication" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication" );

            // archive tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/archives/archive",
                "org.apache.kalumet.model.Archive" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/archives/archive" );

            // add archive archive to application
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/archives/archive",
                "addArchive", "org.apache.kalumet.model.Archive" );

            // contentmanager tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/contentmanagers/contentmanager",
                "org.apache.kalumet.model.ContentManager" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/contentmanagers/contentmanager" );

            // contentmanager property tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/contentmanagers/contentmanager/properties/property",
                "org.apache.kalumet.model.Property" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/contentmanagers/contentmanager/properties/property" );

            // add property in contentmanager
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/contentmanagers/contentmanager/properties/property",
                "addProperty", "org.apache.kalumet.model.Property" );

            // add contentmanager to application
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/contentmanagers/contentmanager",
                "addContentManager", "org.apache.kalumet.model.ContentManager" );

            // configurationfile tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/configurationfiles/configurationfile",
                "org.apache.kalumet.model.ConfigurationFile" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/configurationfiles/configurationfile" );

            // mapping tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/configurationfiles/configurationfile/mappings/mapping",
                "org.apache.kalumet.model.Mapping" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/configurationfiles/configurationfile/mappings/mapping" );

            // add mapping to configurationfile
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/configurationfiles/configurationfile/mappings/mapping",
                "addMapping", "org.apache.kalumet.model.Mapping" );

            // add configurationfile to application
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/configurationfiles/configurationfile",
                "addConfigurationFile", "org.apache.kalumet.model.ConfigurationFile" );

            // database tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/databases/database",
                "org.apache.kalumet.model.Database" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/databases/database" );

            // sqlscript tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/databases/database/sqlscripts/sqlscript",
                "org.apache.kalumet.model.SqlScript" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/databases/database/sqlscripts/sqlscript" );

            // sqlscript mapping tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/databases/database/sqlscripts/sqlscript/mappings/mapping",
                "org.apache.kalumet.model.Mapping" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/databases/database/sqlscripts/sqlscript/mappings/mapping" );

            // add mapping to sqlscript
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/databases/database/sqlscripts/sqlscript/mappings/mapping",
                "addMapping", "org.apache.kalumet.model.Mapping" );

            // add sqlscript to database
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/databases/database/sqlscripts/sqlscript",
                "addSqlScript", "org.apache.kalumet.model.SqlScript" );

            // add database to application
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/jeeapplication/databases/database",
                "addDatabase", "org.apache.kalumet.model.Database" );

            // add application to applicationserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/jeeapplications/application",
                "addApplication", "org.apache.kalumet.model.JEEApplication" );

            // cache tag rules
            digester.addObjectCreate(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/caches/cache",
                "org.apache.kalumet.model.Cache" );
            digester.addSetProperties(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/caches/cache" );

            // add cache to applicationserver
            digester.addSetNext(
                "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver/caches/cache", "addCache",
                "org.apache.kalumet.model.Cache" );

            // add applicationserver to applicationservers tag rule
            digester.addSetNext( "kalumet/environments/environment/jeeapplicationservers/jeeapplicationserver",
                                 "addJEEApplicationServer", "org.apache.kalumet.model.JEEApplicationServer" );

            // add applicationservers to environment tag rule
            digester.addSetNext( "kalumet/environments/environment/jeeapplicationservers", "setJEEApplicationServers",
                                 "org.apache.kalumet.model.JEEApplicationServers" );

            // logfile tag rules
            digester.addObjectCreate( "kalumet/environments/environment/logfiles/logfile",
                                      "org.apache.kalumet.model.LogFile" );
            digester.addSetProperties( "kalumet/environments/environment/logfiles/logfile" );

            // add logfile to environment tag rule
            digester.addSetNext( "kalumet/environments/environment/logfiles/logfile", "addLogFile",
                                 "org.apache.kalumet.model.LogFile" );

            // software tag rules
            digester.addObjectCreate( "kalumet/environments/environment/softwares/software",
                                      "org.apache.kalumet.model.Software" );
            digester.addSetProperties( "kalumet/environments/environment/softwares/software" );

            // software update plan command item
            digester.addObjectCreate( "kalumet/environments/environment/softwares/software/updateplan/command",
                                      "org.apache.kalumet.model.Command" );
            digester.addSetProperties( "kalumet/environments/environment/softwares/software/updateplan/command" );
            digester.addCallMethod( "kalumet/environments/environment/softwares/software/updateplan/command",
                                    "setCommand", 0 );
            digester.addSetNext( "kalumet/environments/environment/softwares/software/updateplan/command", "addCommand",
                                 "org.apache.kalumet.model.Command" );

            // software update plan location item
            digester.addObjectCreate( "kalumet/environments/environment/softwares/software/updateplan/location",
                                      "org.apache.kalumet.model.Location" );
            digester.addSetProperties( "kalumet/environments/environment/softwares/software/updateplan/location" );
            digester.addSetNext( "kalumet/environments/environment/softwares/software/updateplan/location",
                                 "addLocation", "org.apache.kalumet.model.Location" );

            // software update plan configuration file item
            digester.addObjectCreate(
                "kalumet/environments/environment/softwares/software/updateplan/configurationfile",
                "org.apache.kalumet.model.ConfigurationFile" );
            digester.addSetProperties(
                "kalumet/environments/environment/softwares/software/updateplan/configurationfile" );
            digester.addObjectCreate(
                "kalumet/environments/environment/softwares/software/updateplan/configurationfile/mappings/mapping",
                "org.apache.kalumet.model.Mapping" );
            digester.addSetProperties(
                "kalumet/environments/environment/softwares/software/updateplan/configurationfile/mappings/mapping" );
            digester.addSetNext(
                "kalumet/environments/environment/softwares/software/updateplan/configurationfile/mappings/mapping",
                "addMapping", "org.apache.kalumet.model.Mapping" );
            digester.addSetNext( "kalumet/environments/environment/softwares/software/updateplan/configurationfile",
                                 "addConfigurationFile", "org.apache.kalumet.model.ConfigurationFile" );

            // software update plan database item
            digester.addObjectCreate( "kalumet/environments/environment/softwares/software/updateplan/database",
                                      "org.apache.kalumet.model.Database" );
            digester.addSetProperties( "kalumet/environments/environment/softwares/software/updateplan/database" );
            digester.addObjectCreate(
                "kalumet/environments/environment/softwares/software/updateplan/database/sqlscripts/sqlscript",
                "org.apache.kalumet.model.SqlScript" );
            digester.addSetProperties(
                "kalumet/environments/environment/softwares/software/updateplan/database/sqlscripts/sqlscript" );
            digester.addObjectCreate(
                "kalumet/environments/environment/softwares/software/updateplan/database/sqlscripts/sqlscript/mappings/mapping",
                "org.apache.kalumet.model.Mapping" );
            digester.addSetProperties(
                "kalumet/environments/environment/softwares/software/updateplan/database/sqlscripts/sqlscript/mappings/mapping" );
            digester.addSetNext(
                "kalumet/environments/environment/softwares/software/updateplan/database/sqlscripts/sqlscript/mappings/mapping",
                "addMapping", "org.apache.kalumet.model.Mapping" );
            digester.addSetNext(
                "kalumet/environments/environment/softwares/software/updateplan/database/sqlscripts/sqlscript",
                "addSqlScript", "org.apache.kalumet.model.SqlScript" );
            digester.addSetNext( "kalumet/environments/environment/softwares/software/updateplan/database",
                                 "addDatabase", "org.apache.kalumet.model.Database" );

            // add software to environment
            digester.addSetNext( "kalumet/environments/environment/softwares/software", "addSoftware",
                                 "org.apache.kalumet.model.Software" );

            // notifiers tag rules
            digester.addObjectCreate( "kalumet/environments/environment/notifiers",
                                      "org.apache.kalumet.model.Notifiers" );
            digester.addSetProperties( "kalumet/environments/environment/notifiers" );

            // email tag rules
            digester.addObjectCreate( "kalumet/environments/environment/notifiers/email",
                                      "org.apache.kalumet.model.Email" );
            digester.addSetProperties( "kalumet/environments/environment/notifiers/email" );

            // destination tag rules
            digester.addObjectCreate( "kalumet/environments/environment/notifiers/email/destinations/destination",
                                      "org.apache.kalumet.model.Destination" );
            digester.addSetProperties( "kalumet/environments/environment/notifiers/email/destinations/destination" );

            // add destination to email notifier
            digester.addSetNext( "kalumet/environments/environment/notifiers/email/destinations/destination",
                                 "addDestination", "org.apache.kalumet.model.Destination" );

            // add email to notifiers
            digester.addSetNext( "kalumet/environments/environment/notifiers/email", "addNotifier",
                                 "org.apache.kalumet.model.Email" );

            // add notifiers to environment
            digester.addSetNext( "kalumet/environments/environment/notifiers", "setNotifiers",
                                 "org.apache.kalumet.model.Notifiers" );

            // email publisher tag rules
            digester.addObjectCreate( "kalumet/environments/environment/publishers/email",
                                      "org.apache.kalumet.model.Email" );
            digester.addSetProperties( "kalumet/environments/environment/publishers/email" );

            // destination email publisher tag rules
            digester.addObjectCreate( "kalumet/environments/environment/publishers/email/destinations/destination",
                                      "org.apache.kalumet.model.Destination" );
            digester.addSetProperties( "kalumet/environments/environment/publishers/email/destinations/destination" );

            // add destination to email publisher
            digester.addSetNext( "kalumet/environments/environment/publishers/email/destinations/destination",
                                 "addDestination", "org.apache.kalumet.model.Destination" );

            // add email publisher to environment
            digester.addSetNext( "kalumet/environments/environment/publishers/email", "addPublisher",
                                 "org.apache.kalumet.model.Email" );

            // statistics tag rules
            digester.addObjectCreate( "kalumet/environments/environment/statistics",
                                      "org.apache.kalumet.model.Statistics" );
            digester.addSetProperties( "kalumet/environments/environment/statistics" );

            // add statistics to environment
            digester.addSetNext( "kalumet/environments/environment/statistics", "setStatistics",
                                 "org.apache.kalumet.model.Statistics" );

            // add environment to kalumet tag rule
            digester.addSetNext( "kalumet/environments/environment", "addEnvironment",
                                 "org.apache.kalumet.model.Environment" );

            // parse the XML file
            kalumet = (Kalumet) digester.parse( path );
        }
        catch ( Exception e )
        {
            throw new KalumetException( "Can't read Kalumet configuration.", e );
        }
        finally
        {
            lock.readLock().unlock();
        }
        return kalumet;
    }
