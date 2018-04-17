package com.mall.pojo;

import java.util.Date;
import java.util.List;

public class Orders {
	private String oid;
	private Date ordertime;
	private double total;
	private int state;
    private String address;
    private String name;
    private String telephone;
    private String uid;
    private List<OrderItem> items;
    
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state
				+ ", address=" + address + ", name=" + name + ", telephone=" + telephone + ", uid=" + uid + "]";
	}
}
