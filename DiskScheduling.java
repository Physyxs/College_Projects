package diskscheduling;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
* This is a program that calculates the number of head movements for the disk
* management algoriths FCFS, SCAN, and C-SCAN for 1000 requests across 5000
* cylinders.
*
* author Brandon Logan
*/
public class DiskScheduling {
    /*
    * args[0] initial head position
    */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java DiskScheduling <initial_head_position>");
            System.exit(1);
        }

        int initialHeadPosition = Integer.parseInt(args[0]);
        int totalRequests = 1000;
        int totalCylinders = 5000;

        // Call methods for generating requests and using algorithms
        List<Integer> requests = randomRequests(totalRequests, totalCylinders);
        int fcfsHeadMovement = fcfs(initialHeadPosition, requests);
        int scanHeadMovement = scan(initialHeadPosition, requests);
        int cScanHeadMovement = cScan(initialHeadPosition, requests);

        // Report the results
        System.out.println("FCFS Head Movement: " + fcfsHeadMovement);
        System.out.println("SCAN Head Movement: " + scanHeadMovement);
        System.out.println("C-SCAN Head Movement: " + cScanHeadMovement);
    }
    
    // Generate a random series of cylinder requests
    private static List<Integer> randomRequests(int totalRequests, int totalCylinders) {
        List<Integer> requests = new ArrayList<>();

        for (int i = 0; i < totalRequests; i++) {
            requests.add((int) (Math.random() * totalCylinders));
        }

        return requests;
    }
    
    // FCFS Disk Scheduling Algorithm
    private static int fcfs(int initialHead, List<Integer> requests) {
        int headMovement = 0;

        for (int request : requests) {
            headMovement += Math.abs(request - initialHead);
            initialHead = request;
        }

        return headMovement;
    }

    // SCAN Disk Scheduling Algorithm
    private static int scan(int initialHead, List<Integer> requests) {
        int headMovement = 0;

        // Sort requests in ascending order
        Collections.sort(requests);

        // Find the index where the initial head is located
        int index = Collections.binarySearch(requests, initialHead);
        if (index < 0) {
            // If initial head not found, adjust the index to insert position
            index = -index - 1;
        }

        // Move right to the end
        for (int i = index; i < requests.size(); i++) {
            headMovement += Math.abs(requests.get(i) - initialHead);
            initialHead = requests.get(i);
        }

        // Move left to the beginning
        for (int i = index - 1; i >= 0; i--) {
            headMovement += Math.abs(requests.get(i) - initialHead);
            initialHead = requests.get(i);
        }

        return headMovement;
    }
    
    // C-SCAN Disk Scheduling Algorithm
    private static int cScan(int initialHead, List<Integer> requests) {
        int headMovement = 0;

        // Sort requests in ascending order
        Collections.sort(requests);

        // Find the index where the initial head is located
        int index = Collections.binarySearch(requests, initialHead);
        if (index < 0) {
            // If initial head not found, adjust the index to insert position
            index = -index - 1;
        }

        // Move right to the end
        for (int i = index; i < requests.size(); i++) {
            headMovement += Math.abs(requests.get(i) - initialHead);
            initialHead = requests.get(i);
        }

        // Move to the beginning (circular)
        headMovement += Math.abs(0 - initialHead);
        initialHead = 0;

        // Move right again
        for (int i = 0; i < index; i++) {
            headMovement += Math.abs(requests.get(i) - initialHead);
            initialHead = requests.get(i);
        }

        return headMovement;
    }
}
