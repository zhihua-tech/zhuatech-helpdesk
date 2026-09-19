/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.controller;
import cn.zhuatech.helpdesk.common.ApiResponse;
import cn.zhuatech.helpdesk.service.MajorIncidentReadinessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/incidents")
public class MajorIncidentReadinessController {
    private final MajorIncidentReadinessService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public MajorIncidentReadinessController(MajorIncidentReadinessService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/readiness")
    public ApiResponse<MajorIncidentReadinessService.Result> evaluate(
            @Valid @RequestBody MajorIncidentReadinessService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
