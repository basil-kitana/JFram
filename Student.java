public class Student {
    String name;
    int score;
    String course;
    int age;
    String gender;
    
    public Student(String name, int score, String course, int age, String gender) {
        this.name = name;
        this.score = score;
        this.course = course;
        this.age = age;
        this.gender = gender;
    }
    
    public String toString() {
        return name + " - Score: " + score + " - Course: " + course + " - Age: " + age + " - Gender: " + gender;
    }
}
