import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BonusEarliestEndTime {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n, m;
        int index = 1;
        int currentHouseIndex = 0;

        /**
         * Read n (Number of days 1..n) and m (Number of House).
         */
        n = sc.nextInt();
        m = sc.nextInt();

        /**
         * Read houses.
         */
        House[] houses = new House[m];

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            houses[i] = new House(s, e, index);
            index++;
        }

        /**
         * Priority Queue based on the End Day of the availability of House.
         */
        PriorityQueue<House> priorityQHouses = new PriorityQueue<>(m, Comparator.<House>comparingInt(h -> h.endDay)
                                                                                      .thenComparingInt(h -> h.startDay));

        ArrayList<Integer> paintedHouses = new ArrayList<>();

        int currentDay = 1;

        /**
         * While the current day is valid (less than n).
         * Though it appears to run for n times, You try to paint at least a single house on a single day.
         * You are not painting any house, only if it's not possible.
         * But, next you move to the next valid day (start day of next house) where you are sure you can paint at least one house.
         * (not incremented one by one).
         * So, the worst case, you will be iterating for 2m days. (m days, where you will be successfully painting a house,
         * m days where you won't be painting any house)
         * So, the time complexity is O(m log m).
         */
        while (currentDay <= n) {

            /**
             * Change current day, if there are no houses to paint
             */
            if(priorityQHouses.isEmpty()) {

                /**
                 * If all houses have been processed, break
                 */
                if(currentHouseIndex == m) {
                    break;
                }

                /**
                 * Rather than going through unprocessable days, start from the next valid day.
                 */
                currentDay = houses[currentHouseIndex].startDay;
            }

            /**
             * Add all the houses that could be pained the current day
             * Runs At max for m iterations. (m log m).
             */
            while (currentHouseIndex < m && houses[currentHouseIndex].startDay == currentDay) {
                priorityQHouses.add(houses[currentHouseIndex]);
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
                 * Choose the house that starts the latest.
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
             * After painting move to next day.
             */
            currentDay += 1;
        }

        System.out.println(paintedHouses);
        System.out.println(paintedHouses.size());

        sc.close();
    }

}
