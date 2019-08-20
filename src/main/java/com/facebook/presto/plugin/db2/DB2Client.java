/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.plugin.db2;

import com.facebook.presto.plugin.jdbc.BaseJdbcClient;
import com.facebook.presto.plugin.jdbc.BaseJdbcConfig;
import com.facebook.presto.plugin.jdbc.ConnectionFactory;
import com.facebook.presto.plugin.jdbc.DriverConnectionFactory;
import com.facebook.presto.plugin.jdbc.JdbcConnectorId;
import com.facebook.presto.plugin.jdbc.JdbcSplit;
import com.ibm.db2.jcc.DB2Driver;
import io.airlift.log.Logger;

import javax.inject.Inject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import static com.facebook.presto.plugin.jdbc.DriverConnectionFactory.basicConnectionProperties;

public class DB2Client extends BaseJdbcClient
{
    private static final Logger log = Logger.get(DB2Client.class);

    @Inject
    public DB2Client(JdbcConnectorId connectorId, BaseJdbcConfig config, DB2Config db2Config) throws SQLException
    {
        // super(connectorId, db2Config, "", new DB2Driver());
        super(connectorId, config, "", connectionFactory(config, db2Config));
        // https://www-01.ibm.com/support/knowledgecenter/ssw_ibm_i_72/rzaha/conprop.htm
        // block size (aka fetch size), default 32
        // Properties connectionProperties = basicConnectionProperties(config);
        // connectionProperties.setProperty("block size", "512");

        // return new DriverConnectionFactory(new DB2Driver(), config.getConnectionUrl(), connectionProperties);
    }

    private static ConnectionFactory connectionFactory(BaseJdbcConfig config, DB2Config db2Config)
            throws SQLException
    {
        Properties connectionProperties = basicConnectionProperties(config);
        connectionProperties.setProperty("block size", "512");

        return new DriverConnectionFactory(new DB2Driver(), config.getConnectionUrl(), connectionProperties);
    }
}
