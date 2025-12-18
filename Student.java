public class Student {
    String name;
    int score;
    
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String toString() {
        return name + " - " + score;
    }
}
