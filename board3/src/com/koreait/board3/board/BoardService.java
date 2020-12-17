package com.koreait.board3.board;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.common.Utils;
import com.koreait.board3.db.BoardDAO;
import com.koreait.board3.db.SQLInterUpdate;
import com.koreait.board3.model.BoardPARAM;
import com.koreait.board3.model.BoardSEL;

public class BoardService {
	
	public static void selBoardList(HttpServletRequest request) {
		int typ = Utils.getIntParam(request, "typ");
		BoardPARAM p = new BoardPARAM();
		p.setTyp(typ);
		
		request.setAttribute("list", BoardDAO.selBoardList(p));
	}
	
	public static int regMod(HttpServletRequest request) {
		int i_board = Utils.getIntParam(request, "i_board");
		int typ = Utils.getIntParam(request, "typ");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		int i_user = SecurityUtils.getLoingUserPk(request);
				
		if(i_board == 0) { //등록			
			String sql = " INSERT INTO t_board "
					+ " (typ, seq, title, ctnt, i_user) "
					+ " SELECT "
					+ " ?, ifnull(max(seq), 0) + 1, ?, ?, ? "
					+ " FROM t_board "
					+ " WHERE typ = ? ";
			
			BoardDAO.executeUpdate(sql, new SQLInterUpdate() {
				@Override
				public void proc(PreparedStatement ps) throws SQLException {
					ps.setInt(1, typ);
					ps.setNString(2, title);
					ps.setNString(3,  ctnt);
					ps.setInt(4, i_user);
					ps.setInt(5, typ);
				}
			});			
		} else { //수정
			
		}
		
		return 0;
	}
}
