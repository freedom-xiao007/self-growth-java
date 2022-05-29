package org.self.growth.model.entity.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.self.growth.model.enu.LabelEnum;
import org.self.growth.model.enu.TaskCycleEnum;
import org.self.growth.model.enu.TaskLearnTypeEnum;
import org.self.growth.model.enu.TaskTypeEnum;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskConfig {

    @Id
    private String id;
    private Long userId;
    private String name;
    private String description;
    private LabelEnum label;
    private TaskCycleEnum cycleType;
    private TaskLearnTypeEnum learnType;
    private String group;
    private TaskTypeEnum taskTypeEnum;
    private Boolean isComplete;
    private Date completeDate;
}
