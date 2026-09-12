package backend.reporting;

import java.util.List;
import java.util.stream.Collectors;

public final class CsvReport {
    private CsvReport() {}

    public static String encode(List<List<String>> rows) {
        StringBuilder csv = new StringBuilder("account,label,balance\r\n");
        for (List<String> row : rows) {
            csv.append(row.stream().map(CsvReport::quote).collect(Collectors.joining(","))).append("\r\n");
        }
        return csv.toString();
    }

    private static String quote(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
