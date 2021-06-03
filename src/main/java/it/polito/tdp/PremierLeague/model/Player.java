package it.polito.tdp.PremierLeague.model;

public class Player {
	Integer playerID;
	String name;
	
	public Player(Integer playerID, String name) {
		super();
		this.playerID = playerID;
		this.name = name;
	}
	
	public Integer getPlayerID() {
		return playerID;
	}
	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return playerID + " - " + name;
	}
	
	
	
}
