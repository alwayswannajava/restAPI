package com.spring.task2.mapper;

import com.spring.task2.domain.Student;
import com.spring.task2.dto.StudentDetailsDto;
import com.spring.task2.dto.StudentSaveDto;
import com.spring.task2.dto.StudentInfo;
import com.spring.task2.dto.StudentUploadDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class StudentMapper {

    public static Student mapStudentDtoToStudent(StudentSaveDto studentSaveDto) {
        log.info("Mapping studentDto to student");
        Student student = new Student();
        student.setGroupId(studentSaveDto.getGroupId());
        student.setGroupName(studentSaveDto.getGroupName());
        student.setFirstName(studentSaveDto.getFirstName());
        student.setLastName(studentSaveDto.getLastName());
        return student;
    }

    public static StudentDetailsDto mapStudentToStudentDetailsDto(Student student) {
        log.info("Mapping student to StudentDetailsDto");
        return StudentDetailsDto.builder()
                .studentId(student.getStudentId())
                .groupId(student.getGroupId())
                .groupName(student.getGroupName())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();
    }

    public static StudentInfo mapStudentToStudentInfo(Student student) {
        log.info("Mapping student to StudentInfo");
        return StudentInfo.builder()
                .groupName(student.getGroupName())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();
    }

    public static Student mapStudentUploadDtoToStudent(StudentUploadDto studentUploadDto) {
        log.info("Mapping studentUploadDto to Student");
        Student student = new Student();
        student.setGroupName(studentUploadDto.getGroupName());
        student.setFirstName(studentUploadDto.getFirstName());
        student.setLastName(studentUploadDto.getLastName());
        return student;
    }
}
