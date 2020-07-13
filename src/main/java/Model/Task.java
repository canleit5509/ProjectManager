package Model;

import java.util.List;

public class Task {
    ProjectName prName;
    String title;
    List<Person> phuTrach;
    String startDate;
    String expectedDate;
    String finishDate;
    int process;

    public Task() {
    }

    public Task(ProjectName prName, String title, List<Person> phuTrach, String startDate, String expectedDate, String finishDate, int process) {
        this.prName = prName;
        this.title = title;
        this.phuTrach = phuTrach;
        this.startDate = startDate;
        this.expectedDate = expectedDate;
        this.finishDate = finishDate;
        this.process = process;
    }

    public List<Person> getPhuTrach() {
        return phuTrach;
    }

    public void setPhuTrach(List<Person> phuTrach) {
        this.phuTrach = phuTrach;
    }

    public ProjectName getPrName() {
        return prName;
    }

    public void setPrName(ProjectName prName) {
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

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
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
}
