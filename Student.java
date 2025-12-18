public class Student {
    String name;
    int score;
    String grade;
    int age;
    
    public Student(String name, int score, String grade, int age) {
        this.name = name;
        this.score = score;
        this.grade = grade;
        this.age = age;
    }
    
    public String toString() {
        return name + " - Score: " + score + " - Grade: " + grade + " - Age: " + age;
    }
}
