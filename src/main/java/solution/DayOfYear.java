package solution;

public class DayOfYear {
    public static void main(String[] args) {
        DayOfYear dayOfYear = new DayOfYear();
        System.out.println(dayOfYear.dayOfYear(2016, 1, 3));
        System.out.println(dayOfYear.dayOfYear(2016, 2, 1));
    }

    public int dayOfYear(int year, int month, int day) {
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) && (month > 2)) {
            day += 1;
        }
        for (int i = 0; i < month - 1; i++) {
            day += arr[i];
        }
        return day;
    }
}
