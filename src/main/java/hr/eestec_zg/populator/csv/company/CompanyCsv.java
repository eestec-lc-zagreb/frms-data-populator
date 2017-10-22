package hr.eestec_zg.populator.csv.company;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class CompanyCsv {

  public CompanyCsv(){
  }

  public CompanyCsv(String name, String shortName, String webAddress, String address, String notes, String type) {
    this.name = name;
    this.shortName = shortName;
    this.webAddress = webAddress;
    this.address = address;
    this.notes = notes;
    this.type = type;
  }

  @CsvBindByName(column = "name")
  @CsvBindByPosition(position = 0)
  private
  String name;
  @CsvBindByName(column = "shortName")
  @CsvBindByPosition(position = 1)
  private
  String shortName;
  @CsvBindByName(column = "webAddress")
  @CsvBindByPosition(position = 2)
  private
  String webAddress;
  @CsvBindByName(column = "address")
  @CsvBindByPosition(position = 3)
  private
  String address;
  @CsvBindByName(column = "notes")
  @CsvBindByPosition(position = 4)
  private
  String notes;
  @CsvBindByName(column = "type")
  @CsvBindByPosition(position = 5)
  private
  String type;


  String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  String getWebAddress() {
    return webAddress;
  }

  public void setWebAddress(String webAddress) {
    this.webAddress = webAddress;
  }

  String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
