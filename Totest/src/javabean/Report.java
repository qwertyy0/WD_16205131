package javabean;

public class Report {

	private String report_id;
	private String report_file;
	private String report_name;
	
	
	public Report(String report_id, String report_file, String report_name) {
		super();
		this.report_id = report_id;
		this.report_file = report_file;
		this.report_name = report_name;
	}
	public Report() {
		super();
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getReport_file() {
		return report_file;
	}
	public void setReport_file(String report_file) {
		this.report_file = report_file;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

}
