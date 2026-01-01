import java.sql.*;
import java.util.ArrayList;

public class DataContext {

    public DataContext() {
        createTable(); // Ensure table exists
    }

    public Connection getConnected() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
    }

    public void createTable() {
        try {
            Connection con = getConnected();
            Statement st = con.createStatement();
            // id, name, score, course, age, gender
            String query = "CREATE TABLE IF NOT EXISTS student (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "score INT, " +
                    "course VARCHAR(50), " +
                    "age INT, " +
                    "gender VARCHAR(10))";
            st.executeUpdate(query);
            con.close(); // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        Connection con = getConnected();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from student");
        while (rs.next()) {
            Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("score"),
                    rs.getString("course"),
                    rs.getInt("age"),
                    rs.getString("gender"));
            students.add(s);
        }
        con.close();
        return students;
    }

    public void saveStudent(Student s) throws SQLException {
        String query = "insert into student (name, score, course, age, gender) values(?,?,?,?,?)";
        Connection con = getConnected();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, s.name);
        ps.setInt(2, s.score);
        ps.setString(3, s.course);
        ps.setInt(4, s.age);
        ps.setString(5, s.gender);
        ps.executeUpdate();
        con.close();
    }

    public void deleteStudent(int id) throws SQLException {
        String query = "delete from student where id=?";
        Connection con = getConnected();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }

    public void updateStudent(Student s) throws SQLException {
        String query = "update student set name=?, score=?, course=?, age=?, gender=? where id=?";
        Connection con = getConnected();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, s.name);
        ps.setInt(2, s.score);
        ps.setString(3, s.course);
        ps.setInt(4, s.age);
        ps.setString(5, s.gender);
        ps.setInt(6, s.id);
        ps.executeUpdate();
        con.close();
    }
}
