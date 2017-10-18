package hr.eestec_zg.populator.csv.task;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import hr.eestec_zg.frmscore.domain.models.Company;
import hr.eestec_zg.frmscore.domain.models.Event;
import hr.eestec_zg.frmscore.domain.models.SponsorshipType;
import hr.eestec_zg.frmscore.domain.models.TaskStatus;
import hr.eestec_zg.frmscore.domain.models.User;
import java.time.ZonedDateTime;

public class TaskCsv {

  public TaskCsv(){
  }

  public TaskCsv(Event event, Company company, User assignee,
      SponsorshipType type, ZonedDateTime callTime, ZonedDateTime mailTime,
      ZonedDateTime followUpTime, TaskStatus status, String notes) {
    this.event = event;
    this.company = company;
    this.assignee = assignee;
    this.type = type;
    this.callTime = callTime;
    this.mailTime = mailTime;
    this.followUpTime = followUpTime;
    this.status = status;
    this.notes = notes;
  }

  @CsvBindByName(column = "event")
  @CsvBindByPosition(position = 0)
  private Event event;
  @CsvBindByName(column = "company")
  @CsvBindByPosition(position = 1)
  private Company company;
  @CsvBindByName(column = "assignee")
  @CsvBindByPosition(position = 2)
  private User assignee;
  @CsvBindByName(column = "type")
  @CsvBindByPosition(position = 3)
  private SponsorshipType type;
  @CsvBindByName(column = "callTime")
  @CsvBindByPosition(position = 4)
  private ZonedDateTime callTime;
  @CsvBindByName(column = "mailTime")
  @CsvBindByPosition(position = 5)
  private ZonedDateTime mailTime;
  @CsvBindByName(column = "followUpTime")
  @CsvBindByPosition(position = 6)
  private ZonedDateTime followUpTime;
  @CsvBindByName(column = "status")
  @CsvBindByPosition(position = 7)
  private TaskStatus status;
  @CsvBindByName(column = "notes")
  @CsvBindByPosition(position = 8)
  private String notes;

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public User getAssignee() {
    return assignee;
  }

  public void setAssignee(User assignee) {
    this.assignee = assignee;
  }

  public SponsorshipType getType() {
    return type;
  }

  public void setType(SponsorshipType type) {
    this.type = type;
  }

  public ZonedDateTime getCallTime() {
    return callTime;
  }

  public void setCallTime(ZonedDateTime callTime) {
    this.callTime = callTime;
  }

  public ZonedDateTime getMailTime() {
    return mailTime;
  }

  public void setMailTime(ZonedDateTime mailTime) {
    this.mailTime = mailTime;
  }

  public ZonedDateTime getFollowUpTime() {
    return followUpTime;
  }

  public void setFollowUpTime(ZonedDateTime followUpTime) {
    this.followUpTime = followUpTime;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
