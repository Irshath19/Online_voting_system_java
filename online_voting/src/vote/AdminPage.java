package vote;

import java.util.Scanner;

public class AdminPage{
	AdminController ac=new AdminController();
	DBManipulations dm= new DBManipulations();
	String username;
	Scanner in = new Scanner(System.in);
	 AdminPage(String user) {
	        super();
	        username = user;
	       doprocess();
	    }
	 public void votingLiveDetails() {
			System.out.println("******************************************************");
			int totVoter=dm.getTableSize("voter"),totalVotesdone=dm.getTableSize("voting_information"),remVotes=totVoter-totalVotesdone;
			System.out.println("\tTotal voter: "+totVoter);
			System.out.println("\tTotal cast votes: "+totalVotesdone);
			System.out.println("\tRemaining votes: "+remVotes);
			System.out.println("******************************************************");
		}
	 private void doprocess()
	 {
		 char type='\0';
		 while(type!='J')
		 {
			 System.out.println("[A]-Add Voter");
				System.out.println("[B]-Delete Voter");
				System.out.println("[C]-Add Candidate");
				System.out.println("[D]-Delete Candidate");
				System.out.println("[E]-View voters");
				System.out.println("[F]-View Candidates");
				System.out.println("[G]-View Voting Information");
				System.out.println("[H]-view live details");
				System.out.println("[I]-View leading candidates or winner");
				System.out.println("[J]-Back");
				System.out.println("Select operation: ");
				type =Character.toUpperCase(in.next().charAt(0));
				switch(type) {
				case 'A':
					System.out.println("Voter's name: ");
					String voter=in.next();
					String s=ac.addVoter(voter);
					System.out.println(s);
					dm.viewTables("voter");
					break;
				case 'B':
					System.out.println("Enter voter id to delete: ");
					int id=in.nextInt();
					System.out.println(ac.deletevoter(id));
					dm.viewTables("voter");
					break;
				case 'C':
					System.out.println("Enter candidate name: ");
					String name=in.next();
					System.out.println("Enter candidates age: ");
					int age=in.nextInt();					
					System.out.println("Enter candidate's party: ");
					String party = in.next();
					System.out.println(ac.addcandidate(name, age, party));
					dm.viewTables("candidate");
					break;
				case 'D':
					System.out.println("Enter the candiate id to be delete: ");
					int cid=in.nextInt();
					System.out.println(ac.deletecandidate(cid));
					dm.viewTables("candidate");
				case 'E':
					dm.viewTables("voter");
					break;
				case 'F':
					dm.viewTables("candidate");
					break;
				case 'G':
					dm.viewTables("voting_information");
					break;
				case 'H':
					votingLiveDetails();
					break;
				case 'I':
					dm.viewTables("lead");
					break;
				case 'J':
					return;
				default:
					System.out.println("Invalid operation");
					break;
					
				}
		 }
	 }

	

}
