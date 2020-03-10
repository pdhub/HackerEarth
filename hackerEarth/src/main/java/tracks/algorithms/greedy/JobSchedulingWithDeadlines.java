package tracks.algorithms.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JobSchedulingWithDeadlines {

    private static int scheduleJobs(List<Job> jobs, int max_allowed_deadline) {
        int profit = 0;
        int slot[] = new int[max_allowed_deadline];
        Arrays.fill(slot, -1);
        for(Job job : jobs){
            for (int i = job.getDeadline() - 1; i >=0 ; i--) {
                if (i < max_allowed_deadline && slot[i] == -1){
                    slot[i] = job.getTaskId();
                    profit += job.getProfit();
                    break;
                }
            }
        }

        System.out.println("Scheduled jobs are ");
        Arrays.stream(slot).filter(val -> val!=-1).forEach(System.out::println);
        return profit;
    }

    public static void main(String[] args) {
        List<Job> jobs = Arrays.asList(
                new Job(1, 9, 15), new Job(2, 2, 2),
                new Job(3, 5, 18), new Job(4, 7, 1),
                new Job(5, 4, 25), new Job(6, 2, 20),
                new Job(7, 5, 8), new Job(8, 7, 10),
                new Job(9, 4, 12), new Job(10, 3, 5)
        );

        final int MAX_ALLOWED_DEADLINE = 15;
        Collections.sort(jobs, (a, b)->b.getProfit() - a.getProfit());
        System.out.println("Total profit is : " + scheduleJobs(jobs, MAX_ALLOWED_DEADLINE));
    }
}

class Job {
    private int taskId, deadline, profit;

    public Job(int taskId, int deadline, int profit) {
        this.taskId = taskId;
        this.deadline = deadline;
        this.profit = profit;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getProfit() {
        return profit;
    }
}
