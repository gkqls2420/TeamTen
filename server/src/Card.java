import java.util.Date;

public class Card extends Data{
	public final String name;
	public final int pNumber;
	public Date registerDate;
	private String VMS_ID;
	
	public Card(String name, int number, Date date)
	{
		this.name = name;
		pNumber = number;
		registerDate = date;
		setVMS_ID(null);
	}
	
	public Card(String name, int number, Date date, String VMS_ID)
	{
		this.name = name;
		pNumber = number;
		registerDate = date;
		this.setVMS_ID(VMS_ID);
	}

	public String getVMS_ID() {
		return VMS_ID;
	}

	public void setVMS_ID(String vMS_ID) {
		this.VMS_ID = vMS_ID;
	}
}

