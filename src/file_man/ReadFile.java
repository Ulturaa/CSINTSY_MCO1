package src.file_man;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadFile
 */
public class ReadFile {

  public List<List<String>> records = new ArrayList<List<String>>();

  public ReadFile() {
  } 

  // CSV parsing from https://www.baeldung.com/java-csv-file-array
  public void initRead(String fileName) {
    Boolean skipFirst = false;
    try (BufferedReader br = new BufferedReader(new InputStreamReader(
            this.getClass().getResourceAsStream("../" + fileName)))) {
      String line = "";
      while ((line = br.readLine()) != null) {
        if (line.charAt(0) == '#') { // implementation of comment within CSV with # token
          continue;
        }
        if (!skipFirst) { // skip first line CSV header
          skipFirst = true;
          continue;
        }
        records.add(parseLine(line));
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  private static List<String> parseLine(String line) {
    List<String> values = new ArrayList<>();
    boolean inQuotes = false;
    StringBuilder currentValue = new StringBuilder();

    for (char c : line.toCharArray()) {
      if (c == '"') {
        inQuotes = !inQuotes;
      } else if (c == ',' && !inQuotes) {
        values.add(currentValue.toString());
        currentValue = new StringBuilder();
      } else {
        currentValue.append(c);
      }
    }
    values.add(currentValue.toString());
    return values;
  }
}
