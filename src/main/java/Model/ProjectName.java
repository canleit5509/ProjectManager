package Model;

public class ProjectName {
    private String projectName;
    private String projectColor;
    private int done;


    public ProjectName() {
    }

    public ProjectName(String projectName, String projectColor, int done) {
        this.projectName = projectName;
        this.projectColor = projectColor;
        this.done = done;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectColor() {
        return projectColor;
    }

    public void setProjectColor(String projectColor) {
        this.projectColor = projectColor;
    }
}
