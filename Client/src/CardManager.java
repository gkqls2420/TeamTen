
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
		 
			System.out.println("ī�带 ��������ϴ�");

	}
	
	boolean send_card(){
		// tcp manager�� ī�������� ���� boolean���� ��ȯ�޴´�.
		// ���� DB�� ī�������� ������
		return true;
		
		// ���� DB�� ī�������� ������ 
		// return false
	}
	
}

