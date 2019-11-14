import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	
	private static ExcelManager excelmanager;
	private static XSSFWorkbook workbook ;
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private final String xsspath = "C:\\Users\\wlvkd\\Desktop\\test.xlsx"; //write your xlsx file path plz;
	private final int cellend = 8; 
	
	private ExcelManager(Mediator mediator) {
		
		workbook = getWorkbook(xsspath);
	}
	
	public static ExcelManager getExcelManager(Mediator mediator) {
		
		if(excelmanager == null) {
			excelmanager = new ExcelManager(mediator);
		}
		return excelmanager;
	}
	
	//봉사 기록을 엑셀에 저장하는 내용이다. 시트 0번에 저장하도록 한다. 호환성을 위해 volunteer 반환
	public Volunteer WriteVoluenteertoExcel(Volunteer volunteer) {
		XSSFRow row=null;
		XSSFCell cell=null;
		XSSFSheet sheet=workbook.getSheetAt(0);
		
		int rowTotal = sheet.getLastRowNum();

		if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
		    rowTotal++;
		}
		 row=sheet.createRow((short)rowTotal);

		 
		 CellStyle dateStyle = workbook.createCellStyle();
		 CreationHelper createHelper = workbook.getCreationHelper();
		 dateStyle .setDataFormat(
             createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm"));	
		 
		 
		 
		 CellStyle hourStyle = workbook.createCellStyle();
         CreationHelper createHelper2 = workbook.getCreationHelper();
         hourStyle .setDataFormat(
             createHelper2.createDataFormat().getFormat("hh:mm"));	
         
         for(int cellstate = 0; cellstate< cellend ;cellstate++) {
        	 
        	 cell=row.createCell(cellstate);
        	 
        	 switch(cellstate) {
        	 
        	 case 0:

                 cell.setCellType(CellType.NUMERIC);
                 cell.setCellStyle(dateStyle);
                 cell.setCellValue(new Date());
                 break;
        	 case 1:

                 cell.setCellType(CellType.NUMERIC);
                 cell.setCellStyle(hourStyle);
                 cell.setCellValue(volunteer.vStartDate);
                 break;
        	 case 2:
        		 cell=row.createCell(cellstate);
                 cell.setCellType(CellType.NUMERIC);
                 cell.setCellStyle(hourStyle);
                 cell.setCellValue(volunteer.vEndDate);
                 break;
                 
                 //
        	 case 3:

                 long diff = volunteer.vEndDate.getTime() - volunteer.vStartDate.getTime();
                 long time = diff / (1000 * 60 * 60);
                 cell.setCellValue(time+"시간");
                 break;
                 
                 
                 //vInfo
        	 case 4:
        		
                 cell.setCellValue(volunteer.vInfo);
                 break;
        	 case 5:

                 cell.setCellValue(volunteer.getRemarks());
                 break;
        	 case 6:
                 cell.setCellValue(volunteer.name);
                 break;
        	 case 7:

                 cell.setCellValue(volunteer.number);
                 break;
        	 
        	 }
        	
         }
         //cellsize auto rearrange
		 for(int i=0;i<cellend;i++) {
			 sheet.autoSizeColumn(i);
		 } 
		 //out to the excell and return check
 

		if(volunteer != null && outWorkbook(xsspath,workbook)) {
				return volunteer;
		}else {
				return null;
		}
			
	}
	
	//관리자 인증을 확인한다. 시트 1번에 저장되있는 내용을 확인한다.
	public PW cheackManager(String[] data) {
		
		int rowindex=0;
		int columnindex=0;

		XSSFSheet sheet= workbook.getSheetAt(1);
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=0;rowindex<rows;rowindex++){
		    //read the rows
		    XSSFRow row=sheet.getRow(rowindex);
		    if(row !=null){
		        
		        int cells=row.getPhysicalNumberOfCells();
		        for(columnindex=0;columnindex<=cells;columnindex++){
		            //read the cells
		            XSSFCell cell=row.getCell(columnindex);
		            String value="";
		            
		            if(cell==null){
		                continue;
		            }else{
		                //check cell type
		                switch (cell.getCellType()){
		                case NUMERIC:
		                    value= (int)cell.getNumericCellValue()+"";
		                    
		                    break;
		                case STRING:
		                    value=cell.getStringCellValue()+"";
		                    break;
						default:
							break;
		                }
		            }
		            System.out.println("Cell value :"+value);
		            if(value.equals (data[1])) {
		            	
		            	System.out.println("PW SUCCESS");
		            	
		            	return new PW(data[1]);
		            }
		        }
		    }
		}
	return null;
	}
	
private static XSSFWorkbook getWorkbook(String filePath) {
        
        fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        
        XSSFWorkbook wb = null;
        
        if(filePath.toUpperCase().endsWith(".XLSX")) {
            try {
                wb = new XSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        
        return wb;
        
    }

private static boolean outWorkbook(String filePath,XSSFWorkbook wb) {
	
	try {
		fos = new FileOutputStream(filePath); 
		workbook.write(fos);
        fos.close();
        System.out.println("저장성공");
        return true;
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("<ExcelManager.java> printStackTrace:Save Failed");
		return false;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("<ExcelManager.java>ioexception:Save Failed");
		return false;
	}
	
	}

}