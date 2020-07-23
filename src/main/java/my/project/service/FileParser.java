package my.project.service;

import my.project.entity.Row;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {

    public static ArrayList<Row> openFile(String path)
    {
        ArrayList<Row> table = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(path))) {

            int line = 1;

            while (scanner.hasNextLine()) {
                try {
                    Row row = processLine(scanner.nextLine());
                    table.add(row);
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println("Line parse error has occurred in file " + path + " in line " + line + ":\n" + e.getMessage());
                }

                line++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't open a file:\n" + e.getMessage());
        }

        return table;
    }

    private static Row processLine(String line) throws IllegalArgumentException
    {
        Long id;
        String value;
        String data[];

        if ((data = line.split(",", -1)).length != 2)
            throw new IllegalArgumentException("Line doesn't match required format: <Целое число>,<Строка>");

        //Get id
        id = Long.valueOf(data[0]);

        //Get value
        value = data[1].trim();
        if (value.isBlank()) throw new IllegalArgumentException("Value mustn't be blank");

        Row row = new Row(id, value);
        return row;
    }
}
