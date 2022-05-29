package org.self.growth.model.enu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TaskTypeEnum {

    DEFAULT("无"),
    CODE("代码"),
    NOTE("博客笔记"),
    BOOK("书籍"),
    DAILY("日常"),
    ;

    private final String name;

    TaskTypeEnum(final String name) {
        this.name = name;
    }

    public static TaskTypeEnum fromString(final String name) {
        for (TaskTypeEnum e: TaskTypeEnum.values()) {
            if (e.name.equalsIgnoreCase(name)) {
                return e;
            }
        }
        return DEFAULT;
    }

    public static List<String> names() {
        return Arrays.stream(TaskTypeEnum.values()).map(e -> e.name).collect(Collectors.toList());
    }
}
