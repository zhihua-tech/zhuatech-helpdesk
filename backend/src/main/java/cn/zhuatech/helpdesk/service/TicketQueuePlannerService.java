/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.helpdesk.service;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;
@Service public class TicketQueuePlannerService {
 public PlanResult plan(@Valid PlanRequest request){
  Map<String,Integer> load=new LinkedHashMap<>();Set<String> ids=new HashSet<>();
  for(Agent agent:request.agents()){if(!ids.add(agent.agentId()))throw new IllegalArgumentException("坐席编号不能重复: "+agent.agentId());load.put(agent.agentId(),agent.openTickets());}
  List<Ticket> tickets=request.tickets().stream().sorted(Comparator.comparingInt((Ticket t)->priority(t.priority())).reversed().thenComparing(Ticket::slaDueAt)).toList();
  List<Assignment> assignments=new ArrayList<>();List<Unassigned> unassigned=new ArrayList<>();
  for(Ticket ticket:tickets){Agent best=null;int bestScore=Integer.MIN_VALUE;
   for(Agent agent:request.agents()){int current=load.get(agent.agentId());if(!agent.available()||current>=agent.capacity()||!agent.skills().contains(ticket.requiredSkill()))continue;
    long minutes=Duration.between(request.planAt(),ticket.slaDueAt()).toMinutes();int score=100-current*12+(minutes<60?25:minutes<240?10:0);
    if(best==null||score>bestScore||score==bestScore&&agent.agentId().compareTo(best.agentId())<0){best=agent;bestScore=score;}}
   if(best==null){unassigned.add(new Unassigned(ticket.ticketNo(),"无具备技能且有剩余容量的可用坐席"));continue;}
   load.compute(best.agentId(),(key,value)->value+1);long remaining=Math.max(0,Duration.between(request.planAt(),ticket.slaDueAt()).toMinutes());
   assignments.add(new Assignment(ticket.ticketNo(),best.agentId(),best.team(),bestScore,remaining,remaining<60?"IMMINENT":"ON_TRACK"));
  }
  return new PlanResult(assignments,unassigned,load,assignments.size(),unassigned.size(),assignments.stream().filter(a->"IMMINENT".equals(a.slaStatus())).count());
 }
 private int priority(String value){return switch(value){case "P1"->4;case "P2"->3;case "P3"->2;default->1;};}
 public record PlanRequest(@NotNull LocalDateTime planAt,@NotEmpty List<@Valid Agent> agents,@NotEmpty List<@Valid Ticket> tickets){}
 public record Agent(@NotBlank String agentId,@NotBlank String team,@NotEmpty Set<@NotBlank String> skills,@Min(1) int capacity,@Min(0) int openTickets,boolean available){}
 public record Ticket(@NotBlank String ticketNo,@Pattern(regexp="P[1-4]") String priority,@NotBlank String requiredSkill,@NotNull LocalDateTime slaDueAt,@Min(1) int estimatedMinutes){}
 public record Assignment(String ticketNo,String agentId,String team,int routingScore,long slaRemainingMinutes,String slaStatus){}
 public record Unassigned(String ticketNo,String reason){}
 public record PlanResult(List<Assignment> assignments,List<Unassigned> unassigned,Map<String,Integer> resultingLoad,int assignedCount,int unassignedCount,long imminentCount){}
}
