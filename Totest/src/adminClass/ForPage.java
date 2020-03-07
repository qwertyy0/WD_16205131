package adminClass;

import java.util.List;

public class ForPage<T> {
	private int totalPage;
	private int currentPage;
	private int pageSize = 5;
	private int beginIndex;
	private int endIndex;
	private List<T> resultList; // 查询出的全部结果
	private int navPageNum = 5;

	public ForPage() {

	}

	public void doPage(int currentPage) {

		if (this.resultList == null) {
			return;
		} else {
			this.totalPage = this.resultList.size() % this.pageSize == 0 ? this.resultList.size() / this.pageSize
					: this.resultList.size() / this.pageSize + 1;
			if (this.totalPage < this.navPageNum) {
				beginIndex = 1;
				endIndex = this.totalPage;
			} else {
				beginIndex = currentPage - (this.navPageNum - 1) / 2;
				endIndex = currentPage + (this.navPageNum + 1) / 2;
				if (beginIndex <1) {
					beginIndex = 1;
					endIndex = this.navPageNum;
				}
				if (endIndex > totalPage) {
					beginIndex = totalPage - this.navPageNum + 1;
					endIndex = totalPage;
				}
			}
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public int getNavPageNum() {
		return navPageNum;
	}

	public void setNavPageNum(int navPageNum) {
		this.navPageNum = navPageNum;
	}

}
