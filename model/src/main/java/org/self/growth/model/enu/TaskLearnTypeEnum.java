package org.self.growth.model.enu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TaskLearnTypeEnum {

    DEFAULT("无"),
    INPUT("输入"),
    OUTPUT("输出"),
    ;

    private final String name;

    TaskLearnTypeEnum(final String name) {
        this.name = name;
    }

    public static TaskLearnTypeEnum fromString(final String name) {
        for (TaskLearnTypeEnum e: TaskLearnTypeEnum.values()) {
            if (e.name.equalsIgnoreCase(name)) {
                return e;
            }
        }
        return DEFAULT;
    }

    public static List<String> names() {
        return Arrays.stream(TaskLearnTypeEnum.values()).map(e -> e.name).collect(Collectors.toList());
    }
}
