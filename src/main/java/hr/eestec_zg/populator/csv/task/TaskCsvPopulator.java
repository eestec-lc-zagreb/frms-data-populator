package hr.eestec_zg.populator.csv.task;

import static hr.eestec_zg.populator.csv.CsvHelpers.createFileIfFileDoesNotExist;
import static hr.eestec_zg.populator.csv.CsvHelpers.taskToStringArray;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import hr.eestec_zg.frmscore.domain.models.Company;
import hr.eestec_zg.frmscore.domain.models.Task;
import hr.eestec_zg.frmscore.services.CompanyService;
import hr.eestec_zg.frmscore.services.TaskService;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskCsvPopulator {

  private static String tasksCSV = "tasks.csv";

  public TaskCsvPopulator() throws IOException {
    createFileIfFileDoesNotExist(tasksCSV);
  }

  public void readFromCsv(TaskService taskService) throws FileNotFoundException {
    List<TaskCsv> eventList =
        new CsvToBeanBuilder<TaskCsv>(new FileReader(tasksCSV))
            .withType(TaskCsv.class)
            .withOrderedResults(true)
            .build()
            .parse();

    //TODO: Task cannot be converted to TaskDto
//    eventList.stream()
//        .map(t -> new Task(
//            t.getEvent(),
//            t.getCompany(),
//            t.getAssignee(),
//            t.getType(),
//            t.getCallTime(),
//            t.getMailTime(),
//            t.getFollowUpTime(),
//            t.getStatus(),
//            t.getNotes()
//        )).forEach(taskService::createTask);
  }

  public void writeToCsv(TaskService taskService, CompanyService companyService) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(tasksCSV), ',');
    List<String[]> data = new ArrayList<>();
    for (Company company : companyService.getCompanies())
    for (Task task : taskService.getTasksByCompany(company.getId())) {
      data.add(taskToStringArray(task));
    }

    writer.writeAll(data);
    writer.close();
  }


}
