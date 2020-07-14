package Model;

public class ProjectName {
    private String projectName;
    private String projectColor;
    private String id;

    public ProjectName() {
    }

    public ProjectName(String projectName, String projectColor) {
        this.projectName = projectName;
        this.projectColor = projectColor;
    }

    public ProjectName(String projectName, String projectColor, String id) {
        this.projectName = projectName;
        this.projectColor = projectColor;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
