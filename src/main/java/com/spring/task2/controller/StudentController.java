package com.spring.task2.controller;


import com.spring.task2.dto.*;
import com.spring.task2.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Log4j2
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity addStudent(@RequestBody @Valid StudentSaveDto studentSaveDto) {
        log.info("Controller level. Adding student");
        studentService.createStudent(studentSaveDto);
        return ResponseEntity.ok("Student added successfully");
    }

    @GetMapping("/{studentId}")
    public StudentDetailsDto getStudentById(@PathVariable int studentId) {
        log.info("Controller level. Getting student details with studentId: {}", studentId);
        return studentService.readStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity updateStudent(@PathVariable int studentId, @RequestBody @Valid StudentSaveDto studentSaveDto) {
        log.info("Controller level. Updating student");
        studentService.updateStudentById(studentId, studentSaveDto);
        return ResponseEntity.ok("Student with id: " + studentId + " updated successfully");
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable int studentId) {
        log.info("Controller level. Deleting student with id: {}", studentId);
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok("Student with id: " + studentId + " deleted successfully");
    }

    @PostMapping("/_list")
    public ResponseEntity<StudentPageResponse> getStudentsByPage(@RequestBody @Valid StudentPageDto studentPageDto) {
        log.info("Controller level. Getting students by pageNumber: {}, pageSize: {}",
                studentPageDto.getPageNumber(), studentPageDto.getPageSize());
        List<StudentInfo> studentInfosContent = studentService.findAllByPage(studentPageDto);
        int totalPages = studentService.countTotalPages(studentPageDto);
        return ResponseEntity.ok(new StudentPageResponse(studentInfosContent, totalPages));
    }

    @PostMapping("/_report")
    public void generateReport(HttpServletResponse httpServletResponse, @RequestBody StudentReportDto studentReportDto) {
        log.info("Controller level. Generating report");
        studentService.generateReport(httpServletResponse, studentReportDto);
    }

    @PostMapping("/upload")
    public RestResponse uploadFromFile(@RequestParam("file") MultipartFile multipart) {
        log.info("Controller level. Upload file");
        SuccessFailed successFailed = studentService.uploadFromFile(multipart);
        int successValue = successFailed.successfulRecords();
        int failedValue = successFailed.failedRecords();
        return new RestResponse(successValue, failedValue);
    }

}
