package Service;

import DAO.TaskDao;
import DTO.TaskDTO;
import Model.Task;

import java.util.ArrayList;

public class TaskService {
    private final TaskDao dao;

    public TaskService() {
        dao = new TaskDao();
    }

    public Task convertToTask(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setPrName(dto.getPrName());
        task.setTitle(dto.getTitle());
        task.setName(dto.getName());
        task.setStartDate(dto.getStartDate());
        task.setDeadline(dto.getDeadline());
        task.setFinishDate(dto.getFinishDate());
        task.setExpectedTime(dto.getExpectedTime());
        task.setFinishTime(dto.getFinishTime());
        task.setProcessed(dto.getProcessed());
        return task;
    }

    public TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setPrName(task.getPrName());
        dto.setTitle(task.getTitle());
        dto.setName(task.getName());
        dto.setStartDate(task.getStartDate());
        dto.setDeadline(task.getDeadline());
        dto.setFinishDate(task.getFinishDate());
        dto.setExpectedTime(task.getExpectedTime());
        dto.setFinishTime(task.getFinishTime());
        dto.setProcessed(task.getProcessed());
        return dto;
    }

    public ArrayList<TaskDTO> getAllTask() {
        ArrayList<Task> tasks = dao.getAll();
        ArrayList<TaskDTO> taskDTOS = new ArrayList<>();
        for (Task task :
                tasks) {
            taskDTOS.add(convertToDTO(task));
        }
        return taskDTOS;
    }

    public TaskDTO getTask(String id) {
        return convertToDTO(dao.get(id));
    }
    public void addTask(TaskDTO dto){
        Task task = convertToTask(dto);
        dao.add(task);
    }
    public void updateTask(TaskDTO dto){
        Task task = convertToTask(dto);
        dao.update(task);
    }
    public void deleteTask(TaskDTO dto){
        dao.delete(convertToTask(dto));
    }
}
