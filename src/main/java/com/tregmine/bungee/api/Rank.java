package com.tregmine.bungee.api;

import net.md_5.bungee.api.ChatColor;

public enum Rank {
	UNVERIFIED(ChatColor.WHITE), TOURIST(ChatColor.WHITE), SETTLER(ChatColor.GREEN), RESIDENT(ChatColor.DARK_GREEN), DONATOR(ChatColor.GOLD), GUARDIAN(ChatColor.BLUE), CODER(ChatColor.DARK_PURPLE), BUILDER(ChatColor.YELLOW), JUNIOR_ADMIN(ChatColor.RED), SENIOR_ADMIN(ChatColor.DARK_RED);
	
	public static Rank fromString(String value) {
		for (Rank rank : Rank.values()) {
			if (value.equalsIgnoreCase(rank.toString())) {
				return rank;
			}
		}

		return null;
	}
	
	private ChatColor rankcolor = ChatColor.WHITE;
	
	private Rank(ChatColor color){
		this.rankcolor = color;
	}
	
	public boolean canUseVanillaServer(){
		return this != UNVERIFIED && this != TOURIST && this != SETTLER;
	}
	
	public boolean canBroadcast(){
		return this == JUNIOR_ADMIN || this == SENIOR_ADMIN;
	}
	
	public String getName(){
		if (this == UNVERIFIED) {
			return rankcolor + "Unverified";
		} else if (this == TOURIST) {
			return rankcolor + "Tourist";
		} else if (this == SETTLER) {
			return rankcolor + "Settler";
		} else if (this == RESIDENT) {
			return rankcolor + "Resident";
		} else if (this == DONATOR) {
			return rankcolor + "Donator";
		} else if (this == GUARDIAN) {
			return rankcolor + "Guardian";
		} else if (this == CODER) {
			return rankcolor + "Coder";
		} else if (this == BUILDER) {
			return rankcolor + "Builder";
		} else if (this == JUNIOR_ADMIN) {
			return rankcolor + "Junior Admin";
		} else if (this == SENIOR_ADMIN) {
			return rankcolor + "Senior Admin";
		} else {
			return ChatColor.MAGIC + "ABCDEFGH";
		}
	}
	
}
