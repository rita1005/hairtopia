package com.orderdetail.model;

import java.util.List;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orddVO);
	public void update(OrderDetailVO orddVO);
	public void delete(Integer ordNo, Integer proNo);
	public OrderDetailVO findByPrimaryKey(Integer ordNo, Integer proNo);
	public List<OrderDetailVO> getAll();
}
