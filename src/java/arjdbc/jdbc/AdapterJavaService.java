/*
 **** BEGIN LICENSE BLOCK *****
 * Copyright (c) 2006-2010 Nick Sieger <nick@nicksieger.com>
 * Copyright (c) 2006-2007 Ola Bini <ola.bini@gmail.com>
 * Copyright (c) 2008-2009 Thomas E Enebo <enebo@acm.org>
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ***** END LICENSE BLOCK *****/

package arjdbc.jdbc;

import java.io.IOException;

import arjdbc.db2.DB2Module;
import arjdbc.db2.DB2RubyJdbcConnection;
import arjdbc.derby.DerbyModule;
import arjdbc.h2.H2RubyJdbcConnection;
import arjdbc.hsqldb.HSQLDBModule;
import arjdbc.informix.InformixRubyJdbcConnection;
import arjdbc.mssql.MSSQLModule;
import arjdbc.mssql.MSSQLRubyJdbcConnection;
import arjdbc.mysql.MySQLModule;
import arjdbc.mysql.MySQLRubyJdbcConnection;
import arjdbc.oracle.OracleModule;
import arjdbc.oracle.OracleRubyJdbcConnection;
import arjdbc.postgresql.PostgresqlRubyJdbcConnection;
import arjdbc.sqlite3.SQLite3Module;
import arjdbc.sqlite3.SQLite3RubyJdbcConnection;
import arjdbc.sybase.SybaseASERubyJdbcConnection;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.load.BasicLibraryService;

public class AdapterJavaService implements BasicLibraryService {

    public boolean basicLoad(final Ruby runtime) throws IOException {
        // ActiveRecord::ConnectionAdapter-s :
        RubyClass jdbcConnection = RubyJdbcConnection.createJdbcConnectionClass(runtime);
        PostgresqlRubyJdbcConnection.createPostgresqlJdbcConnectionClass(runtime, jdbcConnection);
        MSSQLRubyJdbcConnection.createMSSQLJdbcConnectionClass(runtime, jdbcConnection);
        InformixRubyJdbcConnection.createInformixJdbcConnectionClass(runtime, jdbcConnection);
        OracleRubyJdbcConnection.createOracleJdbcConnectionClass(runtime, jdbcConnection);
        SQLite3RubyJdbcConnection.createSQLite3JdbcConnectionClass(runtime, jdbcConnection);
        H2RubyJdbcConnection.createH2JdbcConnectionClass(runtime, jdbcConnection);
        MySQLRubyJdbcConnection.createMySQLJdbcConnectionClass(runtime, jdbcConnection);
        DB2RubyJdbcConnection.createDB2JdbcConnectionClass(runtime, jdbcConnection);
		SybaseASERubyJdbcConnection.createSybaseASEJdbcConnectionClass(runtime, jdbcConnection);
        // ArJdbc :
        RubyModule arJdbc = runtime.getOrCreateModule("ArJdbc");
        MySQLModule.load(arJdbc);
        MSSQLModule.load(arJdbc);
        DerbyModule.load(arJdbc);
        HSQLDBModule.load(arJdbc);
        SQLite3Module.load(arJdbc);
        OracleModule.load(arJdbc);
        DB2Module.load(arJdbc);
        return true;
    }
    
}
