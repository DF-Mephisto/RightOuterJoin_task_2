package my.project.service;

import my.project.entity.Row;

import java.util.*;

public class Converter {

    public static HashMap<Long, List<String>> arrayListToHashMap(ArrayList<Row> rows)
    {
        HashMap<Long, List<String>> res = new HashMap<>();

        for (Row row : rows)
        {
            Long id = row.getId();
            List<String> vals = res.get(id);

            if (vals == null)
            {
                vals = new ArrayList<>();
                res.put(id, vals);
            }

            vals.add(row.getValue());
        }

        return res;
    }

}
