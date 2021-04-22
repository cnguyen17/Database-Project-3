/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Controller
 * Student(s) Name(s): Calvin Nguyen and Echglene Woy
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Controller {

    private EntityManager em;
    private Session session;

    Course course = new Course();
    Student student = new Student();


    public Controller() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        em = emf.createEntityManager();
        session = em.unwrap(Session.class);
    }

    // TODO: return a Student entity from the given id (or null if the entity does not exist)
    // Test Passed

    public Student getStudent(int id) {
        try {
            return em.getReference(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: add the given student entity, returning true/false depending whether the operation was successful or not
   // Test Passed
    public boolean addStudent(final Student student) {
        try {
            em.persist(student);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // TODO: return a list of all Course entities
    // Test Passed
    public List<Course> getCourses() {
        List<Course> courses = session.createQuery("FROM Course").getResultList();
        return courses;
    }

    // TODO: enroll a student to a course based on the given parameters, returning true/false depending whether the operation was successful or not
    // Test Passed
    public boolean enrollStudent(String code, int id) {
        try{
            session.beginTransaction();
            Enrollment enroll = new Enrollment();
            enroll.setCode(code);
            enroll.setId(id);
            session.save(enroll);
            session.getTransaction().commit();
            session.evict(enroll);
            session.clear();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // TODO: drop a student from a course based on the given parameters, returning true/false depending whether the operation was successful or not
    // Test Passed
    public boolean dropStudent(String code, int id) {
        try{
            Transaction transaction = session.beginTransaction();
            String sql = ("DELETE FROM Enrollment WHERE code= :code AND id= :id");
            Query query = session.createQuery(sql);
            query.setParameter("code", code);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // TODO: return a list of all Student entities enrolled in the given course (hint: use the stored procedure 'list_students')
    // Test Passed
    public List<Student> getStudentsEnrolled(String course) {
        List<Student> students = (List<Student>) session.createSQLQuery("CALL list_students(:course_code)")
                .addEntity(Student.class)
                .setParameter("course_code", course).list();
        return students;
    }
}
