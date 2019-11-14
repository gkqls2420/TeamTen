import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VolunteerManager {
	private Volunteer volunteer;
	private Mediator mediator;
	SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public VolunteerManager(Mediator mediator) {
		this.mediator = mediator;
	}
	
	//converse data string into volunteer
	public Volunteer converse(String[] data) {
		//input data:name, number,vStartDate,vEndDate, vInfo;+ remarks;	
		try {
			volunteer = new Volunteer(data[1], Integer.parseInt(data[2]),
					dataformat.parse(data[3]),dataformat.parse(data[4]),data[5]);
			
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(data.length ==7 && data[6] != null) {
			volunteer.setRemarks(data[6]);
		}
		
		return this.volunteer;
	}
}
