// me trying to access a csv file in java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSV {
    public static void main(String[] args) {
        List<List<String>> records = new ArrayList<>();

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("test.csv"))
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
            try (Scanner scanner = new Scanner(new File("test.csv"))) {
                while (scanner.hasNextLine()) {
                    records.add(getRecordFromLine(scanner.nextLine()));
                }
                private List<String> getRecordFromLine (String line){
                    List<String> values = new ArrayList<>();
                    try (Scanner rowScanner = new Scanner(line)) {
                        rowScanner.useDelimiter(",");
                        while (rowScanner.hasNext()) {
                            values.add(rowScanner.next());
                        }
                    }
                    return values;
                }
            /*
            import java.io. * ;
import java.util.Scanner;
            public class CSVReaderDemo {
                public static void main(String[] args) throws Exception {
                    Scanner sc = new Scanner(new File("C:\\Users\\Dell\\Desktop\\csvDemo.csv"));
                    //parsing a CSV file into the constructor of Scanner class
                    sc.useDelimiter(",");
                    //setting comma as delimiter pattern
                    while (sc.hasNext()) {
                        System.out.print(sc.next());
                    }
                    sc.close();
                    //closes the scanner
                }
            }
    }
// https://techvidvan.com/tutorials/read-csv-file-in-java/
// https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
// https://www.baeldung.com/java-csv-file-array
             */
            }
        }
    }
    }
