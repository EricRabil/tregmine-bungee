package com.tregmine.bungee.api;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import com.tregmine.bungee.Tregmine;

import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;

public class TregminePlayer implements ProxiedPlayer {

	private Tregmine tregmine;

	private boolean banned;
	private Rank rank;

	private int id;

	private String name;
	private UUID uuid;

	private ProxiedPlayer delegate;

	private PlayerBan banData = null;

	public TregminePlayer(Tregmine t, ProxiedPlayer f, PlayerDataSource source) {
		this.banned = source.isBanned();
		this.banData = source.getBanData();
		this.rank = source.getRank();
		this.id = source.getID();
		this.delegate = f;
		this.uuid = this.getUniqueId();
		this.name = this.getName();
	}

	public boolean isBanned() {
		return this.banned;
	}

	public void setBanned(boolean x) {
		this.banned = x;
	}

	public Rank getRank() {
		return this.rank;
	}

	public int getID() {
		return this.id;
	}

	public void setID(int i) {
		this.id = i;
	}

	public void setBanData(PlayerBan data) {
		this.banData = data;
	}

	public PlayerBan getBanData() {
		return this.banData;
	}

	@Deprecated
	@Override
	public void disconnect(String reason) {
		this.delegate.disconnect(reason);
	}

	@Override
	public void disconnect(BaseComponent... reason) {
		this.delegate.disconnect(reason);
	}

	@Override
	public void disconnect(BaseComponent reason) {
		this.delegate.disconnect(reason);
	}

	@Override
	public InetSocketAddress getAddress() {
		return this.delegate.getAddress();
	}

	@Override
	public boolean isConnected() {
		return this.delegate.isConnected();
	}

	@Override
	public Unsafe unsafe() {
		return this.delegate.unsafe();
	}

	@Override
	public void addGroups(String... groups) {
		this.delegate.addGroups(groups);
	}

	@Override
	public Collection<String> getGroups() {
		return this.delegate.getGroups();
	}

	@Override
	public String getName() {
		return this.delegate.getName();
	}

	@Override
	public Collection<String> getPermissions() {
		return this.delegate.getPermissions();
	}

	@Override
	public boolean hasPermission(String permission) {
		return this.delegate.hasPermission(permission);
	}

	@Override
	public void removeGroups(String... groups) {
		this.delegate.removeGroups(groups);
	}

	@Override
	public void sendMessage(String message) {
		this.delegate.sendMessage(message);
	}

	@Override
	public void sendMessage(BaseComponent... message) {
		this.delegate.sendMessage(message);
	}

	@Override
	public void sendMessage(BaseComponent message) {
		this.delegate.sendMessage(message);
	}

	@Override
	public void sendMessages(String... messages) {
		this.delegate.sendMessages(messages);
	}

	@Override
	public void setPermission(String permission, boolean value) {
		this.delegate.setPermission(permission, value);
	}

	@Override
	public void chat(String message) {
		this.delegate.chat(message);
	}

	@Override
	public void connect(ServerInfo target) {
		this.delegate.connect(target);
	}

	@Override
	public void connect(ServerInfo target, Callback<Boolean> callback) {
		this.delegate.connect(target, callback);
	}

	@Override
	public String getDisplayName() {
		return this.delegate.getDisplayName();
	}

	@Override
	public Locale getLocale() {
		return this.delegate.getLocale();
	}

	@Override
	public Map<String, String> getModList() {
		return this.delegate.getModList();
	}

	@Override
	public PendingConnection getPendingConnection() {
		return this.delegate.getPendingConnection();
	}

	@Override
	public int getPing() {
		return this.delegate.getPing();
	}

	@Override
	public ServerInfo getReconnectServer() {
		return this.delegate.getReconnectServer();
	}

	@Override
	public Server getServer() {
		return this.delegate.getServer();
	}

	@Override
	public String getUUID() {
		return this.delegate.getUniqueId().toString();
	}

	@Override
	public UUID getUniqueId() {
		return this.delegate.getUniqueId();
	}

	@Override
	public boolean isForgeUser() {
		return this.delegate.isForgeUser();
	}

	@Override
	public void resetTabHeader() {
		this.delegate.resetTabHeader();
	}

	@Override
	public void sendData(String channel, byte[] data) {
		this.delegate.sendData(channel, data);
	}

	@Override
	public void sendMessage(ChatMessageType position, BaseComponent... message) {
		this.delegate.sendMessage(position, message);
	}

	@Override
	public void sendMessage(ChatMessageType position, BaseComponent message) {
		this.delegate.sendMessage(position, message);
	}

	@Override
	public void sendTitle(Title title) {
		this.delegate.sendTitle(title);
	}

	@Override
	public void setDisplayName(String name) {
		this.delegate.setDisplayName(name);
	}

	@Override
	public void setReconnectServer(ServerInfo server) {
		this.delegate.setReconnectServer(server);
	}

	@Override
	public void setTabHeader(BaseComponent header, BaseComponent footer) {
		this.delegate.setTabHeader(header, footer);
	}

	@Override
	public void setTabHeader(BaseComponent[] header, BaseComponent[] footer) {
		this.delegate.setTabHeader(header, footer);
	}

}
