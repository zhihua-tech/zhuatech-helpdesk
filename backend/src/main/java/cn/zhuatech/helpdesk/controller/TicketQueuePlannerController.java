/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.controller;
import cn.zhuatech.helpdesk.common.ApiResponse;import cn.zhuatech.helpdesk.service.TicketQueuePlannerService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/advanced/helpdesk") public class TicketQueuePlannerController{private final TicketQueuePlannerService service;/**
                                                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                     */
public TicketQueuePlannerController(TicketQueuePlannerService service){this.service=service;}/**
                                                                                                                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                  */
@PostMapping("/queue-plan") public ApiResponse<TicketQueuePlannerService.PlanResult> plan(@Valid @RequestBody TicketQueuePlannerService.PlanRequest request){return ApiResponse.ok(service.plan(request));}}
