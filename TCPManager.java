import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPManager {

	ClientMediator mediator = new ClientMediator();
	private static Socket Socket;


	OutputStream os = null;
	OutputStreamWriter osw = null;
	BufferedWriter bw = null;

	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;

	public TCPManager() {
		String ip = "127.0.0.1";
		int port = 9091;
		try {
			Socket = new Socket(ip, port);
			System.out.println(ip + " connected");

			mediator.playStart();				// playStart 실행
			sendCard();							// 카드 정보를 서버에 보낸다.
			receiveValidCard();					// 서버로부터 카드 날짜 정보를 받아온다.

			mediator.handle("write_volunteer", mediator.VM.VO);			// 봉사 정보를 입력한다.
			
			
			String passwd = mediator.Auth.passwd;
			sendPasswd(passwd);					// 패스워드를 전송한다.


			if (receiveValidPasswd(passwd)) {					// 패스워드가 유효하다면 봉사정보를 보낸다. 아니라면 보내지 않는다
				sendVolunteer();
			} else {
				System.out.println("비밀번호가 잘못되었습니다.");
			}

			Socket.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if (!Socket.isClosed()) {
			try {
				is.close();
				os.close();
				Socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void sendCard() {			// 서버로 카드 정보를 보냄.

		try {
			os = Socket.getOutputStream();
			osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw);

			String Card_info = mediator.CM.CRD.tostringBuff();

			bw.write(Card_info);
			bw.flush();

			System.out.println("Card info is sent to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void receiveValidCard() {		 // 서버에서 카드 정보에 맞는 날짜정보를 읽어옴

		try {
			is = Socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			String card_date = br.readLine();
			card_date = "201911201216";
			mediator.CM.CRD.date = card_date;

			mediator.IO.refreshCardData(mediator.CM.CRD.date);

			System.out.println("Receive Date from server");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void sendPasswd(String passwd) {		// 서버에 패스워드 전송
		try {
			String password = "PW/" + passwd + "/";

			bw.write(password);
			bw.flush();

			System.out.println("Password is sent to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public boolean receiveValidPasswd(String passwd) {		 //패스워드 유효성 유무 리턴

		boolean result = false;
		try {
			String password = br.readLine();
			if (passwd.equals(password))
				result = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public void sendVolunteer() {				 // 서버에 봉사 정보 전송
		try {
			String Volunteer_info = mediator.VM.VO.tostringBuff();

			bw.write(Volunteer_info);
			bw.flush();

			System.out.println("Volunteering info is sent to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		TCPManager tcp = new TCPManager();
	}

}
