package tracks.algorithms.greedy;

import java.util.*;

public class ActivitySelection {

    private static Set<Integer> selectActivity(List<Pair> activities) {
        int k = 0;
        Set<Integer> activityIndexs = new HashSet<>();
        activityIndexs.add(k);
        for (int i = 1; i < activities.size(); i++) {
            Pair tempPair = activities.get(i);
            if (tempPair.getStart() >= activities.get(k).getEnd()){
                activityIndexs.add(i);
                k = i;
            }
        }
        return activityIndexs;
    }

    public static void main(String[] args) {
        List<Pair> activities = Arrays.asList(new Pair(1, 4),
                new Pair(3, 5),
                new Pair(0, 6),
                new Pair(5, 7),
                new Pair(3, 8),
                new Pair(5, 9),
                new Pair(6, 10),
                new Pair(8, 11),
                new Pair(8, 12),
                new Pair(2, 13),
                new Pair(12, 14)
        );

        Collections.sort(activities, (a, b) -> a.getEnd() - b.getEnd());
        Set<Integer> finalSet = selectActivity(activities);
        for (Integer i : finalSet)
            System.out.println(activities.get(i));
    }
}

class Pair {
    int start, end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}


