package adminClass;

public class Firm {
	private String firm_id;
	private String firm_name;
	private String firm_address_sheng;
	private String firm_address_shi;
	private String firm_linkman;
	private String firm_linkman_phone;
	private int stuNum = 0;

	public String getFirm_id() {
		return firm_id;
	}

	public void setFirm_id(String firm_id) {
		this.firm_id = firm_id;
	}

	public String getFirm_name() {
		return firm_name;
	}

	public void setFirm_name(String firm_name) {
		this.firm_name = firm_name;
	}

	public String getFirm_address_sheng() {
		return firm_address_sheng;
	}

	public void setFirm_address_sheng(String firm_address_sheng) {
		this.firm_address_sheng = firm_address_sheng;
	}

	public String getFirm_address_shi() {
		return firm_address_shi;
	}

	public void setFirm_address_shi(String firm_address_shi) {
		this.firm_address_shi = firm_address_shi;
	}

	public String getFirm_linkman() {
		return firm_linkman;
	}

	public void setFirm_linkman(String firm_linkman) {
		this.firm_linkman = firm_linkman;
	}

	public String getFirm_linkman_phone() {
		return firm_linkman_phone;
	}

	public void setFirm_linkman_phone(String firm_linkman_phone) {
		this.firm_linkman_phone = firm_linkman_phone;
	}

	public int getStuNum() {
		return stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

}
