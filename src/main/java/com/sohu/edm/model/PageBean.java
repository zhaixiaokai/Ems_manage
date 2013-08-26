package com.sohu.edm.model;

import java.util.ArrayList;
import java.util.List;

import com.sohu.edm.tools.Constants;




public class PageBean {
	public int currentPage=0;
	public int totalRow=0;
	public int totalPage=0;
	
	public List<?> data=new ArrayList<Object>();

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
		this.totalPage=totalRow/Constants.MAX_RECORD_PER_PAGE+(totalRow%Constants.MAX_RECORD_PER_PAGE==0?0:1);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

}
