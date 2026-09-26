/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
class SlaBreachEscalationGovernanceServiceTest {
    private final SlaBreachEscalationGovernanceService service = new SlaBreachEscalationGovernanceService();

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    @Test
    void keepsPreparedTicketOnTrack() {
        var result = service.assess(request(30, 120, 0, false, 5, true));
        assertThat(result.decision()).isEqualTo(SlaBreachEscalationGovernanceService.Decision.ON_TRACK);
        assertThat(result.remainingMinutes()).isEqualTo(90);
    }

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    @Test
    void escalatesBreachedTicketAndBuildsRecoveryActions() {
        var result = service.assess(request(150, 120, 0, false, 5, false));
        assertThat(result.decision()).isEqualTo(SlaBreachEscalationGovernanceService.Decision.ESCALATE);
        assertThat(result.breached()).isTrue();
        assertThat(result.actions()).hasSize(3);
    }

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    @Test
    void declaresMajorIncidentForLargeP1Impact() {
        var result = service.assess(request(40, 120, 0, true, 500, false));
        assertThat(result.decision()).isEqualTo(SlaBreachEscalationGovernanceService.Decision.MAJOR_INCIDENT);
        assertThat(result.escalationRoute()).contains("事件指挥官");
    }

    private SlaBreachEscalationGovernanceService.Request request(int elapsed, int target,
                                                                  int paused, boolean major,
                                                                  int impacted, boolean prepared) {
        return new SlaBreachEscalationGovernanceService.Request("TKT-100",
                SlaBreachEscalationGovernanceService.Priority.P1, elapsed, target, paused,
                true, 15, impacted, true, true, major, prepared, prepared, prepared, prepared);
    }
}
