import java.sql.SQLException;

public class Mediator {
	private CardManager cardManager;
	//private DBMS dbms;
	private ExcelManager excelManager;
	private TCPManager tcpManager;
	private VolunteerManager volunteerManager;
	private Data data;
	
	public Mediator() {
		cardManager = new CardManager(this);
		//dbms = DBMS.getDBMS(this);
		excelManager = ExcelManager.getExcelManager(this);
		tcpManager = new TCPManager(this);
		volunteerManager = new VolunteerManager(this);
		
		tcpManager.run();
	}
	
	public Card handle(String state, Card data) throws SQLException {
		switch(state) {
			/*case "DBMS":
				return dbms.loadCards(data);*/
			case "Excel":
				return excelManager.WriteCardtoExcel(data);
			default:
				return null;
		}
	}
	
	public Mediator handle(String state, Volunteer data) throws SQLException {
		System.out.println(state + " " + data);
		switch(state) {
			/*case "DBMS":
				this.data = dbms.inputVolunteers(data);
				break;*/
			case "Excel":
				this.data = excelManager.WriteVoluenteertoExcel(data);
				break;
			default:
				this.data = null;
		}
		return this;
	}
	
	public Mediator handle(String state, String[] data) {
		switch(state) {
			case "Excel":
				this.data =  excelManager.checkManager(data);
				break;
			case "Volunteer":
				this.data = volunteerManager.converse(data);
				break;
			case "Card":
				this.data = cardManager.converse(data);
				break;
			default:
				this.data = null;
		}
		return this;
	}
	
	public Data getData() {
		return this.data;
	}
	
	public static void main(String[] args) {
		Mediator mediator = new Mediator();
	}
}
