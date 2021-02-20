package com.ordermaster.model;

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


public class OrderMasterDAO implements OrderMasterDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/hairtopia");
		} catch (NamingException e) {			
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO OrderMaster (memNo,ordStatus,ordAmt) VALUES (?,?,?)";
	private static final String UPDATE = 
			"UPDATE OrderMaster set memNo=?, ordStatus=?, ordAmt=? where ordNo = ?";
	private static final String DELETE = 
			"DELETE FROM OrderMaster where ordNo = ?";
	private static final String GET_ONE_STMT = 
			"SELECT ordNo,memNo,ordStatus,ordAmt FROM OrderMaster where ordNo = ?";
	private static final String GET_ALL_STMT = 
			"SELECT ordNo,memNo,ordStatus,ordAmt FROM OrderMaster order by ordNo";
	@Override
	public void insert(OrderMasterVO ordmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, ordmVO.getMemNo());
			pstmt.setInt(2, ordmVO.getOrdStatus());
			pstmt.setInt(3, ordmVO.getOrdAmt());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {
			if(pstmt != null) {			
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}			
			}
			if(con != null) {												
				try {
					con.close();
				} catch (SQLException e) {					
					e.printStackTrace(System.err);
				}											
			}
		}
		
	}

	@Override
	public void update(OrderMasterVO ordmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, ordmVO.getMemNo());
			pstmt.setInt(2, ordmVO.getOrdStatus());
			pstmt.setInt(3, ordmVO.getOrdAmt());
			pstmt.setInt(4, ordmVO.getOrdNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {
			if(pstmt != null) {			
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}			
			}
			if(con != null) {												
				try {
					con.close();
				} catch (SQLException e) {					
					e.printStackTrace(System.err);
				}											
			}
		}
		
	}

	@Override
	public void delete(Integer ordNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, ordNo);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {
			if(pstmt != null) {			
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}			
			}
			if(con != null) {												
				try {
					con.close();
				} catch (SQLException e) {					
					e.printStackTrace(System.err);
				}											
			}
		}
		
	}

	@Override
	public OrderMasterVO findByPrimaryKey(Integer ordNo) {
		OrderMasterVO ordmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, ordNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ordmVO = new OrderMasterVO();
				ordmVO.setOrdNo(rs.getInt("ordNo"));
				ordmVO.setMemNo(rs.getInt("memNo"));
				ordmVO.setOrdStatus(rs.getInt("ordStatus"));
				ordmVO.setOrdAmt(rs.getInt("ordAmt"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally{
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return ordmVO;
	}

	@Override
	public List<OrderMasterVO> getAll() {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO ordmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ordmVO = new OrderMasterVO();
				ordmVO.setOrdNo(rs.getInt("ordNo"));
				ordmVO.setMemNo(rs.getInt("memNo"));
				ordmVO.setOrdStatus(rs.getInt("ordStatus"));
				ordmVO.setOrdAmt(rs.getInt("ordAmt"));
				list.add(ordmVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally{
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
