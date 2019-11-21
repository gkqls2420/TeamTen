import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CardManager {
	static private Card card = null;
	
	public CardManager(Mediator mediator) {
	}
	
	//converse data String into card
	public Card converse(String[] data) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			if(data.length == 5)
				card = new Card(data[1], Integer.parseInt(data[2]), transFormat.parse(data[3]));
			else
				card = new Card(data[1], Integer.parseInt(data[2]), transFormat.parse(data[3]), data[4]);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return card;
	}
}
