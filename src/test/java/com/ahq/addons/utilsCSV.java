package com.ahq.addons;

import com.ahq.globals.BrowserGlobal;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class utilsCSV {
    
    private static final char CSV_SEPARATOR = ',';
    private static final char CSV_QUOTE_CHARACTER = '"';
    private static final char CSV_ESCAPE_CHARACTER = '\\';
    
    /**
     * Creates csv data
     *
     * @param fileName
     */
    public static void createCSVTestData(Boolean appendCSV, String fileName, String[] columnHeadersToAdd, String[] valuesToAdd) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, appendCSV));
        // readDataLineByLine(filePath);
        List<String[]> rows = new LinkedList<String[]>();
        if (appendCSV = true) {
            rows.add(columnHeadersToAdd);
        }
        rows.add(valuesToAdd);
        csvWriter.writeAll(rows);
        csvWriter.close();
    }
    
    /**
     * Reads current csv data
     *
     * @param file [String] File path/name
     */
    public static String readDataLineByLine(String file) {
        String fileContents = "empty";
        try {
            // Create an object of FileReader class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);
            // create csvReader object passing file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    // System.out.print(cell + "\t");
                }
                // System.out.println();
                fileContents = "populated";
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContents;
    }
    
    /**
     * Write a single value to a specific row and 1 or more columns. <br>
     * This is useful for updating or initialising a single row of data in a csv file.
     *
     * @param value         [String] The value to write to the desired cell
     * @param rowNumber     [int] The row number to update
     * @param columnHeaders [List] A comma delimited list of column headers to update
     * @param filePath      [String] The file path of the csv file to update. Either absolute or relative to the project root
     */
    public static void writeValueToRowAndColumns(String value, int rowNumber, List<String> columnHeaders, String filePath) throws Exception {
        // Start CSV reader
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build()) {
            List<String[]> lines = reader.readAll();
            // Fail if desired row number is out of bounds
            if (!(rowNumber >= 0 && rowNumber < lines.size())) {
                BrowserGlobal.iFailStepWithInfo("Row number '" + rowNumber + "' is out of bounds for csv file '" + filePath + "'");
            }
            // Update each data point that matches a desired column header
            for (int i = 0; i < lines.get(0).length; i++) {
                if (columnHeaders.contains(lines.get(0)[i])) {
                    System.out.println("Writing '" + value + "' to column '" + lines.get(0)[i] + "' in row " + rowNumber);
                    lines.get(rowNumber)[i] = value;
                    columnHeaders.remove(lines.get(0)[i]);
                }
            }
            // Fail if any desired column headers were not found
            if (!columnHeaders.isEmpty()) {
                BrowserGlobal.iFailStepWithInfo("The following column headers were not found: " + columnHeaders);
            }
            // Write the updated CSV file except the last line
            try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), CSV_SEPARATOR, CSV_QUOTE_CHARACTER, CSV_ESCAPE_CHARACTER, System.lineSeparator())) {
                writer.writeAll(lines.subList(0, lines.size() - 1));
            }
            // Write the last line without a new line character to prevent multiple new lines being created!
            try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true), CSV_SEPARATOR, CSV_QUOTE_CHARACTER, CSV_ESCAPE_CHARACTER, "")) {
                writer.writeNext(lines.get(lines.size() - 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}