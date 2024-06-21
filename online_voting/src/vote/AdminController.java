package vote;

public class AdminController {

	AdminDBA ad= new AdminDBA();
	public String addVoter(String voter) {
      return ad.addVoterpage(voter);    
      }
	public boolean isAdmin(String username,String password)
	{
		return ad.isAdminthere(username,password);
	}
	public int[] votingLiveDetails()
	{
		return ad.votingLiveDetailsDBA();
	}
	 public String deletevoter(int id) {
       return ad.deleteVoterPage(id);    
       }
	 public String  addcandidate(String name,int age,String party)
	    {
//	        AdminDBA adminDBA = new AdminDBA();
	    	return ad.addcandidatepage(name,age,party);
	    }
	    public String  deletecandidate(int id)
	    {
//	        AdminDBA adminDBA = new AdminDBA();
	    	return ad.deletecandidatepage(id);
	    }
	

}
