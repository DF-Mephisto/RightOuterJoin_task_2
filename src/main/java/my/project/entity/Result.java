package my.project.entity;

import java.util.Comparator;

public class Result implements Comparable<Result>{

    private Long id;
    private String leftValue;
    private String rightValue;

    public Result(Long id, String leftValue, String rightValue) {
        this.id = id;
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    @Override
    public int compareTo(Result result)
    {
        return Comparator.comparing(Result::getId)
                .thenComparing(Result::getLeftValue)
                .thenComparing(Result::getRightValue)
                .compare(this, result);
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", leftValue='" + leftValue + '\'' +
                ", rightValue='" + rightValue + '\'' +
                '}';
    }
}
