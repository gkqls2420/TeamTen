import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VolunteerManager {
	static private Volunteer volunteer = null;
	
	public VolunteerManager(Mediator mediator) {
	}
	
	//converse data string into volunteer
	public Volunteer converse(String[] data) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			if(data.length == 6)
				volunteer = new Volunteer(data[1], Integer.parseInt(data[2]), transFormat.parse(data[3]), transFormat.parse(data[4]), data[5]);
			else
				volunteer = new Volunteer(data[1], Integer.parseInt(data[2]), transFormat.parse(data[3]), transFormat.parse(data[4]), data[5], data[6]);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return volunteer;
	}
}
