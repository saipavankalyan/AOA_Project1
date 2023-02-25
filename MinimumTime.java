import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class MinimumTime {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n, m;
        int index = 1;
        int currentDay = 1;
        int currentHouseIndex = 0;

        /**
         * Read n (number of days) and m (number of houses)
         **/
        n = sc.nextInt();
        m = sc.nextInt();

        House[] houses = new House[m];

        /**
         * Read houses
         */
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            houses[i] = new House(s, e, index);
            index++;
        }

        /**
         * Priority Queue based on the duration of the time a house can be painted. (endDay - startDay).
         */
        PriorityQueue<House> priorityQHouses = new PriorityQueue<>(m, Comparator.<House>comparingInt(h -> (h.endDay - h.startDay))
                                                                                .thenComparingInt(h -> h.endDay));

        ArrayList<Integer> paintedHouses = new ArrayList<>();

        /**
         * For each possible day (1..n).
         */
        while (currentDay <= n) {

            /**
             * For each unseen house, that can be painted on this day (startDay <= currentDay), add the house to the priority queue.
             */
            while (currentHouseIndex < m && houses[currentHouseIndex].startDay <= currentDay) {
                priorityQHouses.add(houses[currentHouseIndex]);

                /**
                 * Move to the next house.
                 */
                currentHouseIndex++;
            }

            /**
             * To track if a house is painted on this day.
             */
            boolean painted = false;

            /**
             * If there are houses that could painted on this dau and no house is painted on this day.
             */
            while (!painted && priorityQHouses.size() > 0) {

                /**
                 * Choose the house that has the minimum duration (difference between endDay and startDay).
                 */
                House h = priorityQHouses.poll();

                /**
                 * Paint the house only if the it's is valid for the current day.
                 */
                if (h.startDay <= currentDay && h.endDay >= currentDay) {
                    paintedHouses.add(h.index);
                    painted = true;
                }

            }

            /**
             * Move to next day.
             */
            currentDay++;
        }

        System.out.println(paintedHouses);
        System.out.println(paintedHouses.size());

        sc.close();
    }
}
