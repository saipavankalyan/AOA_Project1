import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class LatestStartTime {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n, m;
        int index = 1;
        int currentDay = 1;
        int currentHouseIndex = 0;

        n = sc.nextInt();
        m = sc.nextInt();

        House[] houses = new House[m];
        PriorityQueue<House> housesLatestStartTime = new PriorityQueue<>(m, (h1, h2) -> h2.startDay - h1.startDay);

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            houses[i] = new House(s, e, index);
            index++;
        }

        ArrayList<Integer> indicesLatestStartTime = new ArrayList<>();

        while (currentDay <= n) {
            while (currentHouseIndex < m && houses[currentHouseIndex].startDay <= currentDay) {
                housesLatestStartTime.add(houses[currentHouseIndex]);
                currentHouseIndex++;
            }

            if (housesLatestStartTime.size() > 0) {
                House h = housesLatestStartTime.poll();
                if (h.startDay <= currentDay && h.endDay >= currentDay)
                    indicesLatestStartTime.add(h.index);
                else
                    continue;
            }
            currentDay++;
        }

        System.out.println(indicesLatestStartTime);

        sc.close();
    }
}
