package vote;

import java.util.List;

public class VoterController {
	VoterDBA vd= new VoterDBA();
	public List<String> doVote(List<Object> parameters) {
	    return vd.validDetails(parameters);
	}


}
