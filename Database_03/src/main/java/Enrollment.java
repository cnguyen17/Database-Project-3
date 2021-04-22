/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Enrollment
 * Student(s) Name(s):
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "enrollments")
public class Enrollment implements Serializable {
    @Id
    private String code;
    private int id;

    public int getId() {

        return id;
    }


    public void setId(int id) {

        this.id = id;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment)) return false;
        Enrollment that = (Enrollment) o;
        return getId () == that.getId () && getCode ().equals (that.getCode ());
    }


    @Override
    public int hashCode() {
        return Objects.hash (getCode (), getId ());
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "code='" + code + '\'' +
                ", id=" + id +
                '}';
    }
}