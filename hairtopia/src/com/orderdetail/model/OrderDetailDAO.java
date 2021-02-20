package com.orderdetail.model;

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


public class OrderDetailDAO implements OrderDetailDAO_interface{
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
			"INSERT INTO OrderDetail (ordNo,proNo,ordDetAmt,ordDetPrice) VALUES (?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE OrderDetail set ordDetAmt=?, ordDetPrice=? where ordNo=? and proNo=?";
	private static final String DELETE = 
			"DELETE FROM OrderDetail where ordNo=? and proNo=?";
	private static final String GET_ONE_STMT = 
			"SELECT ordNo,proNo,ordDetAmt,ordDetPrice FROM OrderDetail where ordNo=? and proNo=?";
	private static final String GET_ALL_STMT = 
			"SELECT ordNo,proNo,ordDetAmt,ordDetPrice FROM OrderDetail order by ordNo";
	
	@Override
	public void insert(OrderDetailVO orddVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, orddVO.getOrdNo());
			pstmt.setInt(2, orddVO.getProNo());
			pstmt.setInt(3, orddVO.getOrdDetAmt());
			pstmt.setInt(4, orddVO.getOrdDetPrice());
			
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
	public void update(OrderDetailVO orddVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setInt(1, orddVO.getOrdDetAmt());
			pstmt.setInt(2, orddVO.getOrdDetPrice());
			pstmt.setInt(3, orddVO.getOrdNo());
			pstmt.setInt(4, orddVO.getProNo());
			
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
	public void delete(Integer ordNo, Integer proNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, ordNo);
			pstmt.setInt(2, proNo);
			
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
	public OrderDetailVO findByPrimaryKey(Integer ordNo, Integer proNo) {
		OrderDetailVO orddVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, ordNo);
			pstmt.setInt(2, proNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orddVO = new OrderDetailVO();
				orddVO.setOrdNo(rs.getInt("ordNo"));
				orddVO.setProNo(rs.getInt("proNo"));
				orddVO.setOrdDetAmt(rs.getInt("ordDetAmt"));
				orddVO.setOrdDetPrice(rs.getInt("ordDetPrice"));
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
		return orddVO;
	}

	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orddVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orddVO = new OrderDetailVO();
				orddVO.setOrdNo(rs.getInt("ordNo"));
				orddVO.setProNo(rs.getInt("proNo"));
				orddVO.setOrdDetAmt(rs.getInt("ordDetAmt"));
				orddVO.setOrdDetPrice(rs.getInt("ordDetPrice"));
				list.add(orddVO);
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
