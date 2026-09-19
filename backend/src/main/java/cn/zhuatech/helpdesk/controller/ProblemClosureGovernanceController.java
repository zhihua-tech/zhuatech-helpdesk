/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.controller;

import cn.zhuatech.helpdesk.common.ApiResponse;
import cn.zhuatech.helpdesk.service.ProblemClosureGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/helpdesk")
public class ProblemClosureGovernanceController {
    private final ProblemClosureGovernanceService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ProblemClosureGovernanceController(ProblemClosureGovernanceService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/problem-closure")
    public ApiResponse<ProblemClosureGovernanceService.Assessment> assess(
            @Valid @RequestBody ProblemClosureGovernanceService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
