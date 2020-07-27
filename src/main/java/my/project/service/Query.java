package my.project.service;

import my.project.entity.Result;
import my.project.entity.Row;

import java.util.*;

public class Query {

    public static List<Result> rightOuterJoinWithArrayList(ArrayList<Row> leftTable, ArrayList<Row> rightTable)
    {
        List<Result> results = new ArrayList<>();

        for (Row right : rightTable)
        {
            boolean matchesExist = false;

            for (Row left : leftTable)
            {
                if (right.getId().equals(left.getId()))
                {
                    results.add(new Result(right.getId(), left.getValue(), right.getValue()));
                    matchesExist = true;
                }
            }

            if (!matchesExist)
                results.add(new Result(right.getId(), "NULL", right.getValue()));
        }

        return results;
    }

    public static List<Result> rightOuterJoinWithHashMap(HashMap<Long, List<String>> leftTable, HashMap<Long, List<String>> rightTable)
    {
        List<Result> results = new ArrayList<>();

        for (Map.Entry<Long, List<String>> rightRow : rightTable.entrySet())
        {
            List<String> leftVals = leftTable.getOrDefault(rightRow.getKey(), List.of("NULL"));

            for (String rightVal : rightRow.getValue())
                for (String leftVal : leftVals)
                    results.add(new Result(rightRow.getKey(), leftVal, rightVal));
        }

        return results;
    }

    public static List<Result> rightOuterJoinWithSortedLinkedList(LinkedList<Row> leftTable, LinkedList<Row> rightTable)
    {
        List<Result> results = new ArrayList<>();

        ListIterator<Row> leftIter = leftTable.listIterator();
        Row leftRow = null;
        Row rightRowPrev = null;
        int matches = 0;

        for (Row rightRow : rightTable)
        {
            if (matches > 0 && rightRow.getId().equals(rightRowPrev.getId()))
            {
                for (int i = matches; i >= 0; i--)
                    if (leftIter.hasPrevious()) leftRow = leftIter.previous();
                leftIter.next();
            }

            matches = 0;
            rightRowPrev = rightRow;

            boolean prevResChecked = false;
            do {
                if ((leftRow == null || prevResChecked) && leftIter.hasNext())
                    leftRow = leftIter.next();

                if (leftRow != null && rightRow.getId().equals(leftRow.getId()))
                {
                    results.add(new Result(rightRow.getId(), leftRow.getValue(), rightRow.getValue()));
                    matches++;
                }

                prevResChecked = true;
            } while (leftIter.hasNext() && rightRow.getId().compareTo(leftRow.getId()) >= 0);

            if (matches == 0)
                results.add(new Result(rightRow.getId(), "NULL", rightRow.getValue()));
        }

        return results;
    }
}
