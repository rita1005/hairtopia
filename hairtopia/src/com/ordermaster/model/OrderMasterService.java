package com.ordermaster.model;

import java.util.List;


public class OrderMasterService {
private OrderMasterDAO_interface dao;
	
	public OrderMasterService() {
		dao = new OrderMasterDAO();
	}
	
	public OrderMasterVO addOrderMaster(Integer memNo, Integer ordStatus, Integer ordAmt) {
		OrderMasterVO ordmVO = new OrderMasterVO();
		
		ordmVO.setMemNo(memNo);
		ordmVO.setOrdStatus(ordStatus);
		ordmVO.setOrdAmt(ordAmt);
		dao.insert(ordmVO);
		
		return ordmVO;
	}
	
	//預留給Struts2用
	public void addOrderMaster(OrderMasterVO ordmVO) {
		dao.insert(ordmVO);
	}
	
	public OrderMasterVO updateOrderMaster(Integer ordNo, Integer memNo, Integer ordStatus, Integer ordAmt) {
		OrderMasterVO ordmVO = new OrderMasterVO();
		
		ordmVO.setMemNo(memNo);
		ordmVO.setOrdStatus(ordStatus);
		ordmVO.setOrdAmt(ordAmt);
		dao.update(ordmVO);
		
		return dao.findByPrimaryKey(ordNo);
	}
	
	//預留給Struts2用
	public void updateOrderMaster(OrderMasterVO ordmVO) {
		dao.update(ordmVO);
	}
	
	public void deleteOrderMaster(Integer ordNo) {
		dao.delete(ordNo);
	}
	
	public OrderMasterVO getOneOrderMaster(Integer ordNo) {
		return dao.findByPrimaryKey(ordNo);
	}
	
	public List<OrderMasterVO> getAll(){
		return dao.getAll();
	}
}
