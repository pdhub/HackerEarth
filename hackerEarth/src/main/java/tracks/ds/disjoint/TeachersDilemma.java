package tracks.ds.disjoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TeachersDilemma {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] inputs = bufferedReader.readLine().split(" ");
        int numStudents = Integer.valueOf(inputs[0]);
        int numRelationShips = Integer.valueOf(inputs[1]);
        int arr[] = new int[numStudents + 1];
        int size[] = new int[numStudents + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < numRelationShips; i++) {
            String[] pairs = bufferedReader.readLine().split(" ");
            int firstStudent = Integer.valueOf(pairs[0]);
            int secondStudent = Integer.valueOf(pairs[1]);
            union(firstStudent, secondStudent, arr, size);
        }

        Set uniqueRoots = new HashSet();
        for (int i = 1; i < arr.length; i++) {
            uniqueRoots.add(arr[i]);
        }
        double numWays = 1;
        Iterator uniqueRoot = uniqueRoots.iterator();
        while (uniqueRoot.hasNext()){
            int tempRoot = (Integer) uniqueRoot.next();
            numWays *= size[tempRoot];
        }
        System.out.println(String.format("%.0f", numWays));
    }

    private static void union(int firstStudent, int secondStudent, int[] arr, int[] size) {
        int rootA = findRoot(firstStudent, arr);
        int rootB = findRoot(secondStudent, arr);
        if (rootA != rootB){
            if (size[rootA] < size[rootB]){
                arr[rootA] = arr[rootB];
                size[rootB] += size[rootA];
            } else {
                arr[rootB] = arr[rootA];
                size[rootA] += size[rootB];
            }
        }
    }

    private static int findRoot(int student, int[] arr) {
        while (arr[student] !=student){
            arr[student] = arr[arr[student]];
            student = arr[student];
        }
        return student;
    }
}
