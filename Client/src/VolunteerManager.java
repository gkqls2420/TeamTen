import java.io.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Volunteer{
	String name;
	String phone_num;
	Date start_date;
	Date end_date;
	String contents;
}


public class VolunteerManager
{
	ClientMediator mediator;
	Volunteer vol = new Volunteer();
	
	public VolunteerManager(ClientMediator m)
	{
	  mediator = m;
	}
	
	public void send_volunteer()
	{
		
	}
	
	public void write_volunteer() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String start, finish;
		try {
			start = reader.readLine();
			SimpleDateFormat str_to_date = new SimpleDateFormat("yyyyMMddHHmm");
			vol.start_date = str_to_date.parse(start);
			
			finish = reader.readLine();
			vol.end_date = str_to_date.parse(finish);
			
			vol.contents = reader.readLine();	   
			
			System.out.println("봉사내용을 입력하였습니다.");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mediator.handle("combine");
	}
	
	public void combine() {

		vol.name = mediator.CM.card.name;
		vol.phone_num = mediator.CM.card.phone_num;
		
		mediator.handle("auth_volunteer");
		
		mediator.handle("send_volunteer");
		System.out.println("봉사기록이 서버로 전송되었습니다.");
	}
	 
	
}