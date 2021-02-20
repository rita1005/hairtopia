package com.ordermaster.model;

import java.util.List;

public interface OrderMasterDAO_interface {
	public void insert(OrderMasterVO ordmVO);
	public void update(OrderMasterVO ordmVO);
	public void delete(Integer ordNo);
	public OrderMasterVO findByPrimaryKey(Integer ordNo);
	public List<OrderMasterVO> getAll();
}
