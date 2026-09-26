# SLA 违约升级治理

`POST /api/enterprise/helpdesk/sla-escalation` 根据有效耗时和服务影响自动决定工单升级级别。

- 只有具备有效理由的暂停时长才从SLA时钟中扣除。
- 计算有效耗时、剩余分钟和是否违约，在预警窗口内主动升级。
- P1大面积影响或重大事件信号自动进入指挥体系。
- 生成重指活、客户通报、恢复计划和审计证据任务。

返回 `ON_TRACK / ESCALATE / MAJOR_INCIDENT / BLOCKED`。
