package my.project.service;

import my.project.entity.Result;
import my.project.entity.Row;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DataStorage {

    private ArrayList<Row> leftTable;
    private ArrayList<Row> rightTable;

    public DataStorage(String leftTablePath, String rightTablePath)
    {
        leftTable = FileParser.openFile(leftTablePath);
        rightTable = FileParser.openFile(rightTablePath);
    }

    public ArrayList<Row> getLeftTable() {
        return leftTable;
    }

    public ArrayList<Row> getRightTable() {
        return rightTable;
    }

    public void printRightOuterJoinWithArrayList()
    {
        printResults(Query.rightOuterJoinWithArrayList(leftTable, rightTable), "ArrayList");
    }

    public void printRightOuterJoinWithHashMap()
    {
        printResults(Query.rightOuterJoinWithHashMap(Converter.arrayListToHashMap(leftTable),
                                                     Converter.arrayListToHashMap(rightTable)),
                "HashMap");
    }

    private void printResults(List<Result> results, String type)
    {
        results.sort(Comparator.naturalOrder());
        System.out.println("Results using " + type);
        System.out.printf("%-15.15s\t%-15.15s\t%-15.15s\n", "ID", "LEFT_VALUE", "RIGHT_VALUE");

        for (Result res : results)
            System.out.printf("%-15.15s\t%-15.15s\t%-15.15s\n", res.getId(), res.getLeftValue(), res.getRightValue());
    }

    public void printTables() {
        String sep = System.lineSeparator();
        StringBuilder res = new StringBuilder();
        res.append("Left table: " + sep);
        for (Row row : leftTable)
            res.append(row + sep);

        res.append("Right table: " + sep);
        for (Row row : rightTable)
            res.append(row + sep);

        System.out.println(res.toString());
    }
}
