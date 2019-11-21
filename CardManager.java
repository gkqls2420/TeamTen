
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CardManager {

	ClientMediator mediator;
	Card CRD;
	long time = System.currentTimeMillis();
	SimpleDateFormat daytime = new SimpleDateFormat("yyyyMMddHHmm");

	public CardManager(ClientMediator m) {
		mediator = m;
		CRD = new Card();
	}

	public void playCreateCard() {			 // 사용자로부터 입력받아 카드 클래스에 정보를 저장

		Scanner in = new Scanner(System.in);

		CRD.name = in.nextLine();
		CRD.phone_num = in.nextLine();
		CRD.VMS_ID = in.nextLine();
		CRD.date = daytime.format(time);

		System.out.println("Card is created");
	}

}
