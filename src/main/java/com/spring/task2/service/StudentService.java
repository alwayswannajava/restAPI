package com.spring.task2.service;

import com.spring.task2.domain.Student;
import com.spring.task2.dto.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {

    Student createStudent(StudentSaveDto studentSaveDto);

    StudentDetailsDto readStudentById(long studentId);

    void deleteStudentById(long studentId);

    Student updateStudentById(long studentId, StudentSaveDto studentSaveDto);

    int countTotalPages(StudentPageDto studentPageDto);

    List<StudentInfo> findAllByPage(StudentPageDto studentPageDto);

    boolean isStudentExist(long studentId);

    void generateReport(HttpServletResponse response, StudentReportDto studentReportDto);

    SuccessFailed uploadFromFile(MultipartFile file);

}
