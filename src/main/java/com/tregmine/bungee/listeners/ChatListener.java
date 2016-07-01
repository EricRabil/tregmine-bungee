package com.tregmine.bungee.listeners;

import com.tregmine.bungee.Tregmine;
import com.tregmine.bungee.api.TregminePlayer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener{
	
	private Tregmine t;
	
	public ChatListener(Tregmine tregmine){
		this.t = tregmine;
	}
	
	@EventHandler
	public void onChat(ChatEvent e){
		if(e.isCommand()){
			return;
		}
		if(e.getSender() instanceof ProxiedPlayer){
			TregminePlayer player = this.t.getPlayer((ProxiedPlayer) e.getSender());
			e.setCancelled(true);
			if(!player.getServer().getInfo().getName().trim().toLowerCase().contains("tregmine")){
				//No DSV reception, have a player on the Tregmine server [if any] relay to DiscordSRV
			}
			for(ProxiedPlayer pl : this.t.getProxy().getPlayers()){
				String message;
				if(player.getRank().canUseChatColors()){
					message = ChatColor.translateAlternateColorCodes('#', e.getMessage());
				}else{
					message = e.getMessage();
				}
				TextComponent msg = new TextComponent(ChatColor.WHITE + ": " + message);
				TextComponent begin = null;
				if(player.getServer() != pl.getServer()){
					begin = new TextComponent(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + player.getServer().getInfo().getName().toUpperCase() + ChatColor.GRAY + "] ");
				}else{
					
				}
				
				TextComponent name = new TextComponent(player.getRank().getRankColor() + player.getName());
				name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(player.getRank().getName()).create()));
				if(begin != null){
					pl.sendMessage(begin, name, msg);
				}else{
					pl.sendMessage(name, msg);
				}
			}
		}
	}
	
}
