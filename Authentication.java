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

	boolean auth_volunteer(Volunteer vol) {	// ���� ������ ����� �ԷµǾ����� Ȯ��.
		boolean result = true;
		
		if(vol.name.isEmpty() || vol.phone_num.isEmpty())
			return false;

		try {
			dateFormat.setLenient(false);
			dateFormat.parse(mediator.VM.VO.start_date);	// ���� ���ڰ� ��ȿ�� ��¥���� ex) 201912321100 �Ұ�
			dateFormat.parse(mediator.VM.VO.end_date);		// �� ���ڵ� 
			
		} catch (ParseException e) {
			return false; 
		}
		
		
		return result;
	}

	boolean auth_card(Card card) {	// ī�� ������ ����� �ԷµǾ����� Ȯ��
		boolean result = true;
		
		if(card.name.isEmpty() || card.date.isEmpty())
			  return false;
	
		try {
			if (!isNumeric(mediator.CM.CRD.phone_num))
				return false;	// ��ȭ��ȣ�� �������� Ȯ��
				
			dateFormat.setLenient(false);
			dateFormat.parse(mediator.CM.CRD.date);		// ���� ��¥�� ������ ��¥���� Ȯ��
			
			
		} catch (ParseException e) {
			return false;
		}
		return result;
	}

	public void playSendPasswd() {					// �н����带 �Է¹޾� ������ ������ ���� passwd �Է�
		System.out.print("Enter password : ");
		Scanner in = new Scanner(System.in);
		passwd = in.nextLine();

	}

	public static boolean isNumeric(String input) {			// �������� Ȯ���ϱ� ���� �޼ҵ�
		boolean result = true;
		try {
			Double.parseDouble(input);
			
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}

}
