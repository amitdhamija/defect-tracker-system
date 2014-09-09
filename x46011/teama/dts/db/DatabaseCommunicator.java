package x46011.teama.dts.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.DefectPriority;
import x46011.teama.dts.model.DefectStatus;
import x46011.teama.dts.model.Person;
import x46011.teama.dts.model.Status;


/**
 * DTSCommManager handles interactions between the UI and the DB Communicator
 * 
 * @author Travis Cribbet
 * @version 1.0
 * @revision 1.1	Kevin Alexander: Removed unneccesary functions
 * @revision 1.2	Kevin Alexander: Renamed function using "Person" to user "User" to better fit domain terminology 
 * @revision 1.3	Kevin Alexander: Moved main return statements in getUser and getStatus outside of while loop so
 * 									 that the connection is closed prior to function return
 * @revision 1.4	Kevin Alexander: Implemented getDefect 
 */
public class DatabaseCommunicator implements IDatabaseCommunications {
  private static final String dbName = "DefectTracker";
  private static final String url = "jdbc:mysql://localhost/"; 
  private static final String user = "root"; 
  private static final String password = "sql";
  private Connection connection;
  public DatabaseCommunicator()
  {
		CreateDataBase();
		CreateUserTable();
		CreateStatusTable();
		CreateDefectTable();
  }
  
  public void CreateDataBase()
  {
    try {
		connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate("create database if not exists " + dbName);
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  public void CreateDefectTable()
  {
	  try
	  {
	    connection = DriverManager.getConnection(url + dbName, user, password);
	    Statement statement = connection.createStatement();
	    String sql = "CREATE TABLE DefectTable " +
              "(id INTEGER AUTO_INCREMENT, " +
              " date timestamp DEFAULT CURRENT_TIMESTAMP, " + 
              " reporter_id INTEGER  not NULL, " + 
              " summary VARCHAR(100), " + 
              " details VARCHAR(4000), " + 
              " assignee_id INTEGER not NULL, " + 
              " status_id INTEGER not NULL, " + 
              " priority INTEGER not NULL, " + 
              " PRIMARY KEY ( id ))"; 
	    statement.executeUpdate(sql);
	    connection.close();
	  }catch (SQLException e)
	  {
		  return;
	  }
  }
  public void CreateStatusTable()
  {
	  try
	  {
	    connection = DriverManager.getConnection(url + dbName, user, password);
	    Statement statement = connection.createStatement();
	    String sql = "CREATE TABLE StatusTable  " +
              "(id INTEGER not NULL, " +
              " text VARCHAR(255), " + 
              " PRIMARY KEY ( id ))"; 
	    statement.executeUpdate(sql);
	    connection.close();
	  }catch (SQLException e)
	  {
		  return;
	  }
  }
  public void CreateUserTable()
  {
	  try{
	    connection = DriverManager.getConnection(url + dbName, user, password);
	    Statement statement = connection.createStatement();
	    String sql = "CREATE TABLE UserTable " +
                "(id INTEGER AUTO_INCREMENT, " +
                " first VARCHAR(255), " + 
                " last VARCHAR(255), " + 
                " email VARCHAR(255), " + 
                " PRIMARY KEY ( id ))"; 
	    statement.executeUpdate(sql);
	    connection.close();
	  }catch(SQLException e) {
		  return; 
	  }
	  
  }
  
  public void deleteUser(Person personInfo)
  {
	  try
	  {
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "delete from UserTable where " + 
	                "first='"+  personInfo.getFirstName() + "' and " +
	                "last='" +  personInfo.getLastName() +  "' and " + 
	                "email='" + personInfo.getEmail() +     "'";
		  int result = statement.executeUpdate(sql);
		  connection.close();
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
	  }
  }
  public void addUser(Person userInfo)
  {
	  try
	  {
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "insert into UserTable (first,last,email) " + 
		            "values ('" +
	                userInfo.getFirstName() + "','" +
	                userInfo.getLastName() + "','" +
				    userInfo.getEmail() + "')";
		  int result = statement.executeUpdate(sql);
		  connection.close();
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public void addStatus(Status status)
  {
	  try
	  {
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "insert into StatusTable (id, text) " + 
		            "values (" +
	                status.getId() + ",'" +
	                status.getStatus().toString()  + "')";
		  statement.executeUpdate(sql);
		  connection.close();
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public void deleteStatus(Status status)
  {
	  try
	  {
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "delete from UserTable where " + 
	                "id="+  status.getId() +  " and " +
	                "text='" +  status.getStatus().toString() + "'";
		  int result = statement.executeUpdate(sql);
		  connection.close();
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public List<Status> getStatusList()
  {
	  try
	  {
		  List<Status> statusList = new ArrayList<Status>();
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "select * from StatusTable";
		  ResultSet result = statement.executeQuery(sql);
		  while(result.next())
		  {
			  int id = result.getInt("id");
			  DefectStatus stat = Enum.valueOf(DefectStatus.class, result.getString("text"));
			  statusList.add(new Status(id, stat));
		  }
		  connection.close();
		  return statusList;
		  
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
		  return new ArrayList<Status>();
	  }
  }
  
  public List<Person> getUsersList()
  {
	  try
	  {
		  List<Person> personList = new ArrayList<Person>();
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "select * from UserTable";
		  ResultSet result = statement.executeQuery(sql);
		  while(result.next())
		  {
			  int id = result.getInt(0);
			  String first = result.getString("first");
			  String last = result.getString("last");
			  String email = result.getString("email");
			  personList.add(new Person(id, first, last, email));
		  }
		  connection.close();
		  return personList;
		  
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
		  return new ArrayList<Person>();
	  }
  }
  
  public Person getUser(int id)
  {
	  try
	  {
		  Person selectedPerson = null;
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "select * from UserTable where " + 
		    "id=" + id;
		  ResultSet result = statement.executeQuery(sql);
		  while(selectedPerson==null && result.next())
		  {
			  String first = result.getString("first");
			  String last = result.getString("last");
			  String email = result.getString("email");
			  selectedPerson = new Person(id, first, last, email);
		  }
		  connection.close();
		  return selectedPerson;
		  
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
		  return null;
	  }
  }
  
  public Status getStatus(int id)
  {
	  try
	  {
		  Status selectedStatus = null;
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "select * from StatusTable where " + 
		    "id=" + id;
		  ResultSet result = statement.executeQuery(sql);
		  while(selectedStatus==null && result.next())
		  {
			  DefectStatus defectStatus = Enum.valueOf(DefectStatus.class,result.getString("text"));		 
			  selectedStatus = new Status(id, defectStatus);
		  }
		  
		  connection.close();
		  return selectedStatus;
		  
	  }catch(SQLException e) {
		  e.printStackTrace();
		  return null;
	  }
  }
  
  public void addDefect(Defect defect)
  {
	  try
	  {
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "insert into DefectTable (reporter_id, summary, details, assignee_id, status_id, priority) " + 
		            "values (" +
	                defect.getSubmitter().getId() + ","  +
	                "'" + defect.getSummary()    + "'," +
	                "'" + defect.getDescription()    + "'," +
	                defect.getAssignee().getId() + ","  +
	                defect.getStatus().getId()  + ","  +
	                defect.getPriority().ordinal()  + ")";
		  int result = statement.executeUpdate(sql);
		  connection.close();
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
	  }
  }
  
  
  
 
  
  public List<Defect> getDefectList()
  {
	  try
	  {
	    List<Defect> defectList = new ArrayList<Defect>();
	    connection = DriverManager.getConnection(url + dbName, user, password);
	    Statement statement = connection.createStatement();
	    String sql = "select * from DefectTable";
	    ResultSet result = statement.executeQuery(sql);
	    while(result.next())
	    {
	      Date date =  result.getDate("date");
	      int id = result.getInt("id");
	      int reporterId = result.getInt("reporter_id");     
	      String summary = result.getString("summary");
		  String details = result.getString("details");
		  int assigneeId = result.getInt("assignee_id");
		  int statusId = result.getInt("status_id");
		  DefectPriority priority = DefectPriority.values()[result.getInt("priority")];
		  Person submitter = getUser(reporterId);
		  Person assignee = getUser(assigneeId);
		  Status status = getStatus(statusId);
		  //Defect currentDefect = new Defect(date, id, reporter, summary, details, assignee, status, priority);
		  
		  // TODO: Where is the description? We need to get description from database too.
		  Defect currentDefect = new Defect(id, date, submitter, assignee, priority, status, summary, "fake description");
		  defectList.add(currentDefect);
	    }
	  
	    connection.close();
	    return defectList;
	  }catch(SQLException e)
	  {
		  return new ArrayList<Defect>();
	  }
  }

	@Override
	public Defect getDefect(Integer defectId) {
		  try
		  {
			  Defect defect = null;
			  connection = DriverManager.getConnection(url + dbName, user, password);
			  Statement statement = connection.createStatement();
			  String sql = "select * from DefectTable where " + 
			    "id=" + defectId;
			  ResultSet result = statement.executeQuery(sql);
			  while(defect==null && result.next())
			  {
			      Date date =  result.getDate("date");
			      int id = result.getInt("id");
			      int reporterId = result.getInt("reporter_id");     
			      String summary = result.getString("summary");
				  String description = result.getString("description");
				  int assigneeId = result.getInt("assignee_id");
				  int statusId = result.getInt("status_id");
				  DefectPriority priority = DefectPriority.values()[result.getInt("priority")];
				  Person submitter = getUser(reporterId);
				  Person assignee = getUser(assigneeId);
				  Status status = getStatus(statusId);
				  
				  defect = new Defect(id, date, submitter, assignee, priority, status, summary, description);
			  }
			  
			  connection.close();
			  return defect;
		  }catch(SQLException e)
		  {
			  e.printStackTrace();
			  return null;
		  }
	}
	
	@Override
	public void saveDefect(Defect defect) {
		  try
		  {
			  connection = DriverManager.getConnection(url + dbName, user, password);
			  Statement statement = connection.createStatement();
			  String sql = "insert into DefectTable (reporter_id, summary, details, assignee_id, status_id, priority) " + 
			            "values (" +
		                defect.getSubmitter().getId() + ","  +
		                "'" + defect.getSummary()    + "'," +
		                "'" + defect.getDescription()    + "'," +
		                defect.getAssignee().getId() + ","  +
		                defect.getStatus().getId()  + ","  +
		                defect.getPriority().ordinal()  + ")";
			  int result = statement.executeUpdate(sql);
			  connection.close();
		  }catch(SQLException e)
		  {
			  e.printStackTrace();
		  }
		
	}
  

}