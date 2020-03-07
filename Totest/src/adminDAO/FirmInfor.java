package adminDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import adminClass.Firm;

public class FirmInfor {

	public static List<Firm> getAllFirm() {
		Connection connection = DBConnection.getDBConnection();
		String sql = "SELECT firm_id,firm_name,firm_address_sheng,firm_address_shi,firm_linkman,firm_linkman_phone FROM firm WHERE firm_id IN (SELECT firm_id FROM stu_firm)";
		List<Firm> firmList = new ArrayList<>();
		Firm firm = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				firm = new Firm();
				firm.setFirm_id(rs.getString(1));
				firm.setFirm_name(rs.getString(2));
				firm.setFirm_address_sheng(rs.getString(3));
				firm.setFirm_address_shi(rs.getString(4));
				firm.setFirm_linkman(rs.getString(5));
				firm.setFirm_linkman_phone(rs.getString(6));
				firmList.add(firm);
			}
			// 将查询到的未提交的学生学号插入到表count_noreport_list中(未提交汇报学生信息统计表)
			// 未实现
			rs.close();
			ps.close();
			connection.close();
			return firmList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	// 统计在某个地区的学生数量
	public static Firm countFirmNum(String firm_address_sheng, String firm_address_shi) {
		Connection connection = DBConnection.getDBConnection();
		String sql = "SELECT firm_id,firm_name,firm_address_sheng,firm_address_shi,firm_linkman,firm_linkman_phone FROM firm WHERE firm_id IN (SELECT firm_id FROM stu_firm) AND firm_address_sheng=? and firm_address_shi=?";
		Firm firm = null;
		int count = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, firm_address_sheng);
			ps.setString(2, firm_address_shi);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				firm = new Firm();
				firm.setFirm_id(rs.getString(1));
				firm.setFirm_name(rs.getString(2));
				firm.setFirm_address_sheng(rs.getString(3));
				firm.setFirm_address_shi(rs.getString(4));
				firm.setFirm_linkman(rs.getString(5));
				firm.setFirm_linkman_phone(rs.getString(6));
				count++;
				while (rs.next()) {
					count++;
				}
				firm.setStuNum(count);
			}
//			 else {
////				ps = connection.prepareStatement(sql0);
////				ps.setString(1, firm_address_sheng);
////				ps.setString(2, firm_address_shi);
////				rs = ps.executeQuery();
//			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return firm;
	}
}
