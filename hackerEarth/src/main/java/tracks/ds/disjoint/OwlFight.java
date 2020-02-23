package tracks.ds.disjoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OwlFight {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int numOwls = Integer.valueOf(input[0]);
        int[] owlGraph = new int[numOwls + 1];
        int[] owlGraphSize = new int[numOwls + 1];
        int[] strongest = new int[numOwls + 1];
        for (int i = 0; i < owlGraph.length; i++) {
            owlGraph[i] = i;
            strongest[i] = i;
            owlGraphSize[i] = 1;
        }

        int connections = Integer.valueOf(input[1]);
        for (int i = 0; i < connections; i++) {
            String[] inputs = bufferedReader.readLine().split(" ");
            int firstOwl = Integer.valueOf(inputs[0]);
            int secondOwl = Integer.valueOf(inputs[1]);
            union(firstOwl, secondOwl, owlGraph, owlGraphSize, strongest);
        }

        int numFights = Integer.valueOf(bufferedReader.readLine());
        for (int i = 0; i < numFights; i++) {
            String[] fightParticipants = bufferedReader.readLine().split(" ");
            int firstContestant = Integer.valueOf(fightParticipants[0]);
            int secondContestant = Integer.valueOf(fightParticipants[1]);
            fightResult(firstContestant, secondContestant, owlGraph, strongest);
        }
    }

    private static void fightResult(int firstContestant, int secondContestant, int[] owlGraph, int[] strongest) {
        int strongestFirst = findRoot(firstContestant, owlGraph);
        int strongestSecond = findRoot(secondContestant, owlGraph);
        if (strongest[strongestFirst] == strongest[strongestSecond])
            System.out.println("TIE");
        else if (strongest[strongestFirst] > strongest[strongestSecond])
            System.out.println(firstContestant);
        else
            System.out.println(secondContestant);
    }


    private static void union(int firstOwl, int secondOwl, int[] owlGraph, int[] owlGraphSize, int[] stongest) {
        int rootFirstOwl = findRoot(firstOwl, owlGraph);
        int rootSecondOwl = findRoot(secondOwl, owlGraph);
        if (rootFirstOwl != rootSecondOwl){
            if (owlGraphSize[rootFirstOwl] < owlGraphSize[rootSecondOwl]){
                int strongest = stongest[owlGraph[rootFirstOwl]] > stongest[owlGraph[rootSecondOwl]]?stongest[owlGraph[rootFirstOwl]]:stongest[owlGraph[rootSecondOwl]];
                owlGraph[rootFirstOwl] = owlGraph[rootSecondOwl];
                owlGraphSize[rootSecondOwl] += owlGraphSize[rootFirstOwl];
                stongest[owlGraph[rootFirstOwl]] = strongest;
            } else {
                int strongest = stongest[owlGraph[rootFirstOwl]] > stongest[owlGraph[rootSecondOwl]]?stongest[owlGraph[rootFirstOwl]]:stongest[owlGraph[rootSecondOwl]];
                owlGraph[rootSecondOwl] = owlGraph[rootFirstOwl];
                owlGraphSize[rootFirstOwl] += owlGraphSize[rootSecondOwl];
                stongest[owlGraph[rootSecondOwl]] = strongest;
            }
        }

    }

    private static int findRoot(int owl, int[] owlGraph) {
        while (owlGraph[owl] != owl){
            owlGraph[owl] = owlGraph[owlGraph[owl]];
            owl = owlGraph[owl];
        }
        return owl;
    }
}
