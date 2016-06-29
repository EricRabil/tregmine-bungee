package com.tregmine.bungee.database;

import java.util.UUID;

import com.tregmine.bungee.api.PlayerDataSource;
import com.tregmine.bungee.api.TregminePlayer;

public interface IPlayerDAO {
	public PlayerDataSource buildPlayer(UUID uuid) throws DAOException;
}
