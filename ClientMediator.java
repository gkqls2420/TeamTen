import java.io.File;
import java.io.IOException;

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
  
  }
 
  public void playStart() {				// ī���� ������ ���� ���� ����� �ٸ�
	  if (IO.checkBeforeFile(file)) {
			System.out.println("Card is exist");
			IO.FileInput();
		} else {
			System.out.println("Card is not exist");
			handle("create_card");
			IO.FileOutput();
			
		}
	  
	  handle(CM.CRD);
  }
  
  
  public void handle(String state) {		// ī�� ����
	  CM.playCreateCard();
  }

  public void handle(Card card) {			// ī�� ��ȿ�� üũ
	  if(Auth.auth_card(card) == false) {
		  System.out.println("Card info is not valid");
		  // ��� �Ұ���
	  } 
  }
  
  public void handle(String state, Volunteer vol) {		 // ���系�� �Է�
	  VM.playWriteVolunteer();
  }
  
  public void handle(Volunteer vol) {		// �������� ��ȿ�� üũ
	  if(Auth.auth_volunteer(vol) == false) {
		  System.out.println("Volunteer info is not valid");
		  // ��� �Ұ���
	  }
		  
  }

}
