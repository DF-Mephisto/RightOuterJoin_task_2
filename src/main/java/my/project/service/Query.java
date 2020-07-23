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
            List<String> leftVals = leftTable.get(rightRow.getKey());

            if (leftVals == null)
            {
                for (String rightVal : rightRow.getValue())
                    results.add(new Result(rightRow.getKey(), "NULL", rightVal));
            }
            else
            {
                for (String rightVal : rightRow.getValue())
                    for (String leftVal : leftVals)
                        results.add(new Result(rightRow.getKey(), leftVal, rightVal));
            }
        }

        return results;
    }
}
