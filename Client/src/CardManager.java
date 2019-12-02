
import java.io.*;
import java.util.Date;

class Card{
	String name;
	String phone_num;
	String VMS_ID;
	String date;
}

public class CardManager
{
	
	ClientMediator mediator;
	Card card = new Card();

	public CardManager(ClientMediator m)
	{
	  mediator = m; 
	}
	
	public void create()
	{
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try{
		     card.name = reader.readLine();	    
		     card.phone_num = reader.readLine();		     
		     card.VMS_ID = reader.readLine();
		  
		} catch (IOException e){ 
		      System.err.println("error");
		}
		 
			System.out.println("카드를 만들었습니다");

	}
	
	boolean send_card(){
		// tcp manager에 카드정보를 보내 boolean값을 반환받는다.
		// 서버 DB에 카드정보가 있으면
		return true;
		
		// 서버 DB에 카드정보가 없으면 
		// return false
	}
	
}

