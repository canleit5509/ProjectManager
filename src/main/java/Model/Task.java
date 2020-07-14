package Model;

public class Task {
    private String id;
    private String prName;
    private String title;
    private String charger;
    private String startDate;
    private String deadline;
    private String finishDate;
    private int expectedTime;
    private int finishTime;
    private int process;

    public Task() {
    }

    public Task(String prName, String title, String phuTrach, String startDate, String deadline) {
        this.prName = prName;
        this.title = title;
        this.charger = phuTrach;
        this.startDate = startDate;
        this.deadline = deadline;
    }


    public Task(String prName, String title, String phuTrach, String startDate, String deadline, String finishDate, int process) {
        this.prName = prName;
        this.title = title;
        this.charger = phuTrach;
        this.startDate = startDate;
        this.deadline = deadline;
        this.finishDate = finishDate;
        this.process = process;
    }

    public Task(String prName, String title, String phuTrach, String startDate, String deadline, String finishDate, int expectedTime, int finishTime, int process) {
        this.prName = prName;
        this.title = title;
        this.charger = phuTrach;
        this.startDate = startDate;
        this.deadline = deadline;
        this.finishDate = finishDate;
        this.expectedTime = expectedTime;
        this.finishTime = finishTime;
        this.process = process;
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

    public String getPhuTrach() {
        return charger;
    }

    public void setPhuTrach(String phuTrach) {
        this.charger = phuTrach;
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

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
