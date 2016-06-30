package com.tregmine.bungee.database.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import com.tregmine.bungee.Tregmine;
import com.tregmine.bungee.database.DAOException;
import com.tregmine.bungee.database.IContext;
import com.tregmine.bungee.database.IContextFactory;

import net.md_5.bungee.config.Configuration;

public class DBContextFactory implements IContextFactory {
	private BasicDataSource ds;
	private Map<String, LoggingConnection.LogEntry> queryLog;
	private Tregmine plugin;

	public DBContextFactory(Configuration config, Tregmine instance) {
		queryLog = new HashMap<>();

		// String driver = config.getString("db.driver");
		// if (driver == null) {
		// driver = "com.mysql.jdbc.Driver";
		// }

		// try {
		// Class.forName(com.mysql.jdbc.Driver.class.to).newInstance();
		// } catch (ClassNotFoundException ex) {
		// throw new RuntimeException(ex);
		// } catch (IllegalAccessException ex) {
		// throw new RuntimeException(ex);
		// } catch (InstantiationException ex) {
		// throw new RuntimeException(ex);
		// }

		String user = config.getString("db.user");
		String password = config.getString("db.password");
		String url = config.getString("db.url");

		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setMaxActive(5);
		ds.setMaxIdle(5);
		ds.setDefaultAutoCommit(true);
		System.out.println("Called! 1");

		this.plugin = instance;
	}

	@Override
	public IContext createContext() throws DAOException {
		try {
			// It's the responsibility of the context to make sure that the
			// connection is correctly closed
			Connection conn = ds.getConnection();
			try (Statement stmt = conn.createStatement()) {
				stmt.execute("SET NAMES latin1");
			}

			return new DBContext(new LoggingConnection(conn, queryLog), plugin);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public Map<String, LoggingConnection.LogEntry> getLog() {
		return queryLog;
	}
}
