package com.self.growth.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppVersionCheck {

    private boolean isLatest;
    private String downloadUrl;
    private String updateMsg;
}
