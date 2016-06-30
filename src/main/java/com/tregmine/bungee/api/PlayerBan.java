package com.tregmine.bungee.api;

public class PlayerBan {
	private int report_id;
	private int victim_id;
	private int issuer_id;
	private String report_message;
	private int timestamp;
	private Integer expiration;
	
	public PlayerBan(){
		
	}
	
	public void setReportID(int id){
		this.report_id = id;
	}
	
	public void setVictimID(int id){
		this.victim_id = id;
	}
	
	public void setIssuerID(int id){
		this.issuer_id = id;
	}
	
	public void setReportMessage(String message){
		this.report_message = message;
	}
	
	public void setTimestamp(int i){
		this.timestamp = i;
	}
	
	public void setValidUntil(Integer i){
		this.expiration = i;
	}
	
	public int getReportID(){
		return this.report_id;
	}
	
	public int getVictimID(){
		return this.victim_id;
	}
	
	public int getIssuerID(){
		return this.issuer_id;
	}
	
	public String getReportMessage(){
		return this.report_message;
	}
	
	public boolean neverExpires(){
		return this.expiration == null;
	}
	
	public int getTimestamp(){
		return this.timestamp;
	}
	
	public Integer getValidUntil(){
		return this.expiration;
	}
}
