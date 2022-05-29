package org.self.growth.model.enu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TaskCycleEnum {

    DEFAULT("无"),
    DAILY("每天"),
    WEEK("每周"),
    MONTH("每月"),
    YEAR("每年"),
    ;

    private final String name;

    TaskCycleEnum(final String name) {
        this.name = name;
    }

    public static TaskCycleEnum fromString(final String name) {
        for (TaskCycleEnum e: TaskCycleEnum.values()) {
            if (e.name.equalsIgnoreCase(name)) {
                return e;
            }
        }
        return DEFAULT;
    }

    public static List<String> names() {
        return Arrays.stream(TaskCycleEnum.values()).map(e -> e.name).collect(Collectors.toList());
    }
}
