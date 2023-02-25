import java.util.Scanner;
import java.util.ArrayList;

public class EarliestStartTime {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n, m;
        int index = 1;
        int currentDay = 1;
        int currentHouseIndex = 0;

        /**
         * Read n (number of days) and m (number of houses)
         */
        n = sc.nextInt();
        m = sc.nextInt();

        House[] houses = new House[m];

        /**
         * Read the houses
         */
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            houses[i] = new House(s, e, index);
            index++;
        }

        ArrayList<Integer> paintedHouses = new ArrayList<>();

        /**
         * Iterate for each day until all houses are seen
         */
        while (currentDay <= n && currentHouseIndex < m) {

            /**
             * Choose the house, that is available the earliest.
             */
            House h = houses[currentHouseIndex];

            /**
             * If the current house cannot be painted, skip the house without painting
             */
            if (h.endDay < currentDay) {
                currentHouseIndex += 1;

                /**
                 * Current house is ready for painting on the current day, paint the house and move to the next house
                 */
            } else if (h.startDay <= currentDay) {
                paintedHouses.add(h.index);
                currentHouseIndex += 1;

                /**
                 * Move to the next day
                 */
                currentDay += 1;

                /**
                 * Current house is not yet ready to be painting. (OPTIMIZATION - OPTIONAL) - Move to the day,
                 * when the current house can be painted as there will be no house to be painted in the middle days.
                 */
            } else {
                currentDay = h.startDay;
            }
        }

        System.out.println(paintedHouses);
        System.out.println(paintedHouses.size());

        sc.close();
    }
}