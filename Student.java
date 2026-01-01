public class Student {
  int id;
  String name;
  int score;
  String course;
  int age;
  String gender;

  public Student(int id, String name, int score, String course, int age, String gender) {
    this.id = id;
    this.name = name;
    this.score = score;
    this.course = course;
    this.age = age;
    this.gender = gender;
  }

  public Student(String name, int score, String course, int age, String gender) {
    this(0, name, score, course, age, gender);
  }

  public String toString() {
    return name + " " + score + " " + course;
  }
}
