import java.util.PriorityQueue;

public class Question3_PriorityLabAccess {
    static class Student implements Comparable<Student> {
        String name;
        int priority;

        Student(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Student other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Student> lab = new PriorityQueue<>();
        lab.offer(new Student("Normal", 3));
        lab.offer(new Student("Emergency", 1));
        lab.offer(new Student("Faculty", 2));
        while (!lab.isEmpty()) {
            System.out.println(lab.poll().name);
        }
    }
}
