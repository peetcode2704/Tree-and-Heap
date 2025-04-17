import java.util.*;

public class ElectionSystem {
    public static void main(String[] args) {
        Election election = new Election();
        Random rand = new Random();

        String[] allCandidates = {
                "Marcus Fenix", "Dominic Santiago", "Damon Baird",
                "Cole Train", "Anya Stroud", "Jayden Cruz", "Peter Hoang",
                "Chris Garcia", "Navi Bounto", "Kevin Gonzalez", "Ethan William"
        };

        int numCandidates = 3 + rand.nextInt(5); // 3 to 7 inclusive
        LinkedList<String> selectedCandidates = new LinkedList<>();

        List<String> pool = new ArrayList<>(Arrays.asList(allCandidates));
        Collections.shuffle(pool);        // Shuffle and pick unique candidates
        selectedCandidates.addAll(pool.subList(0, numCandidates));

        int totalVotes = 5 + rand.nextInt(11); //between 5 and 15

        System.out.println("🗳️  Candidates in the Election:");
        for (String c : selectedCandidates) {
            System.out.println(" - " + c);
        }
        System.out.println("🔢 Total Votes Allowed: " + totalVotes);

        election.initializeCandidates(selectedCandidates, totalVotes);

        for (int i = 0; i < totalVotes; i++) {
            election.castRandomVote();
        }

        int topK = Math.min(3, selectedCandidates.size());

        System.out.println("\n📊 Top " + topK + " Candidates After Voting:");
        List<String> topBefore = election.getTopKCandidates(topK);
        for (String name : topBefore) {
            System.out.println(" - " + name);
        }

        String riggedWinner = selectedCandidates.get(rand.nextInt(selectedCandidates.size()));
        System.out.println("\n⚙️  Rigging election for: " + riggedWinner);
        election.rigElection(riggedWinner);       // Rig election for a random candidate


        System.out.println("\n📊 Top " + topK + " Candidates After Rigging:");
        List<String> topAfter = election.getTopKCandidates(topK);
        for (String name : topAfter) {
            System.out.println(" - " + name);
        }

        System.out.println("\n📋 Final Audit Report:");
        election.auditElection();
    }
}
