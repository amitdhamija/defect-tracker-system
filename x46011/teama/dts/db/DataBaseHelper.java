import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class DataBaseHelper {
  private static final String dbName = "DefectTracker";
  private static final String url = "jdbc:mysql://localhost/"; 
  private static final String user = "root"; 
  private static final String password = "sql";
  private Connection connection;
  public DataBaseHelper()
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
	    int result = statement.executeUpdate(sql);
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
	    int result = statement.executeUpdate(sql);
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
	    int result = statement.executeUpdate(sql);
	    connection.close();
	  }catch(SQLException e) {
		  return; 
	  }
	  
  }
  
  public void deleteUser(User userInfo)
  {
	  try
	  {
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "delete from UserTable where " + 
	                "first='"+  userInfo.getFirstName() + "' and " +
	                "last='" +  userInfo.getLastName() +  "' and " + 
	                "email='" + userInfo.getEmail() +     "'";
		  int result = statement.executeUpdate(sql);
		  connection.close();
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
	  }
  }
  public void addUser(User userInfo)
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
	                status.getStatus()  + "')";
		  int result = statement.executeUpdate(sql);
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
	                "id="+  status.getId() + " and " +
	                "text='" +  status.getStatus() + "'";
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
			  String stat = result.getString("text");	 
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
  
  public List<User> getUserList()
  {
	  try
	  {
		  List<User> userList = new ArrayList<User>();
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "select * from UserTable";
		  ResultSet result = statement.executeQuery(sql);
		  while(result.next())
		  {
			  String first = result.getString("first");
			  String last = result.getString("last");
			  String email = result.getString("email");
			  userList.add(new User(first, last, email));
		  }
		  connection.close();
		  return userList;
		  
	  }catch(SQLException e)
	  {
		  e.printStackTrace();
		  return new ArrayList<User>();
	  }
  }
  
  public User getUser(int id)
  {
	  try
	  {
		  User selectedUser;
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "select * from UserTable where " + 
		    "id=" + id;
		  ResultSet result = statement.executeQuery(sql);
		  while(result.next())
		  {
			  String first = result.getString("first");
			  String last = result.getString("last");
			  String email = result.getString("email");
			  selectedUser = new User(id, first, last, email);
			  return selectedUser;
		  }
		  connection.close();
		  return null;
		  
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
		  Status selectedStatus;
		  connection = DriverManager.getConnection(url + dbName, user, password);
		  Statement statement = connection.createStatement();
		  String sql = "select * from StatusTable where " + 
		    "id=" + id;
		  ResultSet result = statement.executeQuery(sql);
		  while(result.next())
		  {
			  String text = result.getString("text");		 
			  selectedStatus = new Status(id, text);
			  return selectedStatus;
		  }
		  connection.close();
		  return null;
		  
	  }catch(SQLException e)
	  {
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
	                defect.getReporter().getId() + ","  +
	                "'" + defect.getSummary()    + "'," +
	                "'" + defect.getDetails()    + "'," +
	                defect.getAssignee().getId() + ","  +
	                defect.getStatus().getId()   + ","  +
	                defect.getPriority() + ")";
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
		  int priority = result.getInt("priority");
		  User reporter = getUser(reporterId);
		  User assignee = getUser(assigneeId);
		  Status status = getStatus(statusId);
		  Defect currentDefect = new Defect(date.toString(), id, reporter, summary, details, assignee, status, priority);
		  defectList.add(currentDefect);
	    }
	  
	    connection.close();
	    return defectList;
	  }catch(SQLException e)
	  {
		  return new ArrayList<Defect>();
	  }
  }
  

  
 
}
