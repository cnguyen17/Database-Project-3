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
INSERT INTO courses (code, title, instructor, `max`) VALUES ('CS3810', 'Principles of Database Systems', 'Thyago Mota',   2);

CREATE TABLE students (
    id   INT         NOT NULL PRIMARY KEY,
    name VARCHAR(15) NOT NULL
);

INSERT INTO students VALUES ( 1, 'Perry Rhodan' );
INSERT INTO students VALUES ( 2, 'Icho Tolot');
INSERT INTO students VALUES ( 3, 'Deshan Apian');

CREATE TABLE enrollments (
    code VARCHAR(10) NOT NULL,
    id   INT         NOT NULL,
    PRIMARY KEY (code, id),
    FOREIGN KEY (code) REFERENCES courses(code),
    FOREIGN KEY (id)   REFERENCES students(id)
);

-- TODO: create a trigger name enroll_student that automatically increments the actual field in courses whenever a student enrolls in a course
DELIMITER $$
CREATE TRIGGER enroll_student
	AFTER INSERT
    ON enrollments  FOR EACH ROW
BEGIN

Update courses
set actual=(actual+1)
where code=NEW.code;
    -- statements
END$$    

DELIMITER ;

-- Test for insertions
Select * from enrollments;

SELECT * FROM courses;
insert into enrollments values('CS3810',1);
Select * from enrollments;

SELECT * FROM courses;

-- TODO: create a trigger name drop_student that automatically decrements the actual field in courses whenever a student drops from a course
DELIMITER $$

CREATE TRIGGER drop_student
    AFTER DELETE
    ON enrollments  FOR EACH ROW
BEGIN

Update courses
set actual=(actual-1)
where code=OLD.code;
    -- statements
END$$    

DELIMITER ;

-- Test for dropping trigger
select * from enrollments;

select * from courses;

DELETE from enrollments where id=1;-- values('CS3810',1);

select * from enrollments;

select * from courses;
-- TODO: create a stored procedure name list_students that returns a list of ids and names of all students currently enrolled in a given course 

CREATE USER 'enrollments' IDENTIFIED BY '123456';
GRANT ALL ON enrollments.* TO 'enrollments';

DELIMITER $$
CREATE PROCEDURE list_students(IN courseCode varchar(6))
BEGIN

SELECT * FROM students 
        WHERE id IN (Select id from enrollments where code=courseCode);
END //

DELIMITER ;

-- Test data insertion
insert into enrollments values('CS3810',4);
insert into enrollments values('CS3810',5);
insert into enrollments values('CS3810',6);
CALL list_students('CS3810'); 


-- CREATE USER 'enrollments' IDENTIFIED BY '135791';
-- GRANT ALL ON enrollments.* TO 'enrollments';
