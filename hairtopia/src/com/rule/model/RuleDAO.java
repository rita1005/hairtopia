package com.rule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.brand.model.BrandVO;

public class RuleDAO implements RuleDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Hairtopia");
		} catch (NamingException e) {			
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT= 
			"INSERT INTO RULE (RULENAME,RULECON) VALUES (?,?)";
	private static final String UPDATE_STMT=
			"UPDATE RULE SET RULENAME=?, RULECON=? WHERE RULENO=?";
	private static final String DELETE_STMT=
			"DELETE FROM RULE WHERE RULENO=?";
	private static final String GET_ONE_STMT=
			"SELECT RULENO,RULENAME,RULECON FROM RULE WHERE RULENO=?";
	private static final String GET_ALL_STMT=
			"SELECT RULENO,RULENAME,RULECON FROM RULE ORDER BY RULENO";
	@Override
	public void insert(RuleVO ruleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,ruleVO.getRuleName());
			pstmt.setString(2, ruleVO.getRuleCon());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {		
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}		
	}

	@Override
	public void update(RuleVO ruleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1,ruleVO.getRuleName());
			pstmt.setString(2, ruleVO.getRuleCon());
			pstmt.setInt(3, ruleVO.getRuleNo());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {		
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}						
	}

	@Override
	public void delete(int ruleNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, ruleNo);
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {		
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}							
	}

	@Override
	public RuleVO findByPrimaryKey(int ruleNo) {
		RuleVO ruleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ruleNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ruleVO = new RuleVO();
				ruleVO.setRuleNo(rs.getInt("RULENO"));
				ruleVO.setRuleName(rs.getString("RULENAME"));
				ruleVO.setRuleCon(rs.getString("RULECON"));				
			}
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}				
		return ruleVO;
	}

	@Override
	public List<RuleVO> getAll() {
		List<RuleVO> list = new ArrayList<RuleVO>();
		RuleVO ruleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ruleVO = new RuleVO();
				ruleVO.setRuleNo(rs.getInt("RULENO"));
				ruleVO.setRuleName(rs.getString("RULENAME"));
				ruleVO.setRuleCon(rs.getString("RULECON"));	
				list.add(ruleVO);
			}
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}				
		return list;
	}

}
