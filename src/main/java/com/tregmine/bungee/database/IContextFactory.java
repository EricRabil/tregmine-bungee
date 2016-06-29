package com.tregmine.bungee.database;

public interface IContextFactory {
	public IContext createContext() throws DAOException;
}
