package Model;

public class Task {
    ProjectName prName;
    String title;
    Person nguoiPhuTrach;
    String startDate;
    String expectedDate;
    String finishDate;
    int process;

    public Task() {
    }

    public Task(ProjectName prName, String title, Person nguoiPhuTrach, String startDate, String expectedDate, String finishDate, int process) {
        this.prName = prName;
        this.title = title;
        this.nguoiPhuTrach = nguoiPhuTrach;
        this.startDate = startDate;
        this.expectedDate = expectedDate;
        this.finishDate = finishDate;
        this.process = process;
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

    public Person getNguoiPhuTrach() {
        return nguoiPhuTrach;
    }

    public void setNguoiPhuTrach(Person nguoiPhuTrach) {
        this.nguoiPhuTrach = nguoiPhuTrach;
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
