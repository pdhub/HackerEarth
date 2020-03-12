package tracks.ds.disjoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StillMaximum {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numNodes = Integer.valueOf(bufferedReader.readLine());

        int nodes[] = new int[numNodes];
        int nodeValues[] = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            String[] nodesToLink = bufferedReader.readLine().split(" ");
            //union(nodesToLink[0], nodesToLink[1], nodes);
        }

        String[] tempNodeValues = bufferedReader.readLine().split(" ");
        for (int i = 0; i < tempNodeValues.length; i++) {
            nodeValues[i] = Integer.valueOf(tempNodeValues[i]);
        }


    }
}
