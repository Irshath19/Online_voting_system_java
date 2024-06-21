package vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VoterDBA {
    private DBManipulations dbManipulations;
    public VoterDBA() {
        this.dbManipulations = new DBManipulations();
    }

    public List<String> validDetails(List<Object> parameters) {
        List<String> result = new ArrayList<>();

        try {
            int voter_id = (int) parameters.get(0);
            String president = (String) parameters.get(1);
            String party = (String) parameters.get(2);

            // Validate voter ID
            PreparedStatement validateVoter = dbManipulations.getConnection().prepareStatement("SELECT * FROM voter WHERE voter_id=?");
            validateVoter.setInt(1, voter_id);

            ResultSet voterResult = validateVoter.executeQuery();
            if (!voterResult.next()) {
                result.add("Vote failed: Invalid voter ID");
                return result;
            }

            PreparedStatement validatePresident = dbManipulations.getConnection().prepareStatement("SELECT * FROM candidate WHERE name=? AND party=?");
            validatePresident.setString(1, president);
            validatePresident.setString(2, party);

            ResultSet presidentResult = validatePresident.executeQuery();
            if (!presidentResult.next()) {
                result.add("Vote failed: Invalid president or party");
                return result;
            }

            // Insert vote into voting_information without NULL
            PreparedStatement insertVote = dbManipulations.getConnection().prepareStatement("INSERT INTO voting_information (voter_id, president, party) VALUES (?, ?, ?)");
            insertVote.setInt(1, voter_id);
            insertVote.setString(2, president);
            insertVote.setString(3, party);

            int rowsAffected = insertVote.executeUpdate();

            // Check existing count value
            if (rowsAffected > 0) {
                PreparedStatement getExistingCount = dbManipulations.getConnection().prepareStatement("SELECT count FROM lead WHERE name=? AND party=?");
                getExistingCount.setString(1, president);
                getExistingCount.setString(2, party);

                ResultSet existingCountResult = getExistingCount.executeQuery();

                int existingCount = 0;
                if (existingCountResult.next()) {
                    existingCount = existingCountResult.getInt("count");

                    // Update the count in the lead table
                    PreparedStatement updateLead = dbManipulations.getConnection().prepareStatement("UPDATE lead SET count = ? WHERE name=? AND party=?");
                    updateLead.setInt(1, existingCount + 1);
                    updateLead.setString(2, president);
                    updateLead.setString(3, party);

                    int updateRowsAffected = updateLead.executeUpdate();

                    if (updateRowsAffected <= 0) {
                        result.add("Vote failed: Error updating count in lead table");
                    } else {
                        result.add("Vote success: Vote cast successfully");
                    }
                } else {
                    // Insert into the lead table with the initial count (0 + 1)
                    PreparedStatement insertLead = dbManipulations.getConnection().prepareStatement("INSERT INTO lead (name, party, count) VALUES (?, ?, 1)");
                    insertLead.setString(1, president);
                    insertLead.setString(2, party);

                    int leadRowsAffected = insertLead.executeUpdate();

                    if (leadRowsAffected <= 0) {
                        result.add("Vote failed: Error during lead insertion");
                    } else {
                        result.add("Vote success: Vote cast successfully");
                    }
                }
            } else {
                result.add("Vote failed: Error during vote insertion");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            result.add("Vote failed: Error occurred during vote processing");
        }

        return result;
    }
}
