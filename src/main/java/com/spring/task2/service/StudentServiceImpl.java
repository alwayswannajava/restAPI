package com.spring.task2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.task2.domain.Student;
import com.spring.task2.dto.*;
import com.spring.task2.exception.FileUploadException;
import com.spring.task2.exception.StudentNotFoundException;
import com.spring.task2.mapper.StudentMapper;
import com.spring.task2.repository.GroupRepository;
import com.spring.task2.repository.StudentRepository;
import com.spring.task2.specification.SearchCriteria;
import com.spring.task2.specification.StudentSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class StudentServiceImpl implements StudentService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Override
    public Student createStudent(StudentSaveDto studentSaveDto) {
        log.info("Service level. Creating student");
        Student student = StudentMapper.mapStudentDtoToStudent(studentSaveDto);
        groupService.isGroupExistByGroupName(student.getGroupName());
        return studentRepository.save(student);
    }

    @Override
    public StudentDetailsDto readStudentById(long studentId) {
        log.info("Service level. Reading student by id: {}", studentId);
        isStudentExist(studentId);
        Student student = studentRepository.findById(studentId).get();
        return StudentMapper.mapStudentToStudentDetailsDto(student);
    }

    @Override
    public void deleteStudentById(long studentId) {
        log.info("Service level. Deleting student by id: {}", studentId);
        isStudentExist(studentId);
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student updateStudentById(long studentId, StudentSaveDto updatedStudentSaveDto) {
        log.info("Service level. Updating student by id: {}", studentId);

        isStudentExist(studentId);
        groupService.isGroupExistByGroupName(updatedStudentSaveDto.getGroupName());

        Student student = studentRepository.findById(studentId).get();
        student.setGroupId(updatedStudentSaveDto.getGroupId());
        student.setGroupName(updatedStudentSaveDto.getGroupName());
        student.setFirstName(updatedStudentSaveDto.getFirstName());
        student.setLastName(updatedStudentSaveDto.getLastName());
        return studentRepository.save(student);
    }

    @Override
    public int countTotalPages(StudentPageDto studentPageDto) {
        log.info("Service level. Find all students by page number: {}, page size: {}",
                studentPageDto.getPageNumber(), studentPageDto.getPageSize());

        int pageNumber = studentPageDto.getPageNumber();
        int pageSize = studentPageDto.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return studentRepository.findAll(pageable).getTotalPages();
    }

    @Override
    public List<StudentInfo> findAllByPage(StudentPageDto studentPageDto) {
        log.info("Service level. Finding all students by page: {}", studentPageDto.getPageNumber());
        String key = studentPageDto.getKey();
        String operation = studentPageDto.getOperation();
        Object value = studentPageDto.getValue();

        SearchCriteria searchCriteria = new SearchCriteria(key, operation, value);
        StudentSpecification specification = new StudentSpecification(searchCriteria);

        return studentRepository.findAll(specification).stream()
                .map(StudentMapper::mapStudentToStudentInfo)
                .toList();
    }

    public boolean isStudentExist(long studentId) {
        log.info("Service level. Checking if student with id: {} exists", studentId);
        if (studentRepository.existsById(studentId)) {
            return true;
        } else {
            log.error("Service level. Student does not exist, exception has thrown");
            throw new StudentNotFoundException("Student with id " + studentId + " not found");
        }
    }

    @Override
    public void generateReport(HttpServletResponse response,
                               StudentReportDto studentReportDto) {

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.txt");

        String key = studentReportDto.getKey();
        String operation = studentReportDto.getOperation();
        Object value = studentReportDto.getValue();

        SearchCriteria searchCriteria = new SearchCriteria(key, operation, value);
        StudentSpecification specification = new StudentSpecification(searchCriteria);

        try {
            StringBuilder sb = new StringBuilder();
            List<StudentInfo> students = studentRepository.findAll(specification)
                    .stream().map(StudentMapper::mapStudentToStudentInfo).toList();

            for (StudentInfo student : students) {
                sb.append(student.getGroupName())
                        .append(" ")
                        .append(student.getFirstName())
                        .append(" ")
                        .append(student.getLastName())
                        .append("\n");
            }

            response.getOutputStream().write(sb.toString().getBytes());
            response.getOutputStream().flush();

        } catch (IOException e) {
            log.error("Generating report failed", e);
            throw new RuntimeException("Error generating text file", e);
        }
    }

    @Override
    public SuccessFailed uploadFromFile(MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();

            List<Student> students = objectMapper.readValue(fileBytes, new TypeReference<List<StudentUploadDto>>() {
                    })
                    .stream()
                    .map(StudentMapper::mapStudentUploadDtoToStudent)
                    .toList();

            List<Student> validatedStudents = validateRecords(students);

            int successfulRecords = validatedStudents.size();
            int failedRecords = students.size() - validatedStudents.size();

            SuccessFailed successFailed = new SuccessFailed(successfulRecords, failedRecords);

            studentRepository.saveAll(validatedStudents);

            return successFailed;
        } catch (IOException e) {
            log.error("File upload failed");
            throw new FileUploadException(e.getMessage());
        }
    }


    private List<Student> validateRecords(List<Student> studentList) {
        log.info("Validating records");
        return studentList.stream().
                 filter(student -> groupRepository.existsByGroupName(student.getGroupName())).
                 filter(student -> student.getGroupName().matches(GroupSaveDto.GROUP_NAME_REGEX_PATTERN))
                .filter(student -> student.getFirstName().matches(StudentSaveDto.FIRST_NAME_AND_LAST_NAME_REGEX))
                .filter(student -> student.getLastName().matches(StudentSaveDto.FIRST_NAME_AND_LAST_NAME_REGEX))
                .toList();
    }
}
