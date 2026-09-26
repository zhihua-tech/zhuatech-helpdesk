/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务台SLA升级根据有效耗时、优先级、影响面和重大事件信号生成处置级别。
 *
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class SlaBreachEscalationGovernanceService {
    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        int excludedPause = request.pauseReasonValid() ? request.pausedMinutes() : 0;
        int effectiveElapsed = Math.max(0, request.elapsedMinutes() - excludedPause);
        int remainingMinutes = request.targetMinutes() - effectiveElapsed;
        boolean breached = remainingMinutes < 0;
        boolean majorIncident = request.majorIncidentSignal()
                || (request.priority() == Priority.P1 && request.impactedUserCount() >= 100);
        if (!request.ticketOpen()) blockers.add("工单已关闭或取消，不得再发起SLA升级");
        if (request.pausedMinutes() > 0 && !request.pauseReasonValid()) actions.add("无效暂停时长不从SLA中扣除，复核暂停理由");
        if (!request.assigneeActive()) actions.add("重新指活到在线处理组并设置代理人");
        if (majorIncident && !request.commanderAssigned()) actions.add("指定重大事件指挥官与技术负责人");
        if ((breached || majorIncident) && !request.customerCommunicationPlanned()) actions.add("建立客户通报节奏和下次更新时间");
        if (breached && !request.recoveryPlanReady()) actions.add("补充恢复计划、资源需求和预计恢复时间");
        if (!request.auditEvidenceAttached()) actions.add("归档SLA时钟、暂停、升级和沟通证据");
        Decision decision;
        if (!blockers.isEmpty()) decision = Decision.BLOCKED;
        else if (majorIncident) decision = Decision.MAJOR_INCIDENT;
        else if (breached || remainingMinutes <= request.warningWindowMinutes()) decision = Decision.ESCALATE;
        else decision = Decision.ON_TRACK;
        String escalationRoute = majorIncident ? "值班组长→事件指挥官→业务负责人→管理层"
                : decision == Decision.ESCALATE ? "处理人→值班组长→服务经理" : "处理人";
        return new Assessment(request.ticketNo(), decision, effectiveElapsed, remainingMinutes,
                breached, escalationRoute, List.copyOf(blockers), List.copyOf(actions));
    }

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public record Request(@NotBlank String ticketNo, @NotNull Priority priority,
                          @Min(0) int elapsedMinutes, @Min(1) int targetMinutes,
                          @Min(0) int pausedMinutes, boolean pauseReasonValid,
                          @Min(0) int warningWindowMinutes, @Min(0) int impactedUserCount,
                          boolean ticketOpen, boolean assigneeActive, boolean majorIncidentSignal,
                          boolean commanderAssigned, boolean customerCommunicationPlanned,
                          boolean recoveryPlanReady, boolean auditEvidenceAttached) {}

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public record Assessment(String ticketNo, Decision decision, int effectiveElapsedMinutes,
                             int remainingMinutes, boolean breached, String escalationRoute,
                             List<String> blockers, List<String> actions) {}

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public enum Priority { P1, P2, P3, P4 }

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public enum Decision { ON_TRACK, ESCALATE, MAJOR_INCIDENT, BLOCKED }
}
