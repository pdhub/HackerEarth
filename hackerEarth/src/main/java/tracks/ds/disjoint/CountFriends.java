package tracks.ds.disjoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountFriends {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = (bf.readLine()).split(" ");
        int numStudents = Integer.valueOf(inputs[0]);
        int numRelationShips = Integer.valueOf(inputs[1]);

        int arr[] = new int[numStudents + 1];
        int size[] = new int[numStudents + 1];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < numRelationShips; i++) {
            String[] relation = (bf.readLine()).split(" ");
            int studentA = Integer.valueOf(relation[0]);
            int studentB = Integer.valueOf(relation[1]);
            union(arr, size, studentA, studentB);
        }

        for (int i = 1; i < size.length; i++) {
            System.out.print(String.format("%s ", size[arr[findRoot(i, arr)]] - 1));
        }
    }

    private static void union(int[] arr, int[] size, int studentA, int studentB) {
        int rootA = findRoot(studentA, arr);
        int rootB = findRoot(studentB, arr);
        if (rootA != rootB) {
            if (size[rootA] < size[rootB]) {
                arr[rootA] = arr[rootB];
                size[rootB] += size[rootA];
            } else {
                arr[rootB] = arr[rootA];
                size[rootA] += size[rootB];
            }
        }
    }

    private static int findRoot(int student, int arr[]) {
        while (arr[student] != student){
            arr[student] = arr[arr[student]];
            student = arr[student];
        }
        return student;
    }
}
