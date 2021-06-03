package it.polito.tdp.PremierLeague.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.PremierLeague.model.Action;
import it.polito.tdp.PremierLeague.model.Archi;
import it.polito.tdp.PremierLeague.model.Player;

public class PremierLeagueDAO {
	
	public void getVertici( Map<Integer, Player> idMap, float x){
		String sql = "SELECT a.PlayerID, p.Name "
				+ "FROM actions a, players p "
				+ "WHERE a.PlayerID=p.PlayerID "
				+ "GROUP BY a.PlayerID "
				+"HAVING AVG(a.Goals)>? ";
	
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setFloat(1, x);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Player player = new Player(res.getInt("a.PlayerID"), res.getString("p.Name"));
				idMap.put(res.getInt("a.PlayerID"), player);
				
			}
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public List<Action> listAllActions(){
		String sql = "SELECT * FROM Actions";
		List<Action> result = new ArrayList<Action>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Action action = new Action(res.getInt("PlayerID"),res.getInt("MatchID"),res.getInt("TeamID"),res.getInt("Starts"),res.getInt("Goals"),
						res.getInt("TimePlayed"),res.getInt("RedCards"),res.getInt("YellowCards"),res.getInt("TotalSuccessfulPassesAll"),res.getInt("totalUnsuccessfulPassesAll"),
						res.getInt("Assists"),res.getInt("TotalFoulsConceded"),res.getInt("Offsides"));
				
				result.add(action);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Archi> getArchi(Map<Integer,Player> idMap){
		String sql="SELECT a1.PlayerID as id1, a2.PlayerID AS id2, sum(a1.TimePlayed) AS t1, sum(a2.TimePlayed) AS t2 "
				+ "FROM actions a1, actions a2 "
				+ "WHERE a1.PlayerID!=a2.PlayerID AND a1.MatchID=a2.MatchID AND a1.`Starts`=1 AND a2.`Starts`=1 "
				+ "AND a1.TeamID!=a2.TeamID "
				+ "GROUP BY a1.PlayerID, a2.PlayerID "
				+ "HAVING t1>t2";
		List<Archi> result= new LinkedList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(idMap.containsKey(res.getInt("id1")) && idMap.containsKey(res.getInt("id2"))){
				int peso= (res.getInt("t1")- res.getInt("t2"));
                  Archi arco= new Archi(idMap.get(res.getInt("id1")), idMap.get(res.getInt("id2")), peso );
                  result.add(arco);
			}}
			conn.close();
			return result;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
