package vote;
import java.util.*;


import java.sql.*;
public class App {
	static void goToAdminPage(String username)
	{
		AdminPage ap=new AdminPage(username);
	}
	static void goToVoterPage() {
		new VoterPage();
	}
	public static void main(String args[])
	{
		try
		{
			AdminController ac= new AdminController();
			Scanner in = new Scanner(System.in);
			while(true)
			{
				System.out.println("Login As");
				System.out.println("[A]-Admin\n [B]-Voter\n [E]-Exit");
				char op=in.next().toUpperCase().charAt(0);
				if(op=='A')
				{
					System.out.println("Admin username:");
					String username=in.next();
					System.out.println("Admin password:");
					String password=in.next();
					if(ac.isAdmin(username,password))
					{
						goToAdminPage(username);
					}
					else
					{
						System.out.println("Invalid credentials");
					}
				}
				else if(op=='B')
				{
					goToVoterPage();
				}
				else if(op=='E')
				{
					break;
				}
				else break;
			}
			in.close();

		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
