package airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Input is a number of meetings (start_time, end_time)。 Output is a number of time intervals (start_time, end_time), where there is no meetings.
 *
 * For exmaple, input is [[1, 3], [6, 7]], [[2, 4]], [[2, 3], [9, 12]] ]
 *
 * output [[4, 6], [7, 9]]
 */
public class MeetingTime {
    public static void main(String[] args) {
        MeetingTime time = new MeetingTime();
        List<List<Interval>> intervals = new ArrayList<List<Interval>>() {{
            add(new ArrayList<Interval>() {{
                add(new Interval(1, 3));
                add(new Interval(6, 7));
            }});
            add(new ArrayList<Interval>() {{
                add(new Interval(2, 4));
            }});
            add(new ArrayList<Interval>() {{
                add(new Interval(2, 3));
                add(new Interval(9, 12));
            }});
        }};
        List<Interval> res = time.getAvailableIntervals(intervals, 3);
        System.out.println(res);
    }
    class Point implements Comparable<Point> {
        int time;
        boolean isStart;

        Point(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Point that) {
            if (this.time != that.time || this.isStart == that.isStart) {
                return this.time - that.time;
            } else {
                return this.isStart ? -1 : 1;
            }
        }
    }

    public List<Interval> getAvailableIntervals(List<List<Interval>> intervals, int k) {
        List<Interval> res = new ArrayList<>();
        List<Point> points = new ArrayList<>();

        for (List<Interval> intervalList : intervals) {
            for (Interval interval : intervalList) {
                points.add(new Point(interval.start, true));
                points.add(new Point(interval.end, false));
            }
        }
        Collections.sort(points);

        int count = 0;
        Integer availableStart = null;
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            if (point.isStart) {
                count++;
                if (availableStart == null && i == 0 && count <= intervals.size() - k) {
                    availableStart = point.time;
                } else if (availableStart != null && count == intervals.size() - k + 1) {
                    res.add(new Interval(availableStart, point.time));
                    availableStart = null;
                }
            } else {
                count--;
                if (count == intervals.size() - k && i < points.size() - 1) {
                    availableStart = point.time;
                } else if (availableStart != null && i == points.size() - 1 && count <= intervals.size() - k) {
                    res.add(new Interval(availableStart, point.time));
                    availableStart = null;
                }
            }
        }

        return res;
    }

   static class Interval {
        public int start;
        public int end;

        public Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Interval that = (Interval) o;
            return start == that.start && end == that.end;
        }

        @Override
        public int hashCode() {
            return 31 * start + end;
        }
    }
}
