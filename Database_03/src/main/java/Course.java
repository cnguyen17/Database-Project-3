import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Student
 * Student(s) Name(s):
 */
@Entity
@Table(name = "courses")

public class Course implements Serializable {

    @Id
    private String code;
    private String title;
    private String instructor;
    private int max;
    private int actual;
    private int remain;
    @ElementCollection
    @CollectionTable(name = "courses")
    private List<String> courses;


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public void setInstructor(String instructor) {

        this.instructor = instructor;
    }

    public String getInstructor() {

        return instructor;
    }

    public int getMax() {

        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }
    public void getCourseList(){

    }

    public int getRemain() {
        return this.max - this.actual;
    }
    public void setRemain(int remain) {
        this.remain = remain;
    }

    @Override
    public int hashCode() {
        return Objects.hash (getCode (), getMax (), getActual (), getTitle (), getInstructor (), getRemain ());
    }

    @Override public String toString() {
        return   String.format("%-7s| %-40s| %-15s| %-3s | %-6s | %-3s",
                code , title ,instructor , max ,actual , getRemain () );
    }
}