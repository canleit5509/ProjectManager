package Model;

public class ProjectName {
    String projectName;
    String projectColor;

    public ProjectName() {
    }

    public ProjectName(String projectName, String projectColor) {
        this.projectName = projectName;
        this.projectColor = projectColor;
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
