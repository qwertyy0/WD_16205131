package adminClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import entity.Teacher;

public class ReadExcelByFileForXlsx {
	/* 读取xlsx文件 */
	public static List<Teacher> readExcelByFileForXlsx(File file, int startrow, int startcol, int sheetnum) {
		List<Teacher> varList = new ArrayList<Teacher>();
		FileInputStream fi = null;
		XSSFWorkbook wb = null;
		XSSFSheet sheet = null;
		Log log = new Log() {

			@Override
			public void warn(Object arg0, Throwable arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void warn(Object arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void trace(Object arg0, Throwable arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void trace(Object arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isWarnEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isTraceEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isInfoEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isFatalEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isErrorEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isDebugEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void info(Object arg0, Throwable arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void info(Object arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void fatal(Object arg0, Throwable arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void fatal(Object arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void error(Object arg0, Throwable arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void error(Object arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void debug(Object arg0, Throwable arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void debug(Object arg0) {
				// TODO Auto-generated method stub

			}
		};
		try {
			// File target = new File(filepath, filename);
			// File target = new File(file);
			fi = new FileInputStream(file);
			wb = new XSSFWorkbook(fi);
			sheet = wb.getSheetAt(sheetnum); // sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; // 取得最后一行的行号

			for (int i = startrow; i < rowNum; i++) { // 行循环开始

				Teacher varpd = new Teacher();
				XSSFRow row = sheet.getRow(i); // 行
				int cellNum = row.getLastCellNum(); // 每行的最后一个单元格位置

				for (int j = startcol; j < cellNum; j++) { // 列循环开始

					XSSFCell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case NUMERIC:// 数值类型
							cellValue = String.valueOf((int) cell.getNumericCellValue());
							break;
						case STRING:// 字符串类型
							cellValue = cell.getStringCellValue();
							break;
						case FORMULA:// 公式类型
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case BLANK:// 空值类型
							cellValue = "";
							break;
						case BOOLEAN:// 布朗类型
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case ERROR:// 错误
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						default:
							break;
						}
					} else {
						cellValue = "";
					}
					switch (j) {
					case 1:
						varpd.setTeacher_id(cellValue);
						break;
					case 2:
						varpd.setTeacher_name(cellValue);
						break;
					case 3:
						varpd.setTeacher_major(cellValue);
						break;
					case 4:
						varpd.setTeacher_phone(cellValue);
						break;
					case 5:
						varpd.setTeacher_qq(cellValue);
						break;
					default:
						break;
					}
//					varpd.put("var"+j, cellValue);
				}
				varList.add(varpd);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (fi != null) {
				try {
					fi.close(); // 关闭流
				} catch (IOException e) {
					log.debug("inputStream close IOException:" + e);
				}
			}
		}
		return varList;
	}
}
