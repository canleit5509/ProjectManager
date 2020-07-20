package Service;

import DAO.ProjectNameDao;
import DTO.ProjectNameDTO;
import Model.ProjectName;

import java.util.ArrayList;

public class ProjectNameService {
    private final ProjectNameDao dao;

    public ProjectNameService() {
        dao = new ProjectNameDao();
    }

    public ProjectName convertToProjectName(ProjectNameDTO dto) {
        ProjectName projectName = new ProjectName();
        projectName.setProjectName(dto.getProjectName());
        projectName.setProjectColor(dto.getProjectColor());
        projectName.setDone(dto.getDone());
        return projectName;
    }

    public ProjectNameDTO convertToDTO(ProjectName projectName) {
        ProjectNameDTO dto = new ProjectNameDTO();
        dto.setProjectName(projectName.getProjectName());
        dto.setProjectColor(projectName.getProjectColor());
        dto.setDone(projectName.getDone());
        return dto;
    }

    public ProjectNameDTO getProjectName(String projectName) {
        ProjectName name = dao.get(projectName);
        return convertToDTO(name);
    }

    public void addProjectName(ProjectNameDTO dto) {
        ProjectName projectName = convertToProjectName(dto);
        dao.add(projectName);
    }

    public ArrayList<ProjectNameDTO> getAllProject() {
        ArrayList<ProjectName> projectNames = dao.getAll();
        ArrayList<ProjectNameDTO> nameDTOS = new ArrayList<>();
        for (ProjectName name :
                projectNames) {
            nameDTOS.add(convertToDTO(name));
        }
        return nameDTOS;
    }

    public void updateProject(ProjectNameDTO dto) {
        ProjectName projectName = convertToProjectName(dto);
        dao.update(projectName, projectName.getProjectName());
    }

    public void deleteProject(ProjectNameDTO dto) {
        ProjectName projectName = convertToProjectName(dto);
        dao.delete(projectName);
    }

    public ArrayList<ProjectNameDTO> getAllDoneProject(int check) {
        ArrayList<ProjectName> projectNames = dao.getAllDone(check);
        ArrayList<ProjectNameDTO> nameDTOS = new ArrayList<>();
        for (ProjectName name :
                projectNames) {
            nameDTOS.add(convertToDTO(name));
        }
        return nameDTOS;
    }

    public ArrayList<String> getAllProjectName() {
        return dao.getAllName();
    }

    public ArrayList<String> getAllDoingProjectName() {
        return dao.getAllProjectNameDoing();
    }
}
