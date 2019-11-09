import java.sql.*;
public class DBMS {
	String DB_URL;
	String DB_USER;
	String DB_PASSWORD;
	public Connection con;
	public Statement stmt;
	public CallableStatement cstmt;
	public ResultSet rs;
	private static DBMS dbms;
	private Card card;
	private Volunteer volunte;
	
	
	public static DBMS getDBMS(Mediator mediator) {
		
		if(dbms == null) {
			dbms = new DBMS(mediator);
		}
		return dbms;
	}

	
	private DBMS(Mediator mediator)
	{
		//fill these informations later
		DB_URL = "jdbc:mariadb://127.0.0.1:3306/xxxx";
		DB_USER = "xxx";
		DB_PASSWORD = "xxxxxxxx";
		con = null;
		stmt = null;
		cstmt = null;
		rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			//System.out.println("Driver Loading Success");
		}
		catch ( ClassNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			//System.out.println("Database Connection Success");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//execute query to get card informations
	public boolean loadCards(Card card)
	{
		//select * from card_table where name = name and pNumber = phone_num
		//if nothing selected, return inputCards(card);
		//else if card != selected card, return false
		//else	/*same*/
		return true;
	}
	
	//put cards into table, return true if it was successful, else return false.
	public boolean inputCards(Card card)
	{
		return true;
	}
	
	//put volunteers' info into table, return true if it was successful, else return false.
	public Volunteer inputVolunteers(Volunteer volunte)
	{
		//use trigger and return the result
		return volunte;
	}
}
