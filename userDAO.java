
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john1234");
            System.out.println(connect);
        }
    }
    
    private String getTime() {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		String datetime = String.format("%d::%d::%d %d::%d::%d", date.getYear(), date.getMonthValue(),
				date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond());

		return datetime;
	}
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            //int PPS_bal = resultSet.getInt("PPS_bal");

             
            user users = new user(email,firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, cash_bal);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, birthday,adress_street_num, adress_street,adress_city,adress_state,adress_zip_code,cash_bal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? )";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getBirthday());
			preparedStatement.setString(6, users.getAdress_street_num());		
			preparedStatement.setString(7, users.getAdress_street());		
			preparedStatement.setString(8, users.getAdress_city());		
			preparedStatement.setString(9, users.getAdress_state());		
			preparedStatement.setString(10, users.getAdress_zip_code());		
			preparedStatement.setDouble(11, users.getCash_bal());		
			//preparedStatement.setInt(12, users.getPPS_bal());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?,password = ?,birthday=?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, cash_bal=?, PPS_bal =? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAdress_street_num());		
		preparedStatement.setString(7, users.getAdress_street());		
		preparedStatement.setString(8, users.getAdress_city());		
		preparedStatement.setString(9, users.getAdress_state());		
		preparedStatement.setString(10, users.getAdress_zip_code());		
		preparedStatement.setDouble(11, users.getCash_bal());		
		//preparedStatement.setInt(12, users.getPPS_bal());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            double cash_bal = resultSet.getDouble("cash_bal");
           // int PPS_bal = resultSet.getInt("PPS_bal");
            user = new user(email, firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code,cash_bal);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists testdb; ",
		        "create database testdb; ",
		        "use testdb; ",
		        "drop table if exists User; ",
		        "drop table if exists nft; ",
		        ("CREATE TABLE if not exists User( " +
		            "email VARCHAR(50) NOT NULL, " + 
		            "firstName VARCHAR(10) NOT NULL, " +
		            "lastName VARCHAR(10) NOT NULL, " +
		            "password VARCHAR(20) NOT NULL, " +
		            "birthday DATE NOT NULL, " +
		            "adress_street_num VARCHAR(4) , "+ 
		            "adress_street VARCHAR(30) , "+ 
		            "adress_city VARCHAR(20)," + 
		            "adress_state VARCHAR(2),"+ 
		            "adress_zip_code VARCHAR(5),"+ 
		            "cash_bal DECIMAL(13,2) DEFAULT 1000,"+ 
		            "PPS_bal DECIMAL(13,2) DEFAULT 0,"+
		            "PRIMARY KEY (email) "+"); "),
				
				("CREATE TABLE IF NOT EXISTS NFTs(" + 
						"nftid INT AUTO_INCREMENT NOT NULL, " + 
						"nftName VARCHAR(100), " + 
						"url VARCHAR(200), " + 
						"creator VARCHAR(100), " + 
						"owner VARCHAR(100), " + 
						"mintTime DATETIME, " + 
						"description VARCHAR(500), " + 
						"PRIMARY KEY(nftid) " + ");"),
				
				("CREATE TABLE IF NOT EXISTS Transactions(" + 
						"transid INT AUTO_INCREMENT NOT NULL, " + 
						"owner VARCHAR(100), " + 
						"recipient VARCHAR(100), " + 
						"transtype CHAR(1), " + 
						"timestamp DATETIME, " + 
						"price DOUBLE, " + 
						"nftid INT, " + 
						"PRIMARY KEY(transid), " + 
						"FOREIGN KEY(owner) REFERENCES User(email), " + 
						"FOREIGN KEY(recipient) REFERENCES User(email)" + ");"),
				
				("CREATE TABLE IF NOT EXISTS Listings(" + 
						"listid INT AUTO_INCREMENT NOT NULL, " + 
						"owner VARCHAR(100), " + 
						"nftid INT, " + 
						"start DATETIME, " + 
						"end DATETIME, " + 
						"price DOUBLE, " + 
						"PRIMARY KEY(listid), " + 
						"FOREIGN KEY(owner) REFERENCES User(email), " + 
						"FOREIGN KEY(nftid) REFERENCES NFTS(nftid) " + ");")
		        
				};
String[] TUPLES = {("insert into User(email, firstName, lastName, password, birthday, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, cash_bal, PPS_bal)"+
		"values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2000-06-27', '1234', 'whatever street', 'detroit', 'MI', '48202','1000', '0'),"+
    		 	"('don@gmail.com', 'Don', 'Cummings','don123', '1969-03-20', '1000', 'hi street', 'mama', 'MO', '12345','1000', '0'),"+
    	 	 	"('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '1980-02-02', '1234', 'ivan street', 'tata','CO','12561','1000', '0'),"+
    		 	"('jo@gmail.com', 'Jo', 'Brady','jo1234', '2002-02-02', '3214','marko street', 'brat', 'DU', '54321','1000', '0'),"+
    		 	"('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '1971-06-15', '4500', 'frey street', 'sestra', 'MI', '48202','1000', '0'),"+
    		 	"('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '2000-03-14', '1245', 'm8s street', 'baka', 'IL', '48000','1000', '0'),"+
    			"('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '1999-06-15', '2468', 'yolos street', 'ides', 'CM', '24680','1000', '0'),"+
    			"('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '2021-06-14', '4680', 'egypt street', 'lolas', 'DT', '13579','1000', '0'),"+
    			"('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '1706-06-05', '1234', 'sign street', 'samo ne tu','MH', '09876','1000', '0'),"+
    			"('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '2001-04-24', '0981', 'snoop street', 'kojik', 'HW', '87654','1000', '0'),"+
    			"('root', 'default', 'default','pass1234', '1000-01-01', '0000', 'Default', 'Default', '0', '00000','1000','1000000000');")
    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    public String getNFTID(String name) throws SQLException {
		String sql = "SELECT nftid FROM NFTs WHERE nftName = ?";
		String nftid = "0";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, name);

		ResultSet results = preparedStatement.executeQuery();

		if (results.next())
			nftid = results.getString("nftid");

		results.close();
		disconnect();

		return nftid;
	}
    
    
    public String getPrice(String nftid) throws SQLException {
		String sql = "SELECT price FROM NFTs WHERE nftid = ?"; //was listing, got fixed to different table
		String price = "0";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, nftid);

		ResultSet results = preparedStatement.executeQuery();

		if (results.next())
			price = results.getString("price");

		results.close();
		disconnect();

		return price;
	}

    
    
    public List<String> getUsernames() throws SQLException {
		List<String> usernames = new ArrayList<String>();
		String sql = "SELECT email FROM User";

		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			usernames.add(results.getString("email"));
		}

		results.close();
		disconnect();

		return usernames;
	}
    
    
    public boolean submitListing(String nftName, String user, String daysAvailable, String price) throws SQLException {
		String sql = "INSERT INTO Listings(owner, nftid, start, end, price) VALUES (?, ?, ?, ?, ?)";
		String getId = String.format("SELECT nftid FROM NFTs WHERE nftName=\"%s\"", nftName);
		LocalDate startDate = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDate endDate = startDate.plusDays(Integer.parseInt(daysAvailable));

		connect_func();

		statement = (Statement) connect.createStatement();

		ResultSet results = statement.executeQuery(getId);
		results.next();
		
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, user);
		preparedStatement.setInt(2, results.getInt("nftid"));
		preparedStatement.setString(3, startDate + " " + time);
		preparedStatement.setString(4, endDate + " " + time);
		preparedStatement.setString(5, price);
		
		preparedStatement.executeUpdate();
		
		results.close();
		disconnect();

		return true;
	}
    
    
    public boolean changeBalance(String user, double change) throws SQLException {
		// Need to add input validation
		String sql = "UPDATE user SET balance = ? WHERE email = ?";
		String getBal = String.format("SELECT balance FROM user WHERE email = \"%s\"", user);
		double balance;

		connect_func();
		
        statement = (Statement) connect.createStatement();

        ResultSet results = statement.executeQuery(getBal);
        results.next();

        balance = results.getDouble("balance");
        
        System.out.println("you balance is: " + balance);
        
        results.close();
		disconnect();
		
		double total = balance + change;
		
		System.out.println("change and balance" + change + " & " + total);
		
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setDouble(1, balance + change);
		preparedStatement.setString(2, user);
		preparedStatement.executeUpdate();
		disconnect();
		
		return true;
	}

    
    public List<user> searchUser(String email) throws SQLException {
		List<user> listUser = new ArrayList<user>();
		String sql = "SELECT * FROM user WHERE email = '" + email + "'";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			email = resultSet.getString("email");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String password = resultSet.getString("password");
			String birthday = resultSet.getString("birthday");
			
			String adress_street_num = resultSet.getString("adress_street_num");
			String adress_street = resultSet.getString("adress_street");
			String adress_city = resultSet.getString("adress_city");
			String adress_state = resultSet.getString("adress_state");
			String adress_zip_code = resultSet.getString("adress_zip_code");
			double balance = resultSet.getDouble("balance");

		    user users = new user(email, firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, balance);

			listUser.add(users);
		}
		resultSet.close();
		disconnect();
		return listUser;
	}
    
    
    public boolean insertTransaction(int nftid, double price, int transType, String origin, String recipient) throws SQLException {
        String sql = "INSERT INTO Transactions(origin, recipient, nftid, transType, timestamp, price) VALUES (?, ?, ?, ?, ?, ?);";

        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, origin);
        preparedStatement.setString(2, recipient);
        preparedStatement.setInt(3, nftid);
        preparedStatement.setInt(4, transType);
        preparedStatement.setString(5, getTime());
        preparedStatement.setDouble(6, price);

        preparedStatement.executeUpdate();
        preparedStatement.close();

        disconnect();

        return true;
    }
    
    
    public List<transactions> transactionList(String user) throws SQLException {
		List<transactions> resultTrans = new ArrayList<transactions>();
		String sql = "SELECT * FROM transactions WHERE origin = '" + user + "' AND transtype = 1";
		connect_func();

		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			transactions trans = new transactions();
			trans.setTransid(results.getInt("transid"));
			trans.setOrigin(results.getString("origin"));
			trans.setNftid(results.getInt("nftid"));
			trans.setRecipient(results.getString("recipient"));
			trans.setTranstype(results.getInt("transtype"));
			trans.setTimestamp(results.getString("timestamp"));
			trans.setPrice(results.getDouble("price"));

			resultTrans.add(trans);
		}

		results.close();
		disconnect();

		return resultTrans;
	}
    
    public List<transactions> transactionList(int nftid) throws SQLException {
		List<transactions> resultTrans = new ArrayList<transactions>();
		String sql = "SELECT * FROM transactions WHERE nftid =" + nftid;
		connect_func();

		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			transactions trans = new transactions();
			trans.setTransid(results.getInt("transid"));
			trans.setOrigin(results.getString("origin"));
			trans.setNftid(results.getInt("nftid"));
			trans.setRecipient(results.getString("recipient"));
			trans.setTranstype(results.getInt("transtype"));
			trans.setTimestamp(results.getString("timestamp"));
			trans.setPrice(results.getDouble("price"));

			resultTrans.add(trans);
		}

		results.close();
		disconnect();

		return resultTrans;
	}
    
    
    public List<nft> searchNFT(int nftid) throws SQLException
	{
	    List<nft> resultNFTs = new ArrayList<nft>();
	    String sql = "SELECT * FROM NFTs WHERE nftid ='" + nftid + "'";
	    
	    connect_func();
	    
	    statement = (Statement) connect.createStatement();
	    ResultSet results = statement.executeQuery(sql);
	    
	    while (results.next()){
	        nft nft = new nft();
	        nft.setNftid(results.getString("nftid"));
	        nft.setName(results.getString("nftName"));
	        nft.setUrl(results.getString("url"));
	        nft.setCreator(results.getString("creator"));
	        nft.setOwner(results.getString("owner"));
	        nft.setMintTime(results.getString("mintTime"));
	        
	        resultNFTs.add(nft);
	    }
	    
	    results.close();
	    disconnect();
	    
	    return resultNFTs;
	}
    
    
    public List<nft> searchNFT(String nftName) throws SQLException {
		List<nft> resultNFTs = new ArrayList<nft>();
		String sql = "SELECT * FROM NFTs WHERE nftName LIKE '" + nftName + "%'";
	
		connect_func();
	
		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);
	
		while (results.next()) {
			nft nft = new nft();
			nft.setNftid(results.getString("nftid"));
			nft.setName(results.getString("nftName"));
			nft.setUrl(results.getString("url"));
			nft.setCreator(results.getString("creator"));
			nft.setOwner(results.getString("owner"));
			nft.setMintTime(results.getString("mintTime"));
			nft.setDescription(results.getString("description"));
	
			resultNFTs.add(nft);
		}
	
		results.close();
		disconnect();
	
		return resultNFTs;
	}
    
    public List<transactions> getBought(String user) throws SQLException {
        List<transactions> resultTransactions = new ArrayList<transactions>();
        String sql = "SELECT * FROM Transactions WHERE recipient='"+user+"' AND transtype=1";
        connect_func();

		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			transactions trans = new transactions();
			trans.setTransid(results.getInt("transid"));
			trans.setOrigin(results.getString("origin"));
			trans.setNftid(results.getInt("nftid"));
			trans.setRecipient(results.getString("recipient"));
			trans.setTranstype(results.getInt("transtype"));
			trans.setTimestamp(results.getString("timestamp"));
			trans.setPrice(results.getDouble("price"));
			
			resultTransactions.add(trans);
		}
		
		results.close();
		disconnect();
		
		for(transactions trans : resultTransactions) {
			trans.setNftName(this.searchNFT(trans.getNftid()).get(0).getName());

		}
		
		
        return resultTransactions;
    }
    
    
    public List<transactions> getSold(String user) throws SQLException {
        List<transactions> resultTransactions = new ArrayList<transactions>();
        String sql = "SELECT * FROM Transactions WHERE origin='"+user+"' AND transtype=1";
        connect_func();

		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			transactions trans = new transactions();
			trans.setTransid(results.getInt("transid"));
			trans.setOrigin(results.getString("origin"));
			trans.setNftid(results.getInt("nftid"));
			trans.setRecipient(results.getString("recipient"));
			trans.setTranstype(results.getInt("transtype"));
			trans.setTimestamp(results.getString("timestamp"));
			trans.setPrice(results.getDouble("price"));
			
			resultTransactions.add(trans);
		}

		results.close();
		disconnect();
		
		for(transactions trans : resultTransactions) {
			trans.setNftName(this.searchNFT(trans.getNftid()).get(0).getName());
		}

        return resultTransactions;
    }
	
    
    
    public boolean mintNFT(String name, String url, String currUser, String description) throws SQLException {
		String sql = "INSERT INTO NFTs(nftName, url, creator, owner, minttime, description) VALUES (?, ?, ?, ?, ?, ?);";
	
	    connect_func();
	    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	    preparedStatement.setString(1, name);
	    preparedStatement.setString(2, url);
	    preparedStatement.setString(3, currUser);
	    preparedStatement.setString(4, currUser);
	    preparedStatement.setString(5, getTime());
	    preparedStatement.setString(6, description);
	
	    preparedStatement.executeUpdate();
	    preparedStatement.close();
	
	    sql = String.format("SELECT nftid FROM NFTs where nftName=\"%s\"", name);
	    statement = (Statement) connect.createStatement();
	
	    ResultSet results = statement.executeQuery(sql);
	    results.next();
	
	    //userDAO.insertTransaction(results.getInt("nftid"), 0, 0, current_user, current_user);
	
	    results.close();
	    disconnect();
	
	
	
	    return true;
	}
    
    
    
    public List<String> getNFTNames() throws SQLException {
		String sql = "SELECT nftName FROM NFTs";
		List<String> NFTnames = new ArrayList<String>();
	
		connect_func();
	
		statement = connect.createStatement();
		ResultSet results = statement.executeQuery(sql);
	
		while (results.next()) {
			String nftName = results.getString("nftName");
			NFTnames.add(nftName);
		}
	
		results.close();
		disconnect();
	
		return NFTnames;
	}
    
    
    
    public List<listing> viewListings() throws SQLException {
		List<listing> listings = new ArrayList<listing>();
		String sql = "SELECT * FROM Listings";

		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			listing list = new listing();

			list.listid = results.getString("listid");
			list.owner = results.getString("owner");
			list.nftid = results.getString("nftid");
			list.startTime = results.getString("start");
			list.endTime = results.getString("end");
			list.price = results.getString("price");

			listings.add(list);
		}

		results.close();
		disconnect();

		return listings;
	}
    
    
    


}
