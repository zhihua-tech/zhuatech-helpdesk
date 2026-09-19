/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk;
import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@SpringBootTest @AutoConfigureMockMvc class TicketQueuePlannerApiTests{@Autowired MockMvc mvc;static final String BODY="""
 {"planAt":"2026-09-20T09:00:00","agents":[{"agentId":"A-1","team":"应用支持","skills":["SAP"],"capacity":3,"openTickets":1,"available":true},{"agentId":"A-2","team":"基础设施","skills":["NETWORK"],"capacity":1,"openTickets":1,"available":true}],"tickets":[{"ticketNo":"HD-1","priority":"P1","requiredSkill":"SAP","slaDueAt":"2026-09-20T09:45:00","estimatedMinutes":30},{"ticketNo":"HD-2","priority":"P2","requiredSkill":"DATABASE","slaDueAt":"2026-09-20T15:00:00","estimatedMinutes":90}]}
 """;
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void assignsBySkillCapacityAndSla()throws Exception{mvc.perform(post("/api/advanced/helpdesk/queue-plan").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content(BODY)).andExpect(status().isOk()).andExpect(jsonPath("$.data.assignedCount").value(1)).andExpect(jsonPath("$.data.unassignedCount").value(1)).andExpect(jsonPath("$.data.assignments[0].agentId").value("A-1")).andExpect(jsonPath("$.data.assignments[0].slaStatus").value("IMMINENT"));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void requiresAuthentication()throws Exception{mvc.perform(post("/api/advanced/helpdesk/queue-plan").contentType(MediaType.APPLICATION_JSON).content(BODY)).andExpect(status().isUnauthorized());}}
