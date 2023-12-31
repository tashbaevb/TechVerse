package com.example.makersprojectbackend.service.impl.forms;

import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.repository.forms.EnrollRepository;
import com.example.makersprojectbackend.service.forms.EnrollService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EnrollServiceImpl implements EnrollService {
    private final EnrollRepository enrollRepository;

    @Override
    public List<Enroll> getAll() {
        return enrollRepository.findAll();
    }

    @Override
    public ResponseEntity<byte[]> exportToExcel(List<Enroll> enrolls) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Enrolls");

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
            for (Enroll enroll : enrolls) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(enroll.getId());
                row.createCell(1).setCellValue(enroll.getFullName());
                row.createCell(2).setCellValue(enroll.getPhoneNumber());
                row.createCell(3).setCellValue(enroll.getCourseName());
            }

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            String fileName = "enrolls info " +
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
