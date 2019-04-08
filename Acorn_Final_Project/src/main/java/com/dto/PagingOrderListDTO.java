package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("PagingOrderListDTO")
public class PagingOrderListDTO {

	private int total;
	private int offset = 0;
	private int limit = 10;
	private int page = 1;
	private int totalpage;
	private int startpage = 1;
	private List<OrderDTO> orderlist;

	public PagingOrderListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagingOrderListDTO(int total, int offset, int limit, int page, int totalpage, int startpage,
			List<OrderDTO> orderlist) {
		super();
		this.total = total;
		this.offset = offset;
		this.limit = limit;
		this.page = page;
		this.totalpage = totalpage;
		this.startpage = startpage;
		this.orderlist = orderlist;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		this.totalpage = total / limit;
		if (total % limit != 0) {
			this.totalpage++;
		}
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		this.offset = (page - 1) * limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		this.offset = (page - 1) * limit;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
		
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public List<OrderDTO> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<OrderDTO> orderlist) {
		this.orderlist = orderlist;
	}

	@Override
	public String toString() {
		return "PagingOrderListDTO [total=" + total + ", offset=" + offset + ", limit=" + limit + ", page=" + page
				+ ", totalpage=" + totalpage + ", startpage=" + startpage + ", orderlist=" + orderlist + "]";
	}

}
