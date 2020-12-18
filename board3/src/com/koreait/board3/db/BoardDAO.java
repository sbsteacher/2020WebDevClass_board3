package com.koreait.board3.db;

import java.sql.*;
import java.util.*;
import com.koreait.board3.model.*;

public class BoardDAO extends CommonDAO {
	public static List<BoardSEL> selBoardList(BoardPARAM p) {
		List<BoardSEL> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT "
				+ " A.i_board, A.seq, A.title, A.r_dt, A.hits "
				+ " , A.i_user, B.nm "
				+ " FROM t_board A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user " 
				+ " WHERE A.typ = ? " 
				+ " ORDER BY A.seq DESC ";
		
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, p.getTyp());
			rs = ps.executeQuery();			
			while(rs.next()) {
				BoardSEL vo = new BoardSEL();
				list.add(vo);
				
				vo.setI_board(rs.getInt("i_board"));
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getNString("title"));				
				vo.setR_dt(rs.getString("r_dt"));
				vo.setHits(rs.getInt("hits"));
				vo.setI_user(rs.getInt("i_user"));
				vo.setNm(rs.getNString("nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps, rs);
		}
		
		return list;
	}
	
	public static BoardSEL selBoard(BoardPARAM p) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT "
				+ " A.seq, A.typ, A.title, A.ctnt, A.r_dt, A.hits "
				+ " , A.i_user, B.nm "
				+ " FROM t_board A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user " 
				+ " WHERE A.i_board = ? ";
		
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, p.getI_board());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				BoardSEL vo = new BoardSEL();
				vo.setI_board(p.getI_board());
				vo.setSeq(rs.getInt("seq"));
				vo.setTyp(rs.getInt("typ"));
				vo.setTitle(rs.getNString("title"));
				vo.setCtnt(rs.getNString("ctnt"));
				vo.setR_dt(rs.getString("r_dt"));
				vo.setHits(rs.getInt("hits"));
				vo.setI_user(rs.getInt("i_user"));
				vo.setNm(rs.getNString("nm"));
				return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps, rs);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
