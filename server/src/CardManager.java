import java.text.ParseException;
import java.text.SimpleDateFormat;


public class CardManager {
	private Card card;
	Mediator mediator;
	SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public CardManager(Mediator mediator) {
		this.mediator = mediator;
		
	}
	
	//converse data String into card
	public Card converse(String[] data)  {
		//data set : name,pNumber,date + vMsID;
		try {
			card = new Card(data[1], Integer.parseInt(data[2]),
					dataformat.parse(data[3]));
			
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(data.length ==5 && data[4] != null) {
			card.setVMS_ID(data[4]);
		}
		
		return this.card;
	}
}
