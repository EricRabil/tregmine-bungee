package com.tregmine.bungee.database;

import java.util.UUID;

import com.tregmine.bungee.api.PlayerDataSource;

public interface IPlayerDAO {
	public PlayerDataSource buildPlayer(UUID uuid) throws DAOException;
}
