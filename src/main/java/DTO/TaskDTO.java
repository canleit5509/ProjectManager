package DTO;

public class TaskDTO {
    private String id;
    private String prName;
    private String title;
    private String name;
    private String startDate;
    private String deadline;
    private String finishDate;
    private int expectedTime;
    private int finishTime;
    private int processed;

    public TaskDTO() {
    }

    public TaskDTO(String id, String prName, String title, String name, String startDate, String deadline, String finishDate, int expectedTime, int finishTime, int processed) {
        this.id = id;
        this.prName = prName;
        this.title = title;
        this.name = name;
        this.startDate = startDate;
        this.deadline = deadline;
        this.finishDate = finishDate;
        this.expectedTime = expectedTime;
        this.finishTime = finishTime;
        this.processed = processed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public int getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(int expectedTime) {
        this.expectedTime = expectedTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }
}
