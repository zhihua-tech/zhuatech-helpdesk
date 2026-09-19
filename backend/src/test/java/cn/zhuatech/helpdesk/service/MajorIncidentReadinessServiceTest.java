/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class MajorIncidentReadinessServiceTest {
    private final MajorIncidentReadinessService service = new MajorIncidentReadinessService();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void marksControlledP1AsReady() {
        var result = service.evaluate(new MajorIncidentReadinessService.Request(
                "INC-001", 1, 3, true, true, true, true));
        assertEquals("READY", result.decision());
        assertEquals(100, result.readinessScore());
        assertEquals(15, result.nextReviewMinutes());
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksMajorIncidentWithoutCommandAndRecoveryControls() {
        var result = service.evaluate(new MajorIncidentReadinessService.Request(
                "INC-002", 2, 0, false, false, false, false));
        assertEquals("BLOCKED", result.decision());
        assertEquals(5, result.gaps().size());
    }
}
