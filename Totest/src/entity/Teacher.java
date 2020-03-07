package entity;

public class Teacher {
	private String teacher_id;
	private String teacher_psd;
	private String teacher_major;
	private String teacher_name;
	private String teacher_phone;
	private String teacher_qq;
	
	public Teacher() {
		super();
	}
	public Teacher(String teacher_id, String teacher_major, String teacher_name, String teacher_phone,
			String teacher_qq) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_major = teacher_major;
		this.teacher_name = teacher_name;
		this.teacher_phone = teacher_phone;
		this.teacher_qq = teacher_qq;
	}
	//get  set访问器修改器
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_psd() {
		return teacher_psd;
	}
	public void setTeacher_psd(String teacher_psd) {
		this.teacher_psd = teacher_psd;
	}
	public String getTeacher_major() {
		return teacher_major;
	}
	public void setTeacher_major(String teacher_major) {
		this.teacher_major = teacher_major;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_phone() {
		return teacher_phone;
	}
	public void setTeacher_phone(String teacher_phone) {
		this.teacher_phone = teacher_phone;
	}
	public String getTeacher_qq() {
		return teacher_qq;
	}
	public void setTeacher_qq(String teacher_qq) {
		this.teacher_qq = teacher_qq;
	}
	
	
	
}
