/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Student
 * Student(s) Name(s): Calvin Nguyen and Echglene Woy
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    private int id;

    private String name;

//    @ElementCollection
//    @CollectionTable(name = "student")
//    private List<String> student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "-" + name;
    }
}
