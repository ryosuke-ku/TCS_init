    JdbcIdAndVersionStream createJdbcIdAndVersionStream(ScrutineerCommandLineOptions options) {
        this.connection = initializeJdbcDriverAndConnection(options);
        return new JdbcIdAndVersionStream(connection, options.sql, idAndVersionFactory);
    }
