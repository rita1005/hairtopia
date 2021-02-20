package com.orderdetail.model;

import java.util.List;


public class OrderDetailService {
private OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDAO();
	}
	
	public OrderDetailVO addOrderDetail(Integer ordNo, Integer proNo, Integer ordDetAmt, Integer ordDetPrice) {
		OrderDetailVO orddVO = new OrderDetailVO();
		
		orddVO.setOrdNo(ordNo);
		orddVO.setProNo(proNo);
		orddVO.setOrdDetAmt(ordDetAmt);
		orddVO.setOrdDetPrice(ordDetPrice);		
		dao.insert(orddVO);
		
		return orddVO;
	}
	
	//預留給Struts2用
	public void addOrderDetail(OrderDetailVO orddVO) {
		dao.insert(orddVO);
	}
	
	public OrderDetailVO updateOrderDetail(Integer ordNo, Integer proNo, Integer ordDetAmt, Integer ordDetPrice) {
		OrderDetailVO orddVO = new OrderDetailVO();
		
		orddVO.setOrdNo(ordNo);
		orddVO.setProNo(proNo);
		orddVO.setOrdDetAmt(ordDetAmt);
		orddVO.setOrdDetPrice(ordDetPrice);
		dao.update(orddVO);
		
		return dao.findByPrimaryKey(ordNo, proNo);
	}
	
	//預留給Struts2用
	public void updateOrderDetail(OrderDetailVO orddVO) {
		dao.update(orddVO);
	}
	
	public void deleteOrderDetail(Integer ordNo, Integer proNo) {
		dao.delete(ordNo, proNo);
	}
	
	public OrderDetailVO getOneOrderDetail(Integer ordNo, Integer proNo) {
		return dao.findByPrimaryKey(ordNo, proNo);
	}
	
	public List<OrderDetailVO> getAll(){
		return dao.getAll();
	}
}
