package pet.project.currencyparser.service.report.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import pet.project.currencyparser.service.report.ReportService;
import pet.project.currencyparser.utils.SpecialCharactersEscaper;

@Component
public class CsvReportServiceImpl implements ReportService {
    private static final String REPORT_PATH = "/src/main/resources/reports/report_";
    private static final String EXTENSION = ".csv";
    private static final String SYSTEM_PROPERTY = "user.dir";
    private final SpecialCharactersEscaper escaper;

    public CsvReportServiceImpl(SpecialCharactersEscaper escaper) {
        this.escaper = escaper;
    }

    @Override
    public String generate(List<String[]> stringList) {
        String fileName = getFilePath();
        writeToFile(stringList, fileName);

        return fileName;
    }

    private String convertToString(String[] data) {
        return Stream.of(data)
                .map(escaper::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String getFilePath() {
        return System.getProperty(SYSTEM_PROPERTY) + REPORT_PATH
                + System.currentTimeMillis() + EXTENSION;
    }

    private File createFile(String fileName) {
        return new File(fileName);
    }

    private void writeToFile(List<String[]> stringList, String fileName) {
        stringList.add(0, new String[] {"name", "minPrice", "maxPrice"});

        try (PrintWriter pw = new PrintWriter(createFile(fileName))) {
            stringList.stream()
                    .map(this::convertToString)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Data didn't write in file.");
        }
    }
}
