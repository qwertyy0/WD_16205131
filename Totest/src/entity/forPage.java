package entity;

import java.util.List;
import entity.Student;

public class forPage {
	private int totalPage;
	private int currentPage = 1;
	private int pageSize = 5;
	private int beginIndex;
	private int endIndex;
	private List<Student> studentList;

	public forPage(int currentPage, List<Student> studentList) {
		super();
		this.studentList = studentList;
		this.currentPage = currentPage;

		if (studentList == null) {
			return;
		} else {
			//计算总页数
			this.totalPage = studentList.size() % this.pageSize == 0 ? studentList.size() / this.pageSize
					: studentList.size() / this.pageSize + 1;
			//如果总页数小于页码数(5)
			if (totalPage <= 5) {
				beginIndex = 1;
				endIndex = totalPage;
			} else {
				beginIndex = currentPage - 2;
				endIndex = currentPage + 4;
				if (beginIndex < 1) {
					beginIndex = 1;
					endIndex = 5;
				}
				if (endIndex > totalPage) {
					beginIndex = totalPage - 5 + 1;
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

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	

}
