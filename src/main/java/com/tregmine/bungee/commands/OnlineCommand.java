package com.tregmine.bungee.commands;

import com.tregmine.bungee.Tregmine;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class OnlineCommand extends Command {

	private Tregmine x;

	public OnlineCommand(Tregmine tregmine) {
		super("online");
		this.x = tregmine;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

	}

}
