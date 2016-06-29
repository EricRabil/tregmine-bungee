package com.tregmine.bungee.api;

public class PlayerDataSource {
	private Rank rank;
	private int id;
	private boolean isBanned;
	
	public PlayerDataSource(){
		
	}
	
	public void setRank(Rank p0){
		this.rank = p0;
	}
	
	public void setID(Integer p0){
		this.id = p0;
	}
	
	public void setBanned(boolean p0){
		this.isBanned = p0;
	}
	
	public Rank getRank(){
		return this.rank;
	}
	
	public Integer getID(){
		return this.id;
	}
	
	public boolean isBanned(){
		return this.isBanned;
	}
}
