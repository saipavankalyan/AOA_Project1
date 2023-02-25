public class House {
    int startDay;
    int endDay;
    int index;

    /**
     * House
     * @param startDay - Start Day when the house can be painted. This house cannot be painted before this day.
     * @param endDay - End Day before which the house should be painted. This house should be painted before this day.
     * @param index - The index of this house in the input sequence.
     */
    public House(int startDay, int endDay, int index) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.index = index;
    }
}