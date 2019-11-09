public class Mediator {
	private CardManager cardManager;
	private DBMS dbms;
	private ExcelManager excelManager;
	private TCPManager tcpManager;
	private VolunteerManager volunteerManager;
	private Data data;
	
	public Mediator() {
		cardManager = new CardManager(this);
		dbms = DBMS.getDBMS(this);
		excelManager = ExcelManager.getExcelManager(this);
		tcpManager = new TCPManager(this);
		volunteerManager = new VolunteerManager(this);
		
		tcpManager.run();
	}
	
	public boolean handle(String state, Card data) {
		switch(state) {
			case "DBMS":
				return dbms.loadCards(data);
			default:
				return false;
		}
	}
	
	public Mediator handle(String state, Volunteer data) {
		switch(state) {
			case "DBMS":
				this.data = dbms.inputVolunteers(data);
				break;
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
				this.data =  excelManager.cheackManager(data);
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
