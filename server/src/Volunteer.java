import java.util.Date;

public class Volunteer extends Data {
	public final String name;
	public final int number;
	public final Date vStartDate;	//봉사 시작일시
	public final Date vEndDate;
	public final String vInfo;		//봉사 내용
	private String remarks;			//비고
	
	public Volunteer(String name, int num, Date sDate, Date eDate, String info)
	{
		this.name = name;
		this.number = num;
		vStartDate = sDate;
		vEndDate = eDate;
		vInfo = info;
	}
	
	public Volunteer(String name, int num, Date sDate, Date eDate, String info, String remarks)
	{
		this.name = name;
		this.number = num;
		vStartDate = sDate;
		vEndDate = eDate;
		vInfo = info;
		this.setRemarks(remarks);
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}

