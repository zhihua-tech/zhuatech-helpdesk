/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ProblemClosureGovernanceServiceTest {
    private final ProblemClosureGovernanceService service = new ProblemClosureGovernanceService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void closesVerifiedProblem() {
        var result = service.assess(request(true, true, true));
        assertEquals(ProblemClosureGovernanceService.Decision.CLOSE, result.decision());
        assertTrue(result.blockers().isEmpty());
        assertTrue(result.actions().isEmpty());
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void reviewsProblemWithKnowledgeActions() {
        var result = service.assess(request(false, false, false));
        assertEquals(ProblemClosureGovernanceService.Decision.REVIEW, result.decision());
        assertEquals(3, result.actions().size());
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUncontrolledProblemClosure() {
        var result = service.assess(new ProblemClosureGovernanceService.Request("PRB-003", 12,
                false, false, true, false, false, false, false, true, false, true, false, false));
        assertEquals(ProblemClosureGovernanceService.Decision.BLOCKED, result.decision());
        assertEquals(9, result.blockers().size());
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private ProblemClosureGovernanceService.Request request(boolean workaround, boolean knowledge, boolean residualRisk) {
        return new ProblemClosureGovernanceService.Request("PRB-001", 8, true, true, workaround, true,
                true, true, true, knowledge, true, residualRisk, true, true);
    }
}
