package ch.jmildner.tools;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;


public final class MyPoolingDataSource
{

	private final DataSource dataSource;


	public MyPoolingDataSource(final String DATABASE)
	{
		this.dataSource = setupDataSource(MyDbTools.getUrl(DATABASE));
	}


	public DataSource getDataSource()
	{
		return dataSource;
	}


	private DataSource setupDataSource(String connectURI)
	{
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI, null);

		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);

		poolableConnectionFactory.setPool(connectionPool);

		PoolingDataSource<PoolableConnection> ds = new PoolingDataSource<>(connectionPool);

		return ds;
	}
}
