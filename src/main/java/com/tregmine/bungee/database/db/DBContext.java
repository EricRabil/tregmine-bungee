package com.tregmine.bungee.database.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.tregmine.bungee.Tregmine;
import com.tregmine.bungee.database.IContext;

public class DBContext implements IContext {
	private Connection conn;
	private Tregmine plugin;

	public DBContext(Connection conn, Tregmine instance) {
		this.conn = conn;
		this.plugin = instance;
	}

	@Override
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public DBPlayerDAO getPlayerDAO() {
		return new DBPlayerDAO(this.plugin, this.conn);
	}

}
