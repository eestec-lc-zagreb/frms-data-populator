package hr.eestec_zg.populator.csv.event;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class EventCsv {

  public EventCsv() {
  }

  public EventCsv(String name, String shortName, String year) {
    this.name = name;
    this.shortName = shortName;
    this.year = year;
  }

  @CsvBindByName(column = "name")
  @CsvBindByPosition(position = 0)
  String name;
  @CsvBindByName(column = "shortName")
  @CsvBindByPosition(position = 1)
  String shortName;
  @CsvBindByName(column = "year")
  @CsvBindByPosition(position = 2)
  String year;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }
}
