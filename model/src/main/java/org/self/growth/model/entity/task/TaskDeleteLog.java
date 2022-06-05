package org.self.growth.model.entity.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDeleteLog {

    @Id
    private String id;
    @NotNull
    private String taskId;
    private Long userId;
    @NotNull
    private String name;
    @NotNull
    private String group;
}
