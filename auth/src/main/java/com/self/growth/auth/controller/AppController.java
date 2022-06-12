package com.self.growth.auth.controller;

import com.self.growth.auth.service.AppService;
import com.self.growth.auth.vo.AppVersionCheck;
import com.self.growth.auth.vo.FeedbackVo;
import lombok.AllArgsConstructor;
import org.self.growth.model.vo.ResResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/app")
public class AppController {

    private final AppService appService;

    @GetMapping("/versionCheck")
    public ResResult<AppVersionCheck> versionCheck(@RequestParam("version") Integer versionCode) {
        return ResResult.success(appService.versionCheck(versionCode));
    }

    @PostMapping("/feedback")
    public ResResult<Void> feedback(@RequestBody FeedbackVo feedback) {
        appService.feedback(feedback);
        return ResResult.success(null);
    }
}
