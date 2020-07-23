package my.project.entity;

import java.util.Comparator;

public class Row implements Comparable<Row> {

    private Long id;
    private String value;

    public Row(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Row row)
    {
        return Comparator.comparing(Row::getId)
                .thenComparing(Row::getValue)
                .compare(this, row);
    }

    @Override
    public String toString() {
        return "Row{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
