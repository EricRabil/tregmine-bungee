package com.tregmine.bungee.commands;

import com.tregmine.bungee.Tregmine;
import com.tregmine.bungee.api.TregminePlayer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BroadcastCommand extends Command {
	private Tregmine tregmine;
	private TregminePlayer player;

	public BroadcastCommand(Tregmine t) {
		super("broadcast");
		this.tregmine = t;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			this.player = this.tregmine.getPlayer((ProxiedPlayer) sender);
		} else {
			return;
		}
		if (!this.player.getRank().canBroadcast()) {
			player.sendMessage(new TextComponent(ChatColor.RED + "You don't have permission to broadcast"));
		}
		try {
			String message = ChatColor.translateAlternateColorCodes('#', args[0]);
			TextComponent name = new TextComponent(
					"[" + player.getRank().getRankColor() + player.getDisplayName() + ChatColor.RESET + "] ");
			name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(player.getRank().getName()).create()));
			TextComponent component = new TextComponent(message);
			this.tregmine.getProxy().broadcast(name, component);
		} catch (ArrayIndexOutOfBoundsException e) {
			this.player.sendMessage(new TextComponent(ChatColor.RED + "Please have at least one character"));
		}
	}
}
