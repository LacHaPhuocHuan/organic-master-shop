package com.organic.shop.Dtos;

public class PaginatesDto {
	private int currentPage, limit ,end, start,totalPage;

	public PaginatesDto(int currentPage, int limit, int end, int start, int totalPage) {
		super();
		this.currentPage = currentPage;
		this.limit = limit;
		this.end = end;
		this.start = start;
		this.totalPage = totalPage;
	}

	public PaginatesDto() {
		super();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	

}
