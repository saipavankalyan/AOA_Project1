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

        n = sc.nextInt();
        m = sc.nextInt();

        House[] houses = new House[m];
        PriorityQueue<House> housesMinimumTime = new PriorityQueue<>(m,
                (h1, h2) -> (h1.endDay - h1.startDay) - (h2.endDay - h2.startDay));

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            houses[i] = new House(s, e, index);
            index++;
        }

        ArrayList<Integer> indicesMinimumTime = new ArrayList<>();

        while (currentDay <= n) {
            while (currentHouseIndex < m && houses[currentHouseIndex].startDay <= currentDay) {
                housesMinimumTime.add(houses[currentHouseIndex]);
                currentHouseIndex++;
            }

            if (housesMinimumTime.size() > 0) {
                House h = housesMinimumTime.poll();
                if (h.startDay <= currentDay && h.endDay >= currentDay)
                    indicesMinimumTime.add(h.index);
                else
                    continue;
            }
            currentDay++;
        }

        System.out.println(indicesMinimumTime);

        sc.close();
    }
}
