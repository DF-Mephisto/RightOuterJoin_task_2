package my.project;

import my.project.service.DataStorage;

public class Main {
    public static void main(String... args)
    {
        if (args.length != 2)
            System.out.println("Run parameters should be: [leftTableFileName, rightTableFileName]");
        else
            process(args[0], args[1]);
    }

    private static void process(String leftTablePath, String rightTablePath)
    {
        DataStorage ds = new DataStorage(leftTablePath, rightTablePath);
        ds.printTables();
        ds.printRightOuterJoinWithArrayList();
        ds.printRightOuterJoinWithHashMap();
    }
}
