package tracks.algorithms.greedy;

import java.util.HashSet;
import java.util.Set;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startingGasStation = 0;
        int totalGas = 0;
        int accumulate = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i] - cost[i];
        }
        if (totalGas < 0)
            return -1;

        for (int i = 0; i < gas.length; i++) {
            if (accumulate + gas[i] - cost[i] < 0){
                accumulate = 0;
                startingGasStation = i + 1;
            }else {
                accumulate += gas[i] - cost[i];
            }
        }
        return startingGasStation;
    }

    public static void main(String[] args) {
        int[] gas  = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        GasStation gasStation = new GasStation();
        System.out.println(gasStation.canCompleteCircuit(gas, cost));
        gas = new int[]{3, 3, 4};
        cost = new int[]{3, 4, 4};
        System.out.println(gasStation.canCompleteCircuit(gas, cost));

    }
}
