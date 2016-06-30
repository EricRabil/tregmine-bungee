package com.tregmine.bungee.listeners;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PingListener implements Listener{
	
	
	@EventHandler
	public void onProxyPing(ProxyPingEvent e){
		ServerPing ping = new ServerPing();
	}

}
