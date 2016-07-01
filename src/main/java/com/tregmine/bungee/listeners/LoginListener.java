package com.tregmine.bungee.listeners;

import java.util.Date;

import com.tregmine.bungee.Tregmine;
import com.tregmine.bungee.api.PlayerBan;
import com.tregmine.bungee.api.TregminePlayer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

	private Tregmine x;

	public LoginListener(Tregmine tregmine) {
		this.x = tregmine;
	}

	@EventHandler
	public void onLogin(PostLoginEvent e) {
		TregminePlayer player = x.getPlayer(e.getPlayer());
		x.getLogger().info("Player with UUID " + player.getUniqueId().toString() + " and rank "
				+ player.getRank().toString() + " connected. '" + player.getID() + "'");
		if (player.isBanned()) {
			x.getLogger().info(e.getPlayer().getUniqueId().toString() + " was disconnected because they are banned.");
			PlayerBan ban = player.getBanData();
			String expiration;
			if (ban.neverExpires()) {
				Date validuntil = null;
				expiration = "The End of Time";
			} else {
				Date validuntil = new Date(player.getBanData().getValidUntil() * 1000l);
				expiration = validuntil.getMonth() + "/" + validuntil.getDay() + "/" + validuntil.getYear();
			}
			Date issued = new Date(player.getBanData().getTimestamp() * 1000l);
			String length = issued.getMonth() + "/" + issued.getDay() + "/" + issued.getYear() + " - " + expiration;
			TextComponent component = new TextComponent(ChatColor.RED + "You are banned from the Tregmine Network.\n"
					+ "Message: " + ChatColor.GOLD + player.getBanData().getReportMessage() + "\n" + ChatColor.RED
					+ "Your case ID is " + player.getBanData().getReportID());
			e.getPlayer().disconnect(component);
			return;
		}
		this.x.addPlayer(player);
		for(ProxiedPlayer pl : this.x.getProxy().getPlayers()){
			if(pl.getServer() != player.getServer()){
				pl.sendMessage(new TextComponent(player.getRank().getRankColor() + player.getName() + ChatColor.DARK_AQUA + " connected to " + player.getServer().getInfo().getName().toUpperCase()));
			}
		}
		// x.getLogger().info(e.getPlayer().getUniqueId().toString() + " is
		// banned and won't be allowed to connect.");
		// TextComponent component = new TextComponent(ChatColor.RED + "You are
		// banned from the Tregmine Network.\nPlease contact the Tregmine admins
		// through the Trgemine website\nfor more support.");
		// e.getPlayer().disconnect(component);
	}

}
