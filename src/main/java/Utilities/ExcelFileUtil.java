package Utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelFileUtil {
	Workbook wb;
	//constructor for reading excel path
	public ExcelFileUtil(String excelapth)throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelapth);
		wb= WorkbookFactory.create(fi);
	}
	//method for counting rows in a sheet
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
	//count no of cells in a row
	public int cellCount(String sheetName)
	{
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}
	//method for getting cell data
	public String getCellData(String sheetName,int row,int column)
	{
		String data ="";
		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata =(int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			//convert integer type into string type
			data =String.valueOf(celldata);
		}
		else
		{
			data =wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//method for set cell data
	public void setCellData(String sheetName,int row,int column,String status,String writeexcel)
			throws Throwable{
		//get sheet from wb
		Sheet ws = wb.getSheet(sheetName);
		//get row from sheet
		Row rowNum =ws.getRow(row);
		//create cell
		Cell cell =rowNum.createCell(column);
		//set status in a cell
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle style =wb.createCellStyle();
			Font font= wb.createFont();
			//colour text
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);

		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style =wb.createCellStyle();
			Font font= wb.createFont();
			//colour text
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style =wb.createCellStyle();
			Font font= wb.createFont();
			//colour text
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeexcel);
		wb.write(fo);
	}
}
