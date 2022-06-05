package org.self.growth.model.vo.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.self.growth.model.entity.task.TaskConfig;
import org.self.growth.model.entity.task.TaskDeleteLog;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SyncTaskVo {

    private List<TaskConfig> newTask;
    private List<TaskDeleteLog> deleteLogs;
}
