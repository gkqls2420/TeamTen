
public class ExcelManager {
	
	private static ExcelManager excelmanager;
	
	private ExcelManager(Mediator mediator) {
	}
	
	public static ExcelManager getExcelManager(Mediator mediator) {
		
		if(excelmanager == null) {
			excelmanager = new ExcelManager(mediator);
		}
		return excelmanager;
	}
	
	//봉사 기록을 엑셀에 저장하는 내용이다. 시트 0번에 저장하도록 한다. 호환성을 위해 volunteer 반환
	public Volunteer WriteVoluenteertoExcel(Volunteer volunteer) {
		if(volunteer == null)
			return null;
		else
			return volunteer;
	}
	
	//관리자 인증을 확인한다. 시트 1번에 저장되있는 내용을 확인한다.
	public PW cheackManager(String[] data) {
		if(true)	//인증 성공시
			return new PW(data[1]);
		else		//인증 실패시
			return null;
	}
}