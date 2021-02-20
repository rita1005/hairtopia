package com.rule.model;

import java.util.List;


public class RuleService {
	private RuleDAO dao;

	public RuleService(){
		dao = new RuleDAO();
	}
	
	public RuleVO addRule(String ruleName, String ruleCon) {
		RuleVO ruleVO = new RuleVO();
		
		ruleVO.setRuleName(ruleName);
		ruleVO.setRuleCon(ruleCon);
		dao.insert(ruleVO);
		
		return ruleVO;
	}
	
	public void addRule(RuleVO ruleVO) {
		dao.insert(ruleVO);
	}
	
	public RuleVO updateRule(Integer ruleNo, String ruleName, String ruleCon) {
		RuleVO ruleVO = new RuleVO();
		
		ruleVO.setRuleNo(ruleNo);
		ruleVO.setRuleName(ruleName);
		ruleVO.setRuleCon(ruleCon);
		dao.update(ruleVO);
		
		return dao.findByPrimaryKey(ruleNo);
	}
	
	//預留給Struts2用
	public void updateRule(RuleVO ruleVO) {
		dao.update(ruleVO);
	}
	
	public void deleteRule(Integer ruleNo) {
		dao.delete(ruleNo);
	}
	
	public RuleVO getOneRule(Integer ruleNo) {
		return dao.findByPrimaryKey(ruleNo);
	}
	
	public List<RuleVO> getAll(){
		return dao.getAll();
	}
}
