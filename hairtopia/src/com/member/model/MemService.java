package com.member.model;

import java.util.*;

public class MemService {
	private MemDAO_interface dao;
	
	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO addMember(String memEmail, String memPswd) {
		MemVO memVO = new MemVO();
		
		memVO.setMemEmail(memEmail);
		memVO.setMemPswd(memPswd);
		
		dao.insert(memVO);
		
		return memVO;
	}
	
	public MemVO updateMember(Integer memNo, String memEmail, String memPswd) {
		
		MemVO memVO = new MemVO();
		memVO.setMemNo(memNo);
		memVO.setMemEmail(memEmail);
		memVO.setMemPswd(memPswd);
		
		dao.update(memVO);
		
		return memVO;
	}
	
	public void deleteMember(Integer memNo) {
		dao.delete(memNo);
	}
	
	public MemVO getOneMem(Integer memNo) {
		return dao.findByPrimaryKey(memNo);
	}
	
	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	public MemVO validate(String memEmail, String memPswd){
		return dao.validate(memEmail, memPswd);
	}
	
	public String validateEmail(String memEmail) {
		return dao.validateEmail(memEmail);
	}
	
	public void updatePassword(String memEmail, String memPswd) {
		dao.updatePassword(memEmail, memPswd);
	}
	
}
