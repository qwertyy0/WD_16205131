package entity;

public class Student {
	private String sno;
	private String class_id;
	private String sname;
	private String smajor;
	private String sphone;
	private String sqq;
	private String spsw;
	
	public Student() {
		super();
	}

	public Student(String sno, String class_id, String sname, String smajor, String sphone, String sqq, String spsw) {
		super();
		this.sno = sno;
		this.class_id = class_id;
		this.sname = sname;
		this.smajor = smajor;
		this.sphone = sphone;
		this.sqq = sqq;
		this.spsw = spsw;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSmajor() {
		return smajor;
	}

	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getSqq() {
		return sqq;
	}

	public void setSqq(String sqq) {
		this.sqq = sqq;
	}

	public String getSpsw() {
		return spsw;
	}

	public void setSpsw(String spsw) {
		this.spsw = spsw;
	}
	
		
}

