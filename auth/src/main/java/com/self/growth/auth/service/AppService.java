package com.self.growth.auth.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.self.growth.auth.entity.AppVersionEntity;
import com.self.growth.auth.entity.FeedbackEntity;
import com.self.growth.auth.mapper.AppVersionMapper;
import com.self.growth.auth.mapper.FeedbackMapper;
import com.self.growth.auth.vo.AppVersionCheck;
import com.self.growth.auth.vo.FeedbackVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AppService {

    private final AppVersionMapper appVersionMapper;
    private final FeedbackMapper feedbackMapper;

    public AppVersionCheck versionCheck(int versionCode) {
        final List<AppVersionEntity> versions = appVersionMapper.selectList(Wrappers.<AppVersionEntity>lambdaQuery()
                .orderByDesc(AppVersionEntity::getId));
        if (versions.isEmpty()) {
            return AppVersionCheck.builder()
                    .isLatest(true)
                    .build();
        }

        final AppVersionEntity latestVersion = versions.get(0);
        if (versionCode == latestVersion.getCode()) {
            return AppVersionCheck.builder()
                    .isLatest(true)
                    .build();
        } else {
            return AppVersionCheck.builder()
                    .isLatest(false)
                    .downloadUrl(latestVersion.getUrl())
                    .updateMsg(latestVersion.getMsg())
                    .build();
        }
    }

    public void feedback(FeedbackVo feedback) {
        feedbackMapper.insert(FeedbackEntity.builder()
                .feedback(feedback.getMsg())
                .email(feedback.getEmail())
                .isProcess(false)
                .build());
    }
}
