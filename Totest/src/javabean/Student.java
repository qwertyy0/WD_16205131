package javabean;

public class Student {
	String smajor;
	String sno;
	String sclass_id;
	String sna;
	String sphone;
	String sqq;
	String spsw;
	
	public Student(String smajor,String sno, String sclass, String sna, String sphone, String sqq,String spsw) {
		super();
		this.smajor = smajor;
		this.sclass_id = sclass;
		this.sno = sno;
		this.sna = sna;
		this.sphone = sphone;
		this.sqq = sqq;
		this.spsw = spsw;
	}
	public Student() {
		super();
	}
	public String getSmajor() {
		return smajor;
	}
	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}
	public String getSclass_id() {
		return sclass_id;
	}
	public void setSclass_id(String sclass) {
		this.sclass_id = sclass;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSna() {
		return sna;
	}
	public void setSna(String sna) {
		this.sna = sna;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public String getsqq() {
		return sqq;
	}
	public void setsqq(String sqq) {
		this.sqq = sqq;
	}
	public String getSpsw() {
		return spsw;
	}
	public void setSpsw(String spsw) {
		this.spsw = spsw;
	}
	
}
