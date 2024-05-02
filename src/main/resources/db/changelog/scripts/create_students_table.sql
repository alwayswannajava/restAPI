CREATE TABLE IF NOT EXISTS students
(
    student_id SERIAL PRIMARY KEY,
    group_id   INT         REFERENCES groups ON UPDATE CASCADE ON DELETE CASCADE,
    group_name VARCHAR(5),
    first_name VARCHAR(30) NOT NULL,
    last_name  VARCHAR(30) NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups (group_id)
);