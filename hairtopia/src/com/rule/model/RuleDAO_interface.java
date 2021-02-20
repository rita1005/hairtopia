package com.rule.model;

import java.util.List;

public interface RuleDAO_interface {
	public void insert(RuleVO ruleVO);
	public void update(RuleVO ruleVO);
	public void delete(int ruleNo);
	public RuleVO findByPrimaryKey(int ruleNo);
	public List<RuleVO> getAll();
}
