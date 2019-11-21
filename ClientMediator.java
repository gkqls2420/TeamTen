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
 
  public void playStart() {				// 카드의 유무에 따라 시작 방법이 다름
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
  
  
  public void handle(String state) {		// 카드 생성
	  CM.playCreateCard();
  }

  public void handle(Card card) {			// 카드 유효성 체크
	  if(Auth.auth_card(card) == false) {
		  System.out.println("Card info is not valid");
		  // 어떻게 할건지
	  } 
  }
  
  public void handle(String state, Volunteer vol) {		 // 봉사내용 입력
	  VM.playWriteVolunteer();
  }
  
  public void handle(Volunteer vol) {		// 봉사정보 유효성 체크
	  if(Auth.auth_volunteer(vol) == false) {
		  System.out.println("Volunteer info is not valid");
		  // 어떻게 할건지
	  }
		  
  }

}
