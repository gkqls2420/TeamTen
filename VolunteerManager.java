import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VolunteerManager {
	ClientMediator mediator;

	Volunteer VO;

	public VolunteerManager(ClientMediator m) {
		mediator = m;
		VO = new Volunteer();

	}

	public void playWriteVolunteer() {				// 봉사 정보 입력 후 봉사 클래스에 저장
		Scanner in = new Scanner(System.in);

		VO.start_date = in.nextLine();
		VO.end_date = in.nextLine();
		VO.contents = in.nextLine();

		
		
		combine();							// 카드의 이름, 전화번호를 불러와 봉사 클래스에 저장
		
		mediator.handle(VO);				// 봉사 정보 유효성 판단
		
		mediator.Auth.playSendPasswd();
		
		
	}

	public void combine() {

		VO.name = mediator.CM.CRD.name;
		VO.phone_num = mediator.CM.CRD.phone_num;
		

	}

}