package com.self.growth.task.entity;

import com.self.growth.task.enums.LabelEnum;
import com.self.growth.task.enums.TaskCycleEnum;
import com.self.growth.task.enums.TaskLearnTypeEnum;
import com.self.growth.task.enums.TaskTypeEnum;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class TaskConfig {

    @Id
    private String id;
    private String name;
    private String description;
    private LabelEnum label;
    private TaskCycleEnum cycleType;
    private TaskLearnTypeEnum learnType;
    private String group;
    private TaskTypeEnum taskTypeEnum;
    private Boolean isComplete;
    private Date completeDate;

    public TaskConfig() {

    }

    public TaskConfig(String id, String name, String description, LabelEnum label, TaskCycleEnum cycleType,
                      TaskLearnTypeEnum learnType, String group, TaskTypeEnum taskTypeEnum, Boolean isComplete,
                      Date completeDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.label = label;
        this.cycleType = cycleType;
        this.learnType = learnType;
        this.group = group;
        this.taskTypeEnum = taskTypeEnum;
        this.isComplete = isComplete;
        this.completeDate = completeDate;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LabelEnum getLabel() {
        return label;
    }

    public TaskCycleEnum getCycleType() {
        return cycleType;
    }

    public TaskLearnTypeEnum getLearnType() {
        return learnType;
    }

    public TaskTypeEnum getTaskTypeEnum() {
        return taskTypeEnum;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public Date getCompleteDate() {
        return completeDate;
    }
}
