package vote;

import java.sql.ResultSet;

public class AdminDBA {
    private DBManipulations dbManipulations;

    public AdminDBA() {
        this.dbManipulations = new DBManipulations();
    }

    public String addVoterpage(String voter) {
        String query = "Insert into voter (name) values(\"" + voter + "\")";
        try {
            dbManipulations.stmt.execute(query);
            String s = "New voter added";
            return s;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public boolean isAdminthere(String username, String password) {
        try {
            String query = "SELECT * FROM admin WHERE username='" + username + "' and password = '" + password + "';";
            ResultSet res = dbManipulations.stmt.executeQuery(query);
            return res.next();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public int[] votingLiveDetailsDBA() {
        int totalVoter = dbManipulations.getTableSize("voter");
        int totalVotesDone = dbManipulations.getTableSize("voting_information");
        int remVotes = totalVoter - totalVotesDone;
        int[] resultArray = { totalVoter, totalVotesDone, remVotes };
        return resultArray;
    }

    public String deleteVoterPage(int id) {
        String query = "DELETE FROM voting_information WHERE voter_id=" + id;
        try {
            dbManipulations.stmt.execute(query);
            query = "DELETE FROM voter WHERE voter_id =" + id;
            dbManipulations.stmt.execute(query);
            return "voter removed successfully";
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public String addcandidatepage(String name, int age, String party) {
        try {

            String query = "INSERT INTO candidate (name, age, party) VALUES (\"" + name + "\", \"" + age + "\", \"" + party + "\")";
            dbManipulations.stmt.execute(query);
            return "New candidate added";
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public String deletecandidatepage(int id) {
        try {
            String query = "DELETE FROM candidate WHERE candidate_id=" + id;
            dbManipulations.stmt.execute(query);
            return "Candidate removed successfully";
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
