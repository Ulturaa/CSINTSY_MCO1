package csintsy.file_man;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadFile
 */

// CHANGED THIS TO READ HEURISTICS 

public class ReadFile {

    public List<List<String>> records = new ArrayList<>();

    public ReadFile() {}

    // CSV parsing from https://www.baeldung.com/java-csv-file-array
    public boolean initRead(String fileName) {
        boolean skipFirst = true;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                        this.getClass().getResourceAsStream("/" + fileName)))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) continue;

                if (skipFirst) {
                    skipFirst = false;
                    continue;
                }

                records.add(parseLine(line));
            }

        } catch (Exception e) {
            System.out.print("Error: ");
            System.err.println(e.getMessage());
            System.out.print(" Cannot open file");
            return false; // cannot find file
        }
        return true; // file found
    }

    private static List<String> parseLine(String line) {
        List<String> values = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder currentValue = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                values.add(currentValue.toString().trim());
                currentValue = new StringBuilder();
            } else {
                currentValue.append(c);
            }
        }
        values.add(currentValue.toString().trim()); // add last value
        return values;
    }
}
