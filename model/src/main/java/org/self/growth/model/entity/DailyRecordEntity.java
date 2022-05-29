package org.self.growth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.self.growth.model.enu.LabelEnum;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyRecordEntity {

    @Id
    private String id;
    private Long userId;

    private Long learnTime;
    private Long learnAverage;
    private Long runningTime;
    private Long runningAverage;
    private Long sleepTime;
    private Long sleepAverage;
    private Long taskComplete;
    private Integer blogs;
    private Integer books;
    private String startDate;
    private String endDate;

    private List<DailyLogModel> dailyLogs;

    @Builder.Default
    private Map<String, Long> appTimes = new HashMap<>();

    @Builder.Default
    private Map<Integer, Integer> learnHourSpeed = new HashMap<>(24);
    @Builder.Default
    private Map<Integer, Integer> learnHoursCount = new HashMap<>(24);

    @Builder.Default
    private Map<Integer, Integer> runningHourSpeed = new HashMap<>(24);
    @Builder.Default
    private Map<Integer, Integer> runningHoursCount = new HashMap<>(24);

    @Builder.Default
    private Map<Integer, Integer> sleepHourSpeed = new HashMap<>(24);
    @Builder.Default
    private Map<Integer, Integer> sleepHoursCount = new HashMap<>(24);

    @Builder.Default
    private Map<LabelEnum, Integer> taskLabelStatistics = new HashMap<>();

    @Builder.Default
    private List<String> readBooks = new ArrayList<>();

    @Builder.Default
    private List<String> writeBlogs = new ArrayList<>();

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class DailyLogModel {
        private String message;
        private String date;
        private DailyLogType type;
        private LabelEnum label;

        public enum DailyLogType {
            ACTIVITY_BEGIN,
            ACTIVITY_END,
            TASK_COMPLETE,
        }
    }
}
