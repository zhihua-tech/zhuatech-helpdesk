/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.controller;
import cn.zhuatech.helpdesk.common.ApiResponse;import cn.zhuatech.helpdesk.service.TicketQueuePlannerService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/advanced/helpdesk") public class TicketQueuePlannerController{private final TicketQueuePlannerService service;public TicketQueuePlannerController(TicketQueuePlannerService service){this.service=service;}@PostMapping("/queue-plan") public ApiResponse<TicketQueuePlannerService.PlanResult> plan(@Valid @RequestBody TicketQueuePlannerService.PlanRequest request){return ApiResponse.ok(service.plan(request));}}
