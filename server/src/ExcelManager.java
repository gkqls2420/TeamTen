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
	private static XSSFWorkbook volunteerworkbook ;
	private static XSSFWorkbook cardworkbook ;
	private static XSSFWorkbook managerworkbook;
	
	private static FileInputStream fis=null;
	private static FileOutputStream fos =null;
	private final String xsspath = "C:\\Users\\wlvkd\\Desktop\\"; //write your xlsx file path plz;

	
	private final String volunteerfilepath ="봉사기록.xlsx";
	private final String cardfilepath= "봉사카드.xlsx";
	private final String managerfilepath ="관리자인증.xlsx";

	private ExcelManager(Mediator mediator) {
		
		volunteerworkbook = getWorkbook(xsspath+volunteerfilepath);
		cardworkbook = getWorkbook(xsspath + cardfilepath);
		managerworkbook = getWorkbook(xsspath + managerfilepath);
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
		XSSFSheet sheet=volunteerworkbook.getSheetAt(0);
		
		System.out.println("봉사 기록 체크....");
		
		int rowTotal = sheet.getLastRowNum();

		if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
		    rowTotal++;
		}
		 row=sheet.createRow((short)rowTotal);
		 CellStyle dateStyle = volunteerworkbook.createCellStyle();
		 CellStyle hourStyle = volunteerworkbook.createCellStyle();
		 CreationHelper createHelper = volunteerworkbook.getCreationHelper();
		 dateStyle.setDataFormat(
				 createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm"));	
		 hourStyle.setDataFormat( 
				 createHelper.createDataFormat().getFormat("hh:mm"));
         for(int cellstate = 0; cellstate< 8 ;cellstate++) {
        	 
        	 cell=row.createCell(cellstate);
        	 
        	 switch(cellstate) {
        	 
        	 case 0:
	
                 cell.setCellType(CellType.NUMERIC);
                 cell.setCellStyle(dateStyle);
                 cell.setCellValue(new Date());
                 break;
        	 case 1:
        		 
                 cell.setCellType(CellType.NUMERIC);
                 cell.setCellStyle( hourStyle);
                 cell.setCellValue(volunteer.vStartDate);
                 break;
        	 case 2:
        		 cell=row.createCell(cellstate);
                 cell.setCellType(CellType.NUMERIC);
                 cell.setCellStyle( hourStyle);
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
		 for(int i=0;i<8;i++) {
			 sheet.autoSizeColumn(i);
		 } 
		 //out to the excell and return check
 

		if(volunteer != null && outWorkbook(xsspath+volunteerfilepath,volunteerworkbook)) {
				return volunteer;
		}else {
				return null;
		}
			
	}
	
	//write the card to excel
	public Card WriteCardtoExcel(Card card) {
		
		
		XSSFRow row=null;
		XSSFCell cell=null;
		XSSFSheet sheet=volunteerworkbook.getSheetAt(0);
		
		int rowTotal = sheet.getLastRowNum();

		if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
		    rowTotal++;
		}
		 row=sheet.createRow((short)rowTotal);

		 CellStyle dateStyle = cardworkbook.createCellStyle();
		 CreationHelper createHelper = cardworkbook.getCreationHelper();	
		 dateStyle.setDataFormat(
	             createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm"));
		 
         for(int cellstate = 0; cellstate< 4 ;cellstate++) {
        	 
        	 cell=row.createCell(cellstate);
        	 
        	 switch(cellstate) {
        	 
        	 //card name
        	 case 0:
        		 cell.setCellValue(card.name);
                 break;
             //card.pNumber
        	 case 1:
        		 
        		 cell.setCellValue(card.pNumber);
        		 
        	//card.registerDate	 
        	 case 2:	 
        		 
        		 cell.setCellType(CellType.NUMERIC);
                 cell.setCellStyle(dateStyle);
                 cell.setCellValue(card.registerDate);
             //card.VMS_ID
        	 case 3:
        		 cell.setCellValue(card.getVMS_ID());
        	 
        	 }
        	
         }
         //cellsize auto rearrange
		 for(int i=0;i<4;i++) {
			 sheet.autoSizeColumn(i);
		 } 
		 //out to the excell and return check
 

		if(card != null && outWorkbook(xsspath + cardfilepath,cardworkbook)) {
				return card;
		}else {
				return null;
		}
			
	}
	
	//관리자 인증을 확인한다. 시트 1번에 저장되있는 내용을 확인한다.
	public PW cheackManager(String[] data) {
		
		int rowindex=0;
		int columnindex=0;

		XSSFSheet sheet= managerworkbook.getSheetAt(0);
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
        
        System.out.println(filePath+"확인중");
        try {
            fis = new FileInputStream(filePath);
            XSSFWorkbook wb = null;
            
            if(filePath.toUpperCase().endsWith(".XLSX")) {
                try {
                    wb = new XSSFWorkbook(fis);
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            
            return wb;
        } catch (FileNotFoundException e) {
        	
            throw new RuntimeException(e.getMessage(), e);
        }
        
        
        
    }

private static boolean outWorkbook(String filePath,XSSFWorkbook wb) {
	
	try {
		fos = new FileOutputStream(filePath); 
		wb.write(fos);
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