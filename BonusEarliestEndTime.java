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

        n = sc.nextInt();
        m = sc.nextInt();

        House[] houses = new House[m];

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            houses[i] = new House(s, e, index);
            index++;
        }

        PriorityQueue<House> housesEarliestEndTime = new PriorityQueue<>(m, Comparator.<House>comparingInt(h -> h.endDay)
                                                                                      .thenComparingInt(h -> h.startDay));

        ArrayList<Integer> paintedHouses = new ArrayList<>();

        int currentDay = 1;

        /**
         * While the current day is valid (less than n)
         * Though it appears to run for n times, You try to paint at least a single house on a single day.
         * You are not painting any house, only if it's not possible.
         * But, next you move to the next valid day (start day of next house).
         * (not incremented one by one).
         */
        while (currentDay <= n) {

            /**
             * Change current day, if there are no houses to paint
             */
            if(housesEarliestEndTime.isEmpty()) {

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
                housesEarliestEndTime.add(houses[currentHouseIndex]);
                currentHouseIndex++;
            }


            /**
             * Find the house to paint on the day
             */
            House house = housesEarliestEndTime.poll();

            /**
             * Delete all houses that cannot be painted on that day. (End date is less). At max, all m houses are not valid
             * Runs at max for m log m.
             */
            while (house.endDay < currentDay) {
                if(housesEarliestEndTime.isEmpty()) {
                    house = null;
                    break;
                }
                house = housesEarliestEndTime.poll();
            }

            /**
             * Paint the first valid house (End data greater than or equal to endDate).
             */
            if(house != null) {
                paintedHouses.add(house.index);
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
