import java.util.*;

public class Election {
    private Map<String, Integer> voteMap;
    private PriorityQueue<Map.Entry<String, Integer>> maxHeap;
    private int totalVotes;

    public void initializeCandidates(LinkedList<String> candidateList, int totalVotes) {
        this.voteMap = new HashMap<>();
        for (String name : candidateList) {
            voteMap.put(name, 0);
        }
        this.totalVotes = totalVotes;
        updateMaxHeap();
    }

    public void castVote(String name) {
        if (voteMap.containsKey(name)) {
            voteMap.put(name, voteMap.get(name) + 1);
            updateMaxHeap();
        }
    }

    public void castRandomVote() {
        List<String> candidates = new ArrayList<>(voteMap.keySet());
        Random rand = new Random();
        String randomName = candidates.get(rand.nextInt(candidates.size()));
        castVote(randomName);
    }

    public void rigElection(String name) {
        int currentVotes = voteMap.values().stream().mapToInt(Integer::intValue).sum();
        int votesNeeded = totalVotes - currentVotes;
        if (voteMap.containsKey(name)) {
            voteMap.put(name, voteMap.get(name) + votesNeeded);
            updateMaxHeap();
        }
    }

    public List<String> getTopKCandidates(int k) {
        PriorityQueue<Map.Entry<String, Integer>> tempHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        tempHeap.addAll(voteMap.entrySet());

        List<String> topK = new ArrayList<>();
        while (k-- > 0 && !tempHeap.isEmpty()) {
            topK.add(tempHeap.poll().getKey());
        }
        return topK;
    }

    public void auditElection() {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(voteMap.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Election Audit:");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " votes");
        }
    }

    private void updateMaxHeap() {
        maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(voteMap.entrySet());
    }
}
