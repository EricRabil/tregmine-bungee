package com.tregmine.bungee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.google.common.io.ByteStreams;
import com.tregmine.bungee.api.PlayerDataSource;
import com.tregmine.bungee.api.TregminePlayer;
import com.tregmine.bungee.commands.BroadcastCommand;
import com.tregmine.bungee.database.DAOException;
import com.tregmine.bungee.database.IContext;
import com.tregmine.bungee.database.IContextFactory;
import com.tregmine.bungee.database.IPlayerDAO;
import com.tregmine.bungee.listeners.ChatListener;
import com.tregmine.bungee.listeners.DisconnectListener;
import com.tregmine.bungee.listeners.LoginListener;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Tregmine extends Plugin {

	private HashMap<UUID, TregminePlayer> players = new HashMap<UUID, TregminePlayer>();

	private ArrayList<TregminePlayer> onlinePlayers = new ArrayList<TregminePlayer>();

	private Configuration config;

	private IContextFactory contextFactory;

	public final String disconnectTL = ChatColor.RED + "You have been disconnected from the Tregmine Network.\n";
	
	@Override
	public void onEnable() {
		if (!getDataFolder().exists()) {
			getLogger().info("Data folder was missing, generating.");
			getDataFolder().mkdir();
		}
		File configFile = new File(getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				try (InputStream is = getResourceAsStream("example_config.yml");
						OutputStream os = new FileOutputStream(configFile)) {
					ByteStreams.copy(is, os);
				}
			} catch (IOException e) {
				throw new RuntimeException("Unable to create configuration file", e);
			}
		}

		try {
			this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
		} catch (IOException e) {
			e.printStackTrace();
			getLogger().severe("Couldn't load configuration file, disabling.");
			this.onDisable();
		}
		
		PluginManager plmgm = getProxy().getPluginManager();

		getProxy().getPluginManager().registerListener(this, new LoginListener(this));
		plmgm.registerListener(this, new ChatListener(this));
		plmgm.registerListener(this, new DisconnectListener(this));

		getProxy().getPluginManager().registerCommand(this, new BroadcastCommand(this));

		contextFactory = new com.tregmine.bungee.database.db.DBContextFactory(config, this);
	}

	public TregminePlayer getPlayer(ProxiedPlayer player) {
		TregminePlayer cached = players.get(player.getUniqueId().toString());
		if (cached != null) {
			return cached;
		}
		try (IContext ctx = contextFactory.createContext()) {
			IPlayerDAO playerdao = ctx.getPlayerDAO();
			PlayerDataSource source = playerdao.buildPlayer(player.getUniqueId());
			return new TregminePlayer(this, player, source);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addPlayer(TregminePlayer player){
		if(!this.players.containsKey(player.getUniqueId())){
			this.players.put(player.getUniqueId(), player);
		}
		this.onlinePlayers.add(player);
	}
	
	public void removePlayer(TregminePlayer player){
		this.onlinePlayers.remove(player);
	}

	public IContext createContext() throws DAOException {
		return contextFactory.createContext();
	}

	public IContextFactory getContextFactory() {
		return contextFactory;
	}

	public ArrayList<TregminePlayer> getOnlinePlayers() {
		return this.onlinePlayers;
	}
}
