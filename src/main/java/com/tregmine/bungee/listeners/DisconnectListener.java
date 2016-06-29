package com.tregmine.bungee.listeners;

import com.tregmine.bungee.Tregmine;
import com.tregmine.bungee.api.TregminePlayer;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisconnectListener implements Listener{
	
	private Tregmine x;
	
	public DisconnectListener(Tregmine tregmine){
		this.x = tregmine;
	}
	
	@EventHandler
	public void onPlayerDisconnect(PlayerDisconnectEvent e){
		TregminePlayer player = this.x.getPlayer(e.getPlayer());
		this.x.getOnlinePlayers().remove(player);
	}
}
