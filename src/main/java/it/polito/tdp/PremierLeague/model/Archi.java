package it.polito.tdp.PremierLeague.model;

import java.util.Comparator;

public class Archi implements Comparable<Archi> {

	Player p1;
	Player p2;
	int peso;
	
	public Archi(Player p1, Player p2, int peso) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.peso = peso;
	}

	public Player getP1() {
		return p1;
	}
	public void setP1(Player p1) {
		this.p1 = p1;
	}
	public Player getP2() {
		return p2;
	}
	public void setP2(Player p2) {
		this.p2 = p2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		result = prime * result + peso;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Archi other = (Archi) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		if (peso != other.peso)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Archi [p1=" + p1 + ", p2=" + p2 + ", peso=" + peso + "]";
	}



	@Override
	public int compareTo(Archi o) {
		// TODO Auto-generated method stub
		return o.peso-this.peso;
	}
	
	
	
}
