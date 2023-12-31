package com.example.makersprojectbackend.service.impl.forms;

import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.repository.forms.FeedbackRepository;
import com.example.makersprojectbackend.service.forms.FeedbackService;
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
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback create(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getById(Long id) {
        return feedbackRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<byte[]> exportToExcel(List<Feedback> feedbacks) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Feedbacks");

            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Full name");
            headerRow.createCell(2).setCellValue("Phone number");

            for (Cell cell : headerRow) {
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (Feedback feedback : feedbacks) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(feedback.getId());
                row.createCell(1).setCellValue(feedback.getFullName());
                row.createCell(2).setCellValue(feedback.getPhoneNumber());
            }

            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            String fileName = "feedbacks info " +
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
