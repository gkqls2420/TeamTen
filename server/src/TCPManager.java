import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;

public class TCPManager {
	private Mediator mediator;

	public TCPManager(Mediator mediator) {
		this.mediator = mediator;
	}

	public void run() {
		try {
			System.out.println("Open");
			ServerSocket listener = new ServerSocket(9091);
			while (true) {
				Socket socket = null;
				String[] data = null;
				Boolean result = false;
				socket = listener.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				while (socket.getInputStream() != null) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String str = reader.readLine();
					if(str == null)
						break;
					data = str.split("/");
					switch (data[0]) {
					case "CRD":
						Card card;
						Date date = new Date();
						//result = mediator.handle("Card", data).handle("DBMS", (Card) mediator.getData());
						card = (Card)mediator.handle("Card", data).handle("Excel", (Card)mediator.getData());
						out.println(card.registerDate);

						break;
					case "PW":
						result = mediator.handle("Excel", data).getData() != null;
						break;
					case "VO":
						/*result = mediator.handle("Volunteer", data).handle("DBMS", (Volunteer) mediator.getData())
								.handle("Excel", (Volunteer) mediator.getData()).getData() != null;
						*/
						result = mediator.handle("Volunteer", data).handle("Excel", (Volunteer)mediator.getData()).getData() != null;
						break;
					}
					
					// SERVER
					// ->new Date 포멧 양식을 정해야 함
					// 날짜를 서버로부터 전송받음

					System.out.println("Success");
					out.println(result.toString());
					out.flush();
				}
				socket.close();

			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} /*catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}