package com.tregmine.bungee.listeners;

import com.tregmine.bungee.Tregmine;
import com.tregmine.bungee.api.TregminePlayer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener{
	
	private Tregmine x;
	
	public LoginListener(Tregmine tregmine){
		this.x = tregmine;
	}
	
	@EventHandler
	public void onLogin(PostLoginEvent e){
		TregminePlayer player = x.getPlayer(e.getPlayer());
		x.getLogger().info("Player with UUID " + player.getUniqueId().toString() + " and rank " + player.getRank().toString() + " connected.");
		if(player.isBanned()){
			x.getLogger().info(e.getPlayer().getUniqueId().toString() + " is banned and won't be allowed to connect.");
			TextComponent component = new TextComponent(this.x.disconnectTL + ChatColor.GOLD + "Your ");
			e.getPlayer().disconnect(component);
		}
		this.x.getOnlinePlayers().add(player);
		//x.getLogger().info(e.getPlayer().getUniqueId().toString() + " is banned and won't be allowed to connect.");
		//TextComponent component = new TextComponent(ChatColor.RED + "You are banned from the Tregmine Network.\nPlease contact the Tregmine admins through the Trgemine website\nfor more support.");
		//e.getPlayer().disconnect(component);
	}
	
}
