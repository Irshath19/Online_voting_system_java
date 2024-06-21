package vote;
import java.util.*;
import java.sql.*;
public class VoterPage{
	VoterController vc= new VoterController();
		Scanner in = new Scanner(System.in);
		VoterPage()
		{
			while(true)
	{
		System.out.println("[A]-vote now!");
		System.out.println("[B]-Back");
		System.out.println("Note:Make sure that your data you enter are valid");
		char type=Character.toLowerCase(in.next().charAt(0));
		switch (type) {
	    case 'a':
	        System.out.println("Enter your voter's ID:");
	        int id = in.nextInt();
	        System.out.println("Enter your candidate name:");
	        String name = in.next();
	        System.out.println("Enter your candidate's party");
	        String party = in.next();

	        List<Object> parameters = new ArrayList<>();
	        parameters.add(id);
	        parameters.add(name);
	        parameters.add(party);

	        List<String> result =vc.doVote(parameters);
	        System.out.println(result.get(0)); 
	        break;
	    case 'b':
	        return;
	    default:
	        System.out.println("Invalid operation");
	        return;
	}
}

}}