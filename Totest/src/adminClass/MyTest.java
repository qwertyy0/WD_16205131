package adminClass;

import java.io.File;
import java.util.List;

import entity.Teacher;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\52251\\Desktop\\作业及实验\\Web\\课程设计\\WTF.xlsx");
		List<Teacher> list = ReadExcelByFileForXlsx.readExcelByFileForXlsx(file, 1, 1, 0);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).getTeacher_id() + "   ");
			System.out.print(list.get(i).getTeacher_name() + "   ");
			System.out.print(list.get(i).getTeacher_major() + "   ");
			System.out.print(list.get(i).getTeacher_phone() + "   ");
			System.out.print(list.get(i).getTeacher_qq() + "   ");
			System.out.println();
		}
	}

}
