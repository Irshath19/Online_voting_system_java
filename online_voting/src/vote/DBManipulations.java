	package vote;
	import java.sql.*;
	import java.util.*;
	class DBManipulations {
	    public Connection con;
	    public Statement stmt;
	    Scanner in = new Scanner(System.in);

	    DBManipulations() {
	        try {
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting2", "root", "");
	            stmt = con.createStatement();
	            System.out.println("connected");
	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	    }

	    public Connection getConnection() {
	        return con;
	    }
	public void viewTables(String tableName){
	System.out.println("******************************************************");
	new printTable().printResult(tableName);
	System.out.println("******************************************************");
}
public int getTableSize(String tablename)
{
	String query="SELECT COUNT(*) FROM "+tablename;
	int size=0;
	try
	{
		ResultSet res=stmt.executeQuery(query);
		if(res.next())
		{
			size=res.getInt(1);
		}
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
	return size;
}
}