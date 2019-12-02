import java.io.File;

public class ClientMediator
{
  CardManager CM;
  VolunteerManager VM;
  Authentication Auth;
  FileIO IO;
  TCPManager TCP;
  
  File file = new File("card.data");
  
  public ClientMediator()
  {
	  CM =  new CardManager(this);
	  VM = new VolunteerManager(this);
	  Auth = new Authentication(this);
	  IO = new FileIO(this);
	  TCP = new TCPManager(this);
	  
	  start();
	  
	  
  }

  public void start() {
	  if(IO.checkBeforeFile(file)) {
		  System.out.println("카드가 있습니다");
		  IO.FileInput();
		  handle("auth_card");
		  handle("send_card");
		  handle("write_volunteer");
	  }
	  else {
		  System.out.println("카드가 없습니다");
		  handle("create_card");
		  IO.FileOutput();
		  handle("auth_card");
		  handle("send_card");
		  handle("write_volunteer");
		  
	  }
  }
  public void handle(String state)
  {
	 
	  if(state.equals("create_card")) {
		  CM.create();
	  }
	  else if(state.equals("auth_card")) {
		  Auth.auth_card();
	  }
	  else if(state.equals("write_volunteer")) {
		  VM.write_volunteer();
	  }
	  else if(state.equals("auth_volunteer")) {
		  Auth.auth_volunteer();
	  }
	  else if(state.equals("combine")){
		  VM.combine();
	  }
	  else if(state.equals("send_card")){
		  CM.send_card();
	  }
	  else if(state.equals("send_volunteer")) {
		  VM.send_volunteer();
	  }
	  
	  
  }

  public static void main(String args[])
	{
	  ClientMediator t = new ClientMediator();
		
	} 
  
  
 
}

