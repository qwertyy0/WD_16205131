package entity;

public class Post {
	private String teacher_id;
	private String start_time;
	private String end_time;
	private String class_id;
	private String weeknum;
	
	public Post() {
		super();
	}
	public Post(String teacher_id, String start_time, String end_time, String class_id, String weeknum) {
		super();
		this.teacher_id = teacher_id;
		this.start_time = start_time;
		this.end_time = end_time;
		this.class_id = class_id;
		this.weeknum = weeknum;
	}
	
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getWeeknum() {
		return weeknum;
	}
	public void setWeeknum(String weeknum) {
		this.weeknum = weeknum;
	}
	
	
}
