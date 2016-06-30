package com.tregmine.bungee.database;

public interface IContext extends AutoCloseable {
	@Override
	public void close();

	public IPlayerDAO getPlayerDAO();
}
