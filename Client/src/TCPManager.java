
public class TCPManager {
	
	ClientMediator mediator;
	
	public TCPManager(ClientMediator m)
	{
	  mediator = m; 
	}
	
	boolean valid_card() {
		
		return true;
	}
	
	boolean valid_volunteer() {
		
		return true;
	}
	
	boolean valid_passwd() {
		
		return true;
	}
}
