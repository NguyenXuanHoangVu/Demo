package com.example.demo.utility;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class Utility {

    // Exception handling
    /**In ra thông báo lỗi
     * Handles exceptions by printing the exception message and stack trace to the console.
     * @param e The exception to handle.
     */
    public static void handleException(Exception e) {
        System.err.println("Exception occurred: " + e.getMessage());
        e.printStackTrace();
    }

    // Export data to Excel
    /**
     * Exports data to an Excel file.
     * @param data The data to export, where each String array represents a row.
     * @param headers The headers for the Excel file.
     * @return A Workbook containing the exported data.
     * @throws IOException If an I/O error occurs.
     */
    public static Workbook exportToExcel(List<String[]> data, String[] headers) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // Tạo 1 biến workbook
        Sheet sheet = workbook.createSheet("Data"); //Tạo một bảng với tên Data

        // Tạo dòng tiêu đề của bảng
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]); // Gán nội dung tiêu đề
        }

        // Điền dữ liệu vào các dòng
        int rowNum = 1;
        for (String[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                row.createCell(i).setCellValue(rowData[i]);
            }
        }

        return workbook;
    }

    // Call other API
    /**
     * Calls an external API.
     * @param url The URL of the API.
     * @param method The HTTP method (GET, POST, etc.).
     * @param payload The request payload (if any).
     * @param headers The HTTP headers.
     * @return The response from the API.
     */
    public static ResponseEntity<String> callApi(String url, HttpMethod method, String payload, HttpHeaders headers) {
        RestTemplate restTemplate = new RestTemplate(); // Create a RestTemplate instance
        HttpEntity<String> entity = new HttpEntity<>(payload, headers); // Create an HTTP entity with payload and headers
        return restTemplate.exchange(url, method, entity, String.class); // Make the API call and return the response
    }

    // Validation
    /**
     * Validates form data and returns error messages.
     * @param result The binding result containing validation errors.
     * @return A string with error messages, or null if there are no errors.
     */
    public static String validate(BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .flatMap(error -> Stream.of((FieldError) error)) // Stream and cast to FieldError
                    .map(error -> error.getField() + " " + error.getDefaultMessage()) // Map to field and default message
                    .collect(Collectors.toList()); // Collect to list
            return String.join(", ", errors); // Join errors to a single string
        }
        return null; // Return null if there are no errors
    }
}
/*
*Comment
Exception Handling:

The handleException method is a simple utility to print the exception's message and stack trace to the console.
This can be useful for logging errors during development.

Export to Excel:

The exportToExcel method creates an Excel workbook using Apache POI.

It accepts a list of data (each String[] represents a row) and headers for the columns.

It creates the workbook and sheet, sets the header row, and fills in the data rows.

The method returns the created workbook.

Call Other API:

The callApi method uses RestTemplate to call an external API.

It takes the URL, HTTP method, payload, and headers as parameters.

It creates an HttpEntity with the payload and headers, then makes the API call using exchange method.

The method returns the response from the API as a ResponseEntity<String>.

Validation:

The validate method checks if there are validation errors in a BindingResult.

If errors are present, it collects the error messages for each field and joins them into a single string.

It returns this string of error messages, or null if there are no errors.
* */