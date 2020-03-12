package tracks.algorithms.greedy;

import java.util.HashSet;
import java.util.Set;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startingGasStation = 0;
        int petrol = gas[startingGasStation];
        int nextGasStation = startingGasStation;
        Set<Integer> doneWithzstation = new HashSet<>();
        int stationsVisited = 1;
        while (!allStationsChecked(doneWithzstation, gas.length)){
            int prevGasStation = nextGasStation;
            nextGasStation = (nextGasStation + 1 )%gas.length;
            if(canGoToNextStation(petrol, cost[prevGasStation])){
                stationsVisited++;
                if (stationsVisited == gas.length)
                    return startingGasStation;
                petrol = petrol - cost[prevGasStation] + gas[nextGasStation];
            }else {
                doneWithzstation.add(startingGasStation);
                startingGasStation = nextGasStation;
                petrol = gas[startingGasStation];
                stationsVisited = 1;
            }
        }
        return -1;
    }

    private boolean allStationsChecked(Set<Integer> doneWithzstation, int stations) {
        return doneWithzstation.size() == stations;
    }

    private boolean canGoToNextStation(int petrol, int costToReachNext) {
        return petrol - costToReachNext > 0;
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
