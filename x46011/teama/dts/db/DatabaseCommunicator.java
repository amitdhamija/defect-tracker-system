package x46011.teama.dts.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import x46011.teama.dts.model.Constants;
import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.DefectPriority;
import x46011.teama.dts.model.DefectStatus;
import x46011.teama.dts.model.User;


/**
 * DTSCommManager handles interactions between the UI and the DB Communicator
 * 
 * @author Travis Cribbet
 * @author Kevin Alexander
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Kevin Alexander: Removed unneccesary functions
 * @revision 1.2	Kevin Alexander: Renamed function using "Person" to user "User" to better fit domain terminology 
 * @revision 1.3	Kevin Alexander: Moved main return statements in getUser and getStatus outside of while loop so
 * 									 that the connection is closed prior to function return
 * @revision 1.4	Kevin Alexander: Implemented getDefect 
 * @revision 1.5	Amit Dhamija: Simplified and optimized the db class; fixed db errors
 * 					Amit Dhamija: Removed connection code from various method to avoid unnecessary/multiple connections to the db
 * 					Amit Dhamija: Added getConnection() method to use instead
 * 					
 */
public class DatabaseCommunicator implements IDatabaseCommunicator {
	
	private Connection connection;
	
	public DatabaseCommunicator() {
		String createDatabase = "CREATE DATABASE IF NOT EXISTS " + Constants.DB_NAME;
		String createDefectTable = "CREATE TABLE IF NOT EXISTS defect " +
				"(id INTEGER AUTO_INCREMENT, " +
				" date timestamp DEFAULT CURRENT_TIMESTAMP, " + 
				" submitter_id INTEGER not NULL, " + 
				" assignee_id INTEGER not NULL, " + 
				" priority_id INTEGER not NULL, " + 
				" status_id INTEGER not NULL, " + 
				" summary VARCHAR(100), " + 
				" description VARCHAR(4000), " + 
				" PRIMARY KEY ( id ))";
		
		String createUserTable = "CREATE TABLE IF NOT EXISTS user " +
				"(id INTEGER AUTO_INCREMENT, " +
				" first VARCHAR(255), " + 
				" last VARCHAR(255), " + 
				" email VARCHAR(255), " + 
				" PRIMARY KEY ( id ))";
		
		String createPriorityTable = "CREATE TABLE IF NOT EXISTS priority " +
				"(id INTEGER AUTO_INCREMENT, " +
				" value VARCHAR(32), " + 
				" PRIMARY KEY ( id ))";
		
		String createStatusTable = "CREATE TABLE IF NOT EXISTS status " +
				"(id INTEGER AUTO_INCREMENT, " +
				" value VARCHAR(32), " + 
				" PRIMARY KEY ( id ))";
		
		ResultSet resultSet;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection =  DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
			Statement statement = connection.createStatement();
			statement.executeUpdate(createDatabase);
			statement.executeUpdate("USE " + Constants.DB_NAME);
			statement.executeUpdate(createDefectTable);
			statement.executeUpdate(createUserTable);
			statement.executeUpdate(createPriorityTable);
			statement.executeUpdate(createStatusTable);
			
			statement.execute("SELECT * FROM priority");
			resultSet = statement.getResultSet();
			if (resultSet != null) {
				//if table is empty
				if(!resultSet.next()) {
					for (DefectPriority priority:DefectPriority.values()) {
						String insert = "INSERT INTO priority (value) " + 
					            "VALUES ('" + priority.name()  + "')";
						statement.executeUpdate(insert);
					}
				}
			}
			
			statement.execute("SELECT * FROM status");
			resultSet = statement.getResultSet();
			if (resultSet != null) {
				//if table is empty
				if(!resultSet.next()) {
					for (DefectStatus status:DefectStatus.values()) {
						String insert = "INSERT INTO status (value) " + 
					            "VALUES ('" + status.name()  + "')";
						statement.executeUpdate(insert);
					}
				}
			}
			
			statement.execute("SELECT * FROM user");
			resultSet = statement.getResultSet();
			if (resultSet != null) {
				//if table is empty
				if(!resultSet.next()) {
					String sql = "INSERT INTO user (first, last, email) VALUES ('Amit', 'Dhamija', 'khiladi.ad@gmail.com')";
					statement.executeUpdate(sql);
					sql = "INSERT INTO user (first, last, email) VALUES ('Kevin', 'Alexander', 'kevin.gregory.alexander@gmail.com')";
					statement.executeUpdate(sql);
					sql = "INSERT INTO user (first, last, email) VALUES ('Thomas', 'Hargrove', 'Thomas.R.Hargrove@gmail.com')";
					statement.executeUpdate(sql);
					sql = "INSERT INTO user (first, last, email) VALUES ('Travis', 'Cribbet', 'tcribbet@gmail.com')";
					statement.executeUpdate(sql);
					sql = "INSERT INTO user (first, last, email) VALUES ('Kesha', 'Smith', 'KeshaS@comcast.net')";
					statement.executeUpdate(sql);
				}
			}
			
			statement.execute("SELECT * FROM defect");
			resultSet = statement.getResultSet();
			if (resultSet != null) {
				//if table is empty
				if(!resultSet.next()) {
					String sql = "INSERT INTO defect (submitter_id, assignee_id, priority_id, status_id, summary, description) VALUES (1,2,1,3,'Bad stuff happened', 'CPU pegs 100% and remains until forced to terminate')";
					statement.executeUpdate(sql);
					sql = "INSERT INTO defect (submitter_id, assignee_id, priority_id, status_id, summary, description) VALUES (2,4,2,1,'Terrible stuff happened', 'CPU exploded in a shower of sparks and silicon')";
					statement.executeUpdate(sql);
					sql = "INSERT INTO defect (submitter_id, assignee_id, priority_id, status_id, summary, description) VALUES (3,1,3,1,'Display issue', 'Red pixel #8302 should be blue')";
					statement.executeUpdate(sql);
					sql = "INSERT INTO defect (submitter_id, assignee_id, priority_id, status_id, summary, description) VALUES (4,3,1,2,'Intermittent unintended data export', 'Occasionally, while app is running, a slice of swiss cheese is ejected from the DVD drive.')";
					statement.executeUpdate(sql);
				}
			}
			
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("Class not found: " + ex);
		}
	}

	private Connection getConnection() {
		try {
			if(connection == null || connection.isClosed())
				connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
			return connection;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return null;
		}
	}
	
	private int getPriorityId(DefectPriority priority) {
		int id = 0;
		try {
			Statement statement = getConnection().createStatement();
			String sql = "SELECT * FROM priority WHERE value='" + priority.name() + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					id = resultSet.getInt("id");
				}
			}
			return id;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return id;
		}
	}
	
	private DefectPriority getPriority(int id) {
		DefectPriority priority = null;
		try {
			Statement statement = getConnection().createStatement();
			String sql = "SELECT * FROM priority WHERE id=" + id;
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					priority = DefectPriority.valueOf(resultSet.getString("value"));
				}
			}
			return priority;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return priority;
		}
	}
	
	private int getStatusId(DefectStatus status) {
		int id = 0;
		try {
			Statement statement = getConnection().createStatement();
			String sql = "SELECT * FROM status WHERE value='" + status.name() + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					id = resultSet.getInt("id");
				}
			}
			return id;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return id;
		}
	}
	
	private DefectStatus getStatus(int id) {
		DefectStatus status = null;
		try {
			Statement statement = getConnection().createStatement();
			String sql = "SELECT * FROM status WHERE id=" + id;
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					status = DefectStatus.valueOf(resultSet.getString("value"));
				}
			}
			return status;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return status;
		}
	}
	
	private int getUserId(User user) {
		int id = 0;
		try {
			Statement statement = getConnection().createStatement();
			String sql = "SELECT * FROM user WHERE first='" + user.getFirstName() + "' AND last='" + user.getLastName() + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					id = resultSet.getInt("id");
				}
			}
			return id;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return id;
		}
	}
	
	private User getUser(int id) {
		User user = null;
		try {
			Statement statement = getConnection().createStatement();
			String sql = "SELECT * FROM user WHERE id=" + id;
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					String first = resultSet.getString("first");
					String last = resultSet.getString("last");
					String email = resultSet.getString("email");
					user = new User(first, last, email);
					user.setId(id);
				}
			}
			return user;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return user;
		}
	}
	
	public List<DefectPriority> getPriorityList() {
		List<DefectPriority> priorityList = new ArrayList<DefectPriority>();
		try {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("USE " + Constants.DB_NAME);
			String sql = "SELECT * FROM priority";
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					priorityList.add(DefectPriority.valueOf(resultSet.getString("value")));
				}
			}
			
			connection.close();
			return priorityList;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return priorityList;
		}
	}
	
	public List<DefectStatus> getStatusList() {
		List<DefectStatus> statusList = new ArrayList<DefectStatus>();
		try {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("USE " + Constants.DB_NAME);
			String sql = "SELECT * FROM status";
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					statusList.add(DefectStatus.valueOf(resultSet.getString("value")));
				}
			}
			
			connection.close();
			return statusList;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return statusList;
		}
	}
	
	
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		try {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("USE " + Constants.DB_NAME);
			String sql = "SELECT * FROM user";
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					int id = resultSet.getInt("id");
					String first = resultSet.getString("first");
					String last = resultSet.getString("last");
					String email = resultSet.getString("email");
					User user = new User(first, last, email);
					user.setId(id);
					userList.add(user);
				}
			}
			
			connection.close();
			return userList;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return userList;
		}
	}
	
	public List<Defect> getDefectList() {
		List<Defect> defectList = new ArrayList<Defect>();
		try {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("USE " + Constants.DB_NAME);
			String sql = "SELECT * FROM defect";
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					int id = resultSet.getInt("id");
					Date date = resultSet.getDate("date");
					int submitter_id = resultSet.getInt("submitter_id");
					int assignee_id = resultSet.getInt("assignee_id");
					int priority_id = resultSet.getInt("priority_id");
					int status_id = resultSet.getInt("status_id");
					String summary = resultSet.getString("summary");
					String description = resultSet.getString("description");
					Defect defect = new Defect(getUser(submitter_id), getUser(assignee_id), getPriority(priority_id), getStatus(status_id), summary, description);
					defect.setId(id);
					defect.setDate(date);
					defectList.add(defect);
				}
			}
			
			connection.close();
			return defectList;
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
			return defectList;
		}
	}
	
	public void addUser(User user) {
		try {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("USE " + Constants.DB_NAME);
			String sql = "INSERT INTO user (first, last, email) " + 
					"VALUES ('" +
	                user.getFirstName() + "','" +
	                user.getLastName() + "','" +
	                user.getEmail() + "')";
			statement.executeUpdate(sql);
			
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
		}
	}
	
	public void addDefect(Defect defect) {
		try {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("USE " + Constants.DB_NAME);
			String sql = "INSERT INTO defect (submitter_id, assignee_id, priority_id, status_id, summary, description) " + 
					"VALUES (" +
	                getUserId(defect.getSubmitter()) + ","  +
	                getUserId(defect.getAssignee()) + ","  +
	                getPriorityId(defect.getPriority()) + ","  +
	                getStatusId(defect.getStatus()) + ","  +
	                "'" + defect.getSummary() + "'," +
	                "'" + defect.getDescription() + "')";
			statement.executeUpdate(sql);
			
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
		}
	}
	
	public void saveDefect(Defect defect) {
		try {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("USE " + Constants.DB_NAME);
			String sql = "UPDATE defect SET " + 
					"submitter_id=" + getUserId(defect.getSubmitter()) + ", " +
					"assignee_id=" + getUserId(defect.getAssignee()) + ", " +
					"priority_id=" + getPriorityId(defect.getPriority()) + ", "  +
					"status_id=" + getStatusId(defect.getStatus()) + ", "  +
					"summary='" + defect.getSummary() + "', " +
					"description='" + defect.getDescription() + "' WHERE id=" + defect.getId();
			statement.executeUpdate(sql);
			
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Error Code: " + ex.getErrorCode() + " \nState: " + ex.getSQLState() + " \nMessage: " + ex.getMessage());
		}
	}
}