package DTO;

public class ProjectNameDTO {
    private String projectName;
    private String projectColor;
    private int done;

    public ProjectNameDTO(String projectName, String projectColor, int done) {
        this.projectName = projectName;
        this.projectColor = projectColor;
        this.done = done;
    }

    public ProjectNameDTO() {

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

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }
}
