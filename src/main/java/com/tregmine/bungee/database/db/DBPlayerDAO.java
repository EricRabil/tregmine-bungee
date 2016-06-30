package com.tregmine.bungee.database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.tregmine.bungee.Tregmine;
import com.tregmine.bungee.api.PlayerBan;
import com.tregmine.bungee.api.PlayerDataSource;
import com.tregmine.bungee.api.Rank;
import com.tregmine.bungee.database.DAOException;
import com.tregmine.bungee.database.IPlayerDAO;

public class DBPlayerDAO implements IPlayerDAO{
	
	private Connection conn;
	private Tregmine treg;
	
	public DBPlayerDAO(Tregmine tg, Connection connection){
		this.conn = connection;
		this.treg = tg;
	}
//	
//	@Override
//	public void loadReports(TregminePlayer player) throws DAOException{
//				String sql = "SELECT * FROM player_report WHERE subject_id = ?";
//				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//					stmt.setInt(1, player.getId());
//					stmt.execute();
//					try (ResultSet rs = stmt.getResultSet()) {
//						while (rs.next()) {
//							if ("ban".equals(rs.getString("report_action"))) {
//								player.setBanned(true);
//							}
//						}
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//	@Override
//	public Integer getID(UUID uuid) throws DAOException {
//		String sql = "SELECT * FROM player WHERE player_uuid = ?";
//		try(PreparedStatement stmt = conn.prepareStatement(sql)){
//			stmt.setString(1, uuid.toString());
//			stmt.execute();
//			try(ResultSet rs = stmt.getResultSet()){
//				while(rs.next()){
//					return rs.getInt("player_id");
//				}
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public Rank getRank(UUID uuid) throws DAOException {
//		String sql = "SELECT * FROM player WHERE player_uuid = ?";
//		try(PreparedStatement stmt = conn.prepareStatement(sql)){
//			stmt.setString(1, uuid.toString());
//			stmt.execute();
//			try(ResultSet rs = stmt.getResultSet()){
//				while(rs.next()){
//					return Rank.fromString(rs.getString("player_rank"));
//				}
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public PlayerDataSource buildPlayer(UUID uuid) throws DAOException {
		PlayerDataSource source = new PlayerDataSource();
		String sql = "SELECT * FROM player WHERE player_uuid = ?";
		String sqlx = "SELECT * FROM player_report WHERE subject_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, uuid.toString());
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){
				while(rs.next()){
					source.setID(rs.getInt("player_id"));
					source.setRank(Rank.fromString(rs.getString("player_rank")));
					source.setBanned(false);
				}
			}
			try(PreparedStatement stmtx = conn.prepareStatement(sqlx)){
				stmtx.setInt(1, source.getID());
				stmtx.execute();
				try(ResultSet rs = stmtx.getResultSet()){
					
					while(rs.next()){
						System.out.print(rs.getString("report_action"));
						if (rs.getString("report_action").trim().toLowerCase().contains("ban")) {
							if(rs.getObject("report_validuntil") == null) continue;
							if((rs.getInt("report_validuntil") * 1000l) < System.currentTimeMillis()) continue;
							System.out.print("bitch");
							PlayerBan ban = new PlayerBan();
							ban.setIssuerID(rs.getInt("issuer_id"));
							ban.setReportID(rs.getInt("report_id"));
							ban.setVictimID(rs.getInt("subject_id"));
							ban.setReportMessage(rs.getString("report_message"));
							source.setBanData(ban);
							source.setBanned(true);
						}
					}
				}
			}
			if(source.getID() == null){
				PlayerDataSource pds = new PlayerDataSource();
				pds.setBanned(false);
				pds.setID(null);
				pds.setRank(Rank.UNVERIFIED);
				return pds;
			}else{
				return source;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
