import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Authentication {
	ClientMediator mediator;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");

	public String passwd;

	public Authentication(ClientMediator m) {
		mediator = m;
	}

	boolean auth_volunteer(Volunteer vol) {	// 봉사 정보가 제대로 입력되었는지 확인.
		boolean result = true;
		
		if(vol.name.isEmpty() || vol.phone_num.isEmpty())
			return false;

		try {
			dateFormat.setLenient(false);
			dateFormat.parse(mediator.VM.VO.start_date);	// 시작 일자가 유효한 날짜인지 ex) 201912321100 불가
			dateFormat.parse(mediator.VM.VO.end_date);		// 끝 일자도 
			
		} catch (ParseException e) {
			return false; 
		}
		
		
		return result;
	}

	boolean auth_card(Card card) {	// 카드 정보가 제대로 입력되었는지 확인
		boolean result = true;
		
		if(card.name.isEmpty() || card.date.isEmpty())
			  return false;
	
		try {
			if (!isNumeric(mediator.CM.CRD.phone_num))
				return false;	// 전화번호가 숫자인지 확인
				
			dateFormat.setLenient(false);
			dateFormat.parse(mediator.CM.CRD.date);		// 저장 날짜가 유요한 날짜인지 확인
			
			
		} catch (ParseException e) {
			return false;
		}
		return result;
	}

	public void playSendPasswd() {					// 패스워드를 입력받아 서버로 보내기 위한 passwd 입력
		System.out.print("Enter password : ");
		Scanner in = new Scanner(System.in);
		passwd = in.nextLine();

	}

	public static boolean isNumeric(String input) {			// 숫자인지 확인하기 위한 메소드
		boolean result = true;
		try {
			Double.parseDouble(input);
			
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}

}
