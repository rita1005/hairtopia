package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MemDAO implements MemDAO_interface{

	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//memNo,memName,memGender,memPic,memInform,memEmail,memPswd,memPhone,memAddr,memBal,memStatus,memEndDate, memCode
	private static final String INSERT_SIGNUP_STMT = "INSERT INTO MEMBER (memName, memEmail, memPswd) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEMBER";
	private static final String GET_ONE_STMT = "SELECT * FROM MEMBER WHERE memNO = ?";
	private static final String VALIDATE_STMT = "SELECT * FROM MEMBER WHERE memEmail=? AND memPswd=?";
	private static final String CONFIRM_EMAIL = "SELECT * FROM MEMBER WHERE memEmail=? ";
	private static final String DELETE = "DELETE FROM MEMBER WHERE memNO = ?";
	private static final String UPDATE = "UPDATE MEMBER set memName=?, memEmail=?, memPswd= ? WHERE memNO = ?";
	private static final String UPDATE_PASSWORD_BY_EMAIL = "UPDATE MEMBER set memPswd= ? WHERE memEmail = ?";
	private static final String UPDATE_NOPIC_STMT = "INSERT INTO MEMBER (memName, memGender , memInform ,memEmail, memPswd, memPhone, memAddr) VALUES (?, ?, ?, ?, ?, ?, ?)";


	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SIGNUP_STMT);
			pstmt.setString(1, memVO.getMemName());
			pstmt.setString(2, memVO.getMemEmail());
			pstmt.setString(3, memVO.getMemPswd());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memVO.getMemName());
			pstmt.setString(2, memVO.getMemEmail());
			pstmt.setString(3, memVO.getMemPswd());
			pstmt.setInt(4, memVO.getMemNo());

			pstmt.executeUpdate();
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void updatePassword(String memEmail, String memPswd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PASSWORD_BY_EMAIL);

			pstmt.setString(1, memPswd);
			pstmt.setString(2, memEmail);
			

			int a = pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	@Override
	public void delete(Integer memNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memNo);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public MemVO findByPrimaryKey(Integer memNo) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("memNo"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getInt("memGender"));
				memVO.setMemPic(rs.getBytes("memPic"));
				memVO.setMemInform(rs.getString("memInform"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemPswd(rs.getString("memPswd"));
				memVO.setMemPhone(rs.getString("memPhone"));
				memVO.setMemAddr(rs.getString("memAddr"));
				memVO.setMemBal(rs.getInt("memBal"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemEndDate(rs.getDate("memEndDate"));
				memVO.setMemCode(rs.getString("memCode"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("memNo"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getInt("memGender"));
				memVO.setMemPic(rs.getBytes("memPic"));
				memVO.setMemInform(rs.getString("memInform"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemPswd(rs.getString("memPswd"));
				memVO.setMemPhone(rs.getString("memPhone"));
				memVO.setMemAddr(rs.getString("memAddr"));
				memVO.setMemBal(rs.getInt("memBal"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemEndDate(rs.getDate("memEndDate"));
				memVO.setMemCode(rs.getString("memCode"));
				
				list.add(memVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		return list;
	}
	
	
	public MemVO validate(String memEmail, String memPswd) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(VALIDATE_STMT);
			
			pstmt.setString(1, memEmail);
			pstmt.setString(2, memPswd);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("memNo"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getInt("memGender"));
				memVO.setMemPic(rs.getBytes("memPic"));
				memVO.setMemInform(rs.getString("memInform"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemPswd(rs.getString("memPswd"));
				memVO.setMemPhone(rs.getString("memPhone"));
				memVO.setMemAddr(rs.getString("memAddr"));
				memVO.setMemBal(rs.getInt("memBal"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemEndDate(rs.getDate("memEndDate"));
				memVO.setMemCode(rs.getString("memCode"));
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}

	@Override
	public String validateEmail(String memEmail) {
		String memName = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CONFIRM_EMAIL);
			
			pstmt.setString(1, memEmail);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memName = rs.getString("memName");
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memName;
	}




}
