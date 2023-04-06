package embrace.DTO;

import embrace.Entities.TaskType;

import java.util.Date;

public record TaskDTO(String name, Date deadline, String description, String taskType, Long employeeId) {
}
