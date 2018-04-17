package com.mall.pojo;

import java.util.List;

public class PageBean<T> {
	//当前页
	private int page;
	//每页最多显示的记录数
	private int pageSize;
	//当前页数据
	private List<T> data;
	//总记录数
	private long count;
	//总页数
	private int totalPage;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	@Override
	public String toString() {
		return "PageBean [page=" + page + ", pageSize=" + pageSize + ", data=" + data + ", count=" + count
				+ ", totalPage=" + totalPage + "]";
	}
}
