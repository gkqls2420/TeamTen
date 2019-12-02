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

	public void playWriteVolunteer() {				// ���� ���� �Է� �� ���� Ŭ������ ����
		Scanner in = new Scanner(System.in);

		VO.start_date = in.nextLine();
		VO.end_date = in.nextLine();
		VO.contents = in.nextLine();

		
		
		combine();							// ī���� �̸�, ��ȭ��ȣ�� �ҷ��� ���� Ŭ������ ����
		
		mediator.handle(VO);				// ���� ���� ��ȿ�� �Ǵ�
		
		mediator.Auth.playSendPasswd();
		
		
	}

	public void combine() {

		VO.name = mediator.CM.CRD.name;
		VO.phone_num = mediator.CM.CRD.phone_num;
		

	}

}