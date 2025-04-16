import java.util.LinkedList;
import java.util.List;

public class ElectionSystem {
    public static void main(String[] args) {
        Election election = new Election();

        LinkedList<String> candidates = new LinkedList<>();
        candidates.add("Marcus Fenix");
        candidates.add("Dominic Santiago");
        candidates.add("Damon Baird");
        candidates.add("Cole Train");
        candidates.add("Anya Stroud");

        int totalVotes = 5;
        election.initializeCandidates(candidates, totalVotes);

        election.castVote("Cole Train");
        election.castVote("Cole Train");
        election.castVote("Marcus Fenix");
        election.castVote("Anya Stroud");
        election.castVote("Anya Stroud");
        election.castRandomVote();

        List<String> top3BeforeRig = election.getTopKCandidates(3);
        System.out.println("Top 3 candidates after 5 votes: " + top3BeforeRig);

        election.rigElection("Marcus Fenix");

        List<String> top3AfterRig = election.getTopKCandidates(3);
        System.out.println("Top 3 candidates after rigging the election: " + top3AfterRig);

        election.auditElection();
    }
}
