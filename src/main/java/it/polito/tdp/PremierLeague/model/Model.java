package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private Map<Integer, Player> idMap;
	private PremierLeagueDAO dao;
	private Graph<Player, DefaultWeightedEdge> grafo;

	public Model() {
		super();
	   this.dao= new PremierLeagueDAO();
		
	}
	
	public void creaGrafo(float x) {
		this.grafo= new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		this.idMap=new HashMap<Integer, Player>();
		
		this.dao.getVertici(idMap, x);
		Graphs.addAllVertices(grafo, this.idMap.values());
		List<Archi> archi= dao.getArchi(idMap);
		for(Archi a: archi) {
			DefaultWeightedEdge e= grafo.addEdge(a.getP1(), a.getP2());
			grafo.setEdgeWeight(e, a.getPeso());
			
		}
		
	}
	
	public int getVertici() {
		return this.grafo.vertexSet().size();
	}

	public int getArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Player getTopPlayer() {
		int max=0;
		Player p1=null;
		for(Player p: this.grafo.vertexSet()) {
			if(grafo.outDegreeOf(p)>max) {
				max+= grafo.outDegreeOf(p);
			    p1=p;
			}
		}
		return p1;
	}
	
    public Graph<Player, DefaultWeightedEdge> getGrafo() {
    	return grafo;
    }
    
    public List<Archi> getAvversari(Player top){
    	List<Archi> result= new ArrayList<>();
    	Set<DefaultWeightedEdge> archi= this.grafo.outgoingEdgesOf(top);
    	for(DefaultWeightedEdge e:archi) {
    		Archi a= new Archi(top, grafo.getEdgeTarget(e), (int)grafo.getEdgeWeight(e));
    		result.add(a);
    	}
    	Collections.sort(result);
    	return result;
    }
	
}
