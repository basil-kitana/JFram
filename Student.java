public class Student {
    String name;
    int score;
    String course;
    int age;
    
    public Student(String name, int score, String course, int age) {
        this.name = name;
        this.score = score;
        this.course = course;
        this.age = age;
    }
    
    public String toString() {
        return name + " - Score: " + score + " - Course: " + course + " - Age: " + age;
    }
}
