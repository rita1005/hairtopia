package com.product.model;

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


public class ProductDAO implements ProductDAO_interface{
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
			"INSERT INTO Product (ptypeNo,braNo,proName,proStatus,proPrice,proMpic,proPic,proDesc) VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE Product set ptypeNo=?, braNo=?, proName=?, proStatus=?, proPrice=?, proMpic=?, proPic=?, proDesc=? where proNo=?";
	private static final String DELETE = 
			"DELETE FROM Product where proNo = ?";
	private static final String GET_ONE_STMT = 
			"SELECT proNo,ptypeNo,braNo,proName,proStatus,proPrice,proMpic,proPic,proDesc FROM Product where proNo=?";
	private static final String GET_ALL_STMT = 
			"SELECT proNo,ptypeNo,braNo,proName,proStatus,proPrice,proMpic,proPic,proDesc FROM Product order by proNo";
	
	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, productVO.getPtypeNo());
			pstmt.setInt(2, productVO.getBraNo());
			pstmt.setString(3, productVO.getProName());
			pstmt.setBoolean(4, productVO.isProStatus());
			pstmt.setInt(5, productVO.getProPrice());
			pstmt.setBytes(6, productVO.getProMpic());
			pstmt.setBytes(7, productVO.getProPic());
			pstmt.setString(8, productVO.getProDesc());
			
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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, productVO.getPtypeNo());
			pstmt.setInt(2, productVO.getBraNo());
			pstmt.setString(3, productVO.getProName());
			pstmt.setBoolean(4, productVO.isProStatus());
			pstmt.setInt(5, productVO.getProPrice());
			pstmt.setBytes(6, productVO.getProMpic());
			pstmt.setBytes(7, productVO.getProPic());
			pstmt.setString(8, productVO.getProDesc());
			pstmt.setInt(9, productVO.getProNo());
			
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
	public void delete(Integer proNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, proNo);
			
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
	public ProductVO findByPrimaryKey(Integer proNo) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, proNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO = new ProductVO();
				productVO.setProNo(rs.getInt("proNo"));
				productVO.setPtypeNo(rs.getInt("ptypeNo"));
				productVO.setBraNo(rs.getInt("braNo"));
				productVO.setProName(rs.getString("proName"));
				productVO.setProStatus(rs.getBoolean("proStatus"));
				productVO.setProPrice(rs.getInt("proPrice"));
				productVO.setProMpic(rs.getBytes("proMpic"));
				productVO.setProPic(rs.getBytes("proPic"));
				productVO.setProDesc(rs.getString("proDesc"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO = new ProductVO();
				productVO.setProNo(rs.getInt("proNo"));
				productVO.setPtypeNo(rs.getInt("ptypeNo"));
				productVO.setBraNo(rs.getInt("braNo"));
				productVO.setProName(rs.getString("proName"));
				productVO.setProStatus(rs.getBoolean("proStatus"));
				productVO.setProPrice(rs.getInt("proPrice"));
				productVO.setProMpic(rs.getBytes("proMpic"));
				productVO.setProPic(rs.getBytes("proPic"));
				productVO.setProDesc(rs.getString("proDesc"));
				list.add(productVO);
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
