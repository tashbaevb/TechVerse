package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.forms.Application;
import com.example.makersprojectbackend.repository.CourseRepository;
import com.example.makersprojectbackend.repository.forms.ApplicationRepository;
import com.example.makersprojectbackend.service.ApplicationService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final CourseRepository courseRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, CourseRepository courseRepository) {
        this.applicationRepository = applicationRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    @Override
    public ResponseEntity<byte[]> exportToExcel(List<Application> applications) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users from AHeld");

            // Задаем стиль заголовков
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            // Создаем заголовки столбцов
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Full name");
            headerRow.createCell(2).setCellValue("Phone number");
            headerRow.createCell(3).setCellValue("Course Name");

            // Устанавливаем стиль заголовков
            for (Cell cell : headerRow) {
                cell.setCellStyle(headerStyle);
            }

            // Заполняем таблицу данными
            int rowNum = 1;
            for (Application application : applications) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(application.getId());
                row.createCell(1).setCellValue(application.getFullName());
                row.createCell(2).setCellValue(application.getPhoneNumber());
                row.createCell(3).setCellValue(application.getCourseName());
            }

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            String fileName = "applications info " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            byte[] bytes = outputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName + ".xlsx");
            headers.setContentLength(bytes.length);
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
