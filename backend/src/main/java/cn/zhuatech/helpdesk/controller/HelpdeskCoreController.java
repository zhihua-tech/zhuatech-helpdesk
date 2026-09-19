/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.controller;
import cn.zhuatech.helpdesk.common.ApiResponse;import cn.zhuatech.helpdesk.service.HelpdeskCoreService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/core/helpdesk") public class HelpdeskCoreController{
 private final HelpdeskCoreService service;/**
                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                            */
public HelpdeskCoreController(HelpdeskCoreService service){this.service=service;}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/tickets") ApiResponse<HelpdeskCoreService.Ticket> create(@Valid @RequestBody HelpdeskCoreService.CreateTicketRequest request){return ApiResponse.ok(service.create(request));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @GetMapping("/tickets") ApiResponse<List<HelpdeskCoreService.Ticket>> list(@RequestParam(required=false)String status){return ApiResponse.ok(service.list(status));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @GetMapping("/tickets/{id}") ApiResponse<HelpdeskCoreService.Ticket> detail(@PathVariable Long id){return ApiResponse.ok(service.detail(id));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @GetMapping("/tickets/{id}/timeline") ApiResponse<List<HelpdeskCoreService.TicketEvent>> timeline(@PathVariable Long id){return ApiResponse.ok(service.timeline(id));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/tickets/{id}/assign") ApiResponse<HelpdeskCoreService.Ticket> assign(@PathVariable Long id,@Valid @RequestBody HelpdeskCoreService.AssignRequest r){return ApiResponse.ok(service.assign(id,r));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/tickets/{id}/respond") ApiResponse<HelpdeskCoreService.Ticket> respond(@PathVariable Long id,@Valid @RequestBody HelpdeskCoreService.RemarkRequest r){return ApiResponse.ok(service.respond(id,r));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/tickets/{id}/wait-customer") ApiResponse<HelpdeskCoreService.Ticket> waitCustomer(@PathVariable Long id,@Valid @RequestBody HelpdeskCoreService.RemarkRequest r){return ApiResponse.ok(service.waitCustomer(id,r));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/tickets/{id}/resume") ApiResponse<HelpdeskCoreService.Ticket> resume(@PathVariable Long id,@Valid @RequestBody HelpdeskCoreService.RemarkRequest r){return ApiResponse.ok(service.resume(id,r));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/tickets/{id}/resolve") ApiResponse<HelpdeskCoreService.Ticket> resolve(@PathVariable Long id,@Valid @RequestBody HelpdeskCoreService.ResolveRequest r){return ApiResponse.ok(service.resolve(id,r));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/tickets/{id}/close") ApiResponse<HelpdeskCoreService.Ticket> close(@PathVariable Long id,@Valid @RequestBody HelpdeskCoreService.RemarkRequest r){return ApiResponse.ok(service.close(id,r));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/tickets/{id}/reopen") ApiResponse<HelpdeskCoreService.Ticket> reopen(@PathVariable Long id,@Valid @RequestBody HelpdeskCoreService.RemarkRequest r){return ApiResponse.ok(service.reopen(id,r));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/tickets/{id}/satisfaction") ApiResponse<HelpdeskCoreService.Ticket> satisfaction(@PathVariable Long id,@Valid @RequestBody HelpdeskCoreService.SatisfactionRequest r){return ApiResponse.ok(service.rate(id,r));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @GetMapping("/sla-summary") ApiResponse<HelpdeskCoreService.SlaSummary> summary(){return ApiResponse.ok(service.summary());}
}
