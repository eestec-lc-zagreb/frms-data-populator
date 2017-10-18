package hr.eestec_zg.populator.csv.event;

import static hr.eestec_zg.populator.csv.CsvHelpers.createFileIfFileDoesNotExist;
import static hr.eestec_zg.populator.csv.CsvHelpers.eventToStringArray;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import hr.eestec_zg.frmscore.domain.models.Event;
import hr.eestec_zg.frmscore.services.EventService;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventCsvPopulator {

  private static String eventsCSV = "events.csv";

  public EventCsvPopulator() throws IOException {
    createFileIfFileDoesNotExist(eventsCSV);
  }

  public void readFromCsv(EventService eventService) throws FileNotFoundException {
    List<EventCsv> eventList =
        new CsvToBeanBuilder<EventCsv>(new FileReader(eventsCSV))
            .withType(EventCsv.class)
            .withOrderedResults(true)
            .build()
            .parse();

    eventList.stream()
        .map(e -> new Event(
            e.getName(),
            e.getShortName(),
            e.getYear()
        )).forEach(eventService::createEvent);
  }

  public void writeToCsv(EventService eventService) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(eventsCSV), ',');
    List<String[]> data = new ArrayList<>();
    for (Event event : eventService.getEvents()) {
      data.add(eventToStringArray(event));
    }

    writer.writeAll(data);
    writer.close();
  }
}
