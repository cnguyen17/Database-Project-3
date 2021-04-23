-- CS3810 - Principles of Database Systems - Spring 2021
-- Instructor: Thyago Mota
-- Description: enrollments database
-- Student(s) Name(s): Calvin Nguyen and Echglene Woy

DROP DATABASE enrollments;
CREATE DATABASE enrollments;
USE enrollments;

CREATE TABLE courses (
                         code       VARCHAR(7)  NOT NULL PRIMARY KEY,
                         title      VARCHAR(35) NOT NULL,
                         instructor VARCHAR(15) NOT NULL,
                         `max`      INT         NOT NULL,
                         actual     INT         DEFAULT 0,
                         CHECK (actual >= 0 AND actual <= `max`)
);

INSERT INTO courses (code, title, instructor, `max`) VALUES ('CS1030', 'Computer Science Principles',    'Jody Paul',     5);
INSERT INTO courses (code, title, instructor, `max`) VALUES ('CS1050', 'Computer Science 1',             'David Kramer',  3);
INSERT INTO courses (code, title, instructor, `max`) VALUES ('CS2050', 'Computer Science 2',             'Steve Geinitz', 3);
INSERT INTO courses (code, title, instructor, `max`) VALUES ('CS3810', 'Principles of Database Systems', 'Thyago Mota',   4);

CREATE TABLE students (
                          id   INT         NOT NULL PRIMARY KEY,
                          name VARCHAR(15) NOT NULL
);

INSERT INTO students VALUES ( 1, 'Perry Rhodan' );
INSERT INTO students VALUES ( 2, 'Icho Tolot');
INSERT INTO students VALUES ( 3, 'Deshan Apian');


-- test to get list of students from courses
SELECT Student.name, Courses.title
FROM students
         INNER JOIN Course ON Course.id = Students.id
GROUP BY Student.id, Student.name
HAVING COUNT(*) = 1 ;

CREATE TABLE enrollments (
                             code VARCHAR(10) NOT NULL,
                             id   INT         NOT NULL,
                             PRIMARY KEY (code, id),
                             FOREIGN KEY (code) REFERENCES courses(code),
                             FOREIGN KEY (id)   REFERENCES students(id)
);

-- TODO: create a trigger name enroll_student that automatically increments the actual field in courses whenever a student enrolls in a course

delimiter |
CREATE TRIGGER enroll_student AFTER INSERT ON enrollments
    FOR EACH ROW
BEGIN
    UPDATE courses SET actual = courses.actual +1 WHERE code = NEW.code;
END;
|
delimiter ;

-- Test for insertions
    Select * from enrollments;

    SELECT * FROM courses;
    insert into enrollments values('CS3810',3);
    Select * from enrollments;

    SELECT * FROM courses;

-- TODO: create a trigger name drop_student that automatically decrements the actual field in courses whenever a student drops from a course

delimiter |
CREATE TRIGGER drop_student AFTER DELETE ON enrollments
    FOR EACH ROW
BEGIN
    UPDATE courses SET actual = courses.actual -1 WHERE code = OLD.code;
END;
|
delimiter ;

-- Test for dropping trigger
        select * from enrollments;

        select * from courses;

        DELETE from enrollments where id=1;-- values('CS3810',1);

        select * from enrollments;

        select * from courses;
        
        
-- TODO: create a stored procedure name list_students that returns a list of ids and names of all students currently enrolled in a given course
        DELIMITER |
        CREATE PROCEDURE list_students(
            IN course_code varchar(10)
        )
        BEGIN
        SELECT s.id, s.name
        FROM students s
                 INNER JOIN enrollments e ON s.id = e.id
                 INNER JOIN courses c ON e.code = c.code
        WHERE e.code = course_code;
    END
        |
DELIMITER ;

-- Test data insertion
    insert into enrollments values('CS3810',1);
    insert into enrollments values('CS3810',2);
    insert into enrollments values('CS3810',3);
    CALL list_students('CS3810');


CREATE USER 'enrollments' IDENTIFIED BY '135791';
GRANT ALL ON enrollments.* TO 'enrollments';
