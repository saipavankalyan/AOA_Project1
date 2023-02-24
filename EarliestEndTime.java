import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class EarliestEndTime {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n, m;
        int index = 1;
        int currentDay = 1;
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

        PriorityQueue<House> housesEarliestEndTime = new PriorityQueue<>(m, (h1, h2) -> h1.endDay - h2.endDay);

        ArrayList<Integer> indicesEarliestEndTime = new ArrayList<>();

        while (currentDay <= n) {
            while (currentHouseIndex < m && houses[currentHouseIndex].startDay <= currentDay) {
                housesEarliestEndTime.add(houses[currentHouseIndex]);
                currentHouseIndex++;
            }

            if (housesEarliestEndTime.size() > 0) {
                House h = housesEarliestEndTime.poll();
                if (h.startDay <= currentDay && h.endDay >= currentDay)
                    indicesEarliestEndTime.add(h.index);
                else
                    continue;
            }
            currentDay++;
        }

        System.out.println(indicesEarliestEndTime);

        sc.close();
    }
}