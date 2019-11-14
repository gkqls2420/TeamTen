import java.sql.*;
public class DBMS {
	String DB_URL;
	String DB_USER;
	String DB_PASSWORD;
	public Connection con = null;
	public Statement stmt = null;
	public CallableStatement cstmt = null;
	public ResultSet rs = null;
	public int cnt = 1;
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
	public boolean loadCards(Card card) throws SQLException{	
		
			String query = "select * from card where name = card.name and pNumber = card.phonenumber";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next())
				return true;
			else
				return inputCards(card);
		
	}
	
	//put cards into table, return true if it was successful, else return false.
	public boolean inputCards(Card card) throws SQLException
	{
		
		
		if(card.getVMS_ID() != null){
			String query = "insert into card(name, phonenumber,vmsid) values('"+card.name+"','"+card.pNumber+"','"+card.getVMS_ID()+"');";
		
			stmt = con.createStatement();
			cnt=stmt.executeUpdate(query);
		}
		else{
			String query = "insert into card(name, phonenumber) values('"+card.name+"','"+card.pNumber+");";
			stmt = con.createStatement();
			cnt=stmt.executeUpdate(query);
		}
		if(cnt==0)
			return false;
		else
			return true;
		
	}
	
	//put volunteers' info into table, return true if it was successful, else return false.
	public Volunteer inputVolunteers(Volunteer volunte) throws SQLException
	{
		int card_cardnumber;
		String query1 = "select cardnumber from card where name ='"+card.name +"' and phonenumber ="+ card.pNumber;
		stmt = con.createStatement();
		rs = stmt.executeQuery(query1);
		if(rs.next())
			card_cardnumber= rs.getInt("cardnumber");
		
		else 
			return volunte;
		
		String query2 = "insert into volunteer(starttime, endtime, content) values('"+card_cardnumber+"','"+volunte.vStartDate+"','"+volunte.vEndDate+"'"+"','"+volunte.vInfo+");";
		stmt = con.createStatement();
		cnt=stmt.executeUpdate(query2); 
		if(cnt==0)
			return null; 
		else
			return volunte;
		
	}
	
	
}
