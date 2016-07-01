package com.tregmine.bungee.listeners;

import com.tregmine.bungee.Tregmine;
import com.tregmine.bungee.api.TregminePlayer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisconnectListener implements Listener {

	private Tregmine x;

	public DisconnectListener(Tregmine tregmine) {
		this.x = tregmine;
	}

	@EventHandler
	public void onPlayerDisconnect(PlayerDisconnectEvent e) {
		TregminePlayer player = this.x.getPlayer(e.getPlayer());
		this.x.removePlayer(player);
		for(ProxiedPlayer pl : this.x.getProxy().getPlayers()){
			if(pl.getServer() != player.getServer()){
				pl.sendMessage(new TextComponent(player.getRank().getRankColor() + player.getName() + ChatColor.DARK_AQUA + " disconnected from " + player.getServer().getInfo().getName().toUpperCase()));
			}
		}
	}
}
