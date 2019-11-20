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

			mediator.playStart();				// playStart ����
			sendCard();							// ī�� ������ ������ ������.
			receiveValidCard();					// �����κ��� ī�� ��¥ ������ �޾ƿ´�.

			mediator.handle("write_volunteer", mediator.VM.VO);			// ���� ������ �Է��Ѵ�.
			
			
			String passwd = mediator.Auth.passwd;
			sendPasswd(passwd);					// �н����带 �����Ѵ�.


			if (receiveValidPasswd(passwd)) {					// �н����尡 ��ȿ�ϴٸ� ���������� ������. �ƴ϶�� ������ �ʴ´�
				sendVolunteer();
			} else {
				System.out.println("��й�ȣ�� �߸��Ǿ����ϴ�.");
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


	public void sendCard() {			// ������ ī�� ������ ����.

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

	public void receiveValidCard() {		 // �������� ī�� ������ �´� ��¥������ �о��

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
	
	public void sendPasswd(String passwd) {		// ������ �н����� ����
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

	

	public boolean receiveValidPasswd(String passwd) {		 //�н����� ��ȿ�� ���� ����

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
	
	public void sendVolunteer() {				 // ������ ���� ���� ����
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
