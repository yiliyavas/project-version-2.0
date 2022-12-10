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
@WebServlet("/nftDAO")

public class nftDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public nftDAO() {}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
	
	private String getTime() {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		String datetime = String.format("%d::%d::%d %d::%d::%d", date.getYear(), date.getMonthValue(),
				date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond());

		return datetime;
	}
	
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
	 
	 protected void disconnect() throws SQLException {
	        if (connect != null && !connect.isClosed()) {
	        	connect.close();
	        }
	    }
	 
	 public nft getNFT(String name) throws SQLException {
	    	nft nft = null;
	        String sql = "SELECT * FROM nft WHERE email = ?";
	         
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, name);
	         
	        ResultSet resultSet = preparedStatement.executeQuery();
	         
	        if (resultSet.next()) {
	            String nftid = resultSet.getString("nftid");
	            //String nftName = resultSet.getString("nftName");
	            String url = resultSet.getString("url");
	            String creator = resultSet.getString("creator");
	            String owner = resultSet.getString("owner"); 
	            String mintTime = resultSet.getString("mintTime"); 
	            String description = resultSet.getString("description"); 
	            
	            nft = new nft(nftid, name, url, creator, owner,  mintTime,  description);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        return nft;
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
	 
	 
	 public List<nft> viewNFT(String nftid) throws SQLException {
			List<nft> resultNFTs = new ArrayList<nft>();
			String sql = "SELECT * FROM NFTs WHERE nftid = '" + nftid + "'";
		
			connect_func();
		
			statement = (Statement) connect.createStatement();
			ResultSet results = statement.executeQuery(sql);
		
			while (results.next()) {
				nft temp = new nft();
				temp.setNftid(results.getString("nftid"));
				temp.setName(results.getString("nftName"));
				temp.setUrl(results.getString("url"));
				temp.setCreator(results.getString("creator"));
				temp.setOwner(results.getString("owner"));
				temp.setMintTime(results.getString("mintTime"));
				temp.setDescription(results.getString("description"));
		
				resultNFTs.add(temp);
			}
		
			results.close();
			disconnect();
		
			return resultNFTs;
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
	 
	 public List<nft> ownedList(String nftOwner) throws SQLException {
			List<nft> resultNFTs = new ArrayList<nft>();
			String sql = "SELECT * FROM NFTs WHERE owner = '" + nftOwner + "'";
			connect_func();
		
			statement = (Statement) connect.createStatement();
			ResultSet results = statement.executeQuery(sql);
		
			while (results.next()) {
				nft temp = new nft();
				temp.setNftid(results.getString("nftid"));
				temp.setName(results.getString("nftName"));
				temp.setUrl(results.getString("url"));
				temp.setCreator(results.getString("creator"));
				temp.setOwner(results.getString("owner"));
				temp.setMintTime(results.getString("mintTime"));
				temp.setDescription(results.getString("description"));
		
				resultNFTs.add(temp);
			}
		
			results.close();
			disconnect();
		
			return resultNFTs;
		}
	 
	 public List<String> getNFTNames1() throws SQLException {
			String sql = "SELECT nftName FROM NFTs";
			List<String> names = new ArrayList<String>();
		
			connect_func();
		
			statement = connect.createStatement();
			ResultSet results = statement.executeQuery(sql);
		
			while (results.next()) {
				String name = results.getString("nftName");
				names.add(name);
			}
		
			results.close();
			disconnect();
		
			return names;
		}
	 
	 
	 public boolean transferNFT(String nftName, String targetUser, Double price) throws SQLException {
			// Need to add input validation
			//String nftid = userDAO.getNFTID(nftName);
		
			String sql = "UPDATE NFTs SET owner = ? WHERE nftid = ?";
		
			connect_func();
			
			String trans = String.format("SELECT nftid, owner FROM NFTs where nftName=\"%s\"", nftName);
		    statement = (Statement) connect.createStatement();
		
		    ResultSet results = statement.executeQuery(trans);
		    results.next();
		
		    //userDAO.insertTransaction(results.getInt("nftid"), price, 1, results.getString("owner"), targetUser);
		    
		    results.close();
			disconnect();
			
			connect_func();
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, targetUser);
			//preparedStatement.setString(2, nftid);
			preparedStatement.executeUpdate();
			disconnect();
			
			return true;
		}
	 
	 
	 public List<nft> mintedList(String nftCreator) throws SQLException {
			List<nft> resultNFTs = new ArrayList<nft>();
			String sql = "SELECT * FROM NFTs WHERE creator = '" + nftCreator + "'";
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
	 
	 
	 public List<nft> ownedNftList(String nftOwner) throws SQLException {
			List<nft> resultNFTs = new ArrayList<nft>();
			String sql = "SELECT * FROM NFTs WHERE owner = '" + nftOwner + "'";
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
	 
}
