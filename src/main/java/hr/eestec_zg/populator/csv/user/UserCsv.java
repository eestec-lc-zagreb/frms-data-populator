package hr.eestec_zg.populator.csv.user;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import hr.eestec_zg.frmscore.domain.models.Role;

public class UserCsv {


  public UserCsv(){
  }

  public UserCsv(String firstName, String lastName, String email, String password,
      String phoneNumber, String role, String notes) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.role = role;
    this.notes = notes;
  }

  @CsvBindByName(column = "name")
  @CsvBindByPosition(position = 0)
  String firstName;
  @CsvBindByName(column = "lastName")
  @CsvBindByPosition(position = 1)
  String lastName;
  @CsvBindByName(column = "email")
  @CsvBindByPosition(position = 2)
  String email;
  @CsvBindByName(column = "password")
  @CsvBindByPosition(position = 3)
  String password;
  @CsvBindByName(column = "phoneNumber")
  @CsvBindByPosition(position = 4)
  String phoneNumber;
  @CsvBindByName(column = "role")
  @CsvBindByPosition(position = 5)
  String role;
  @CsvBindByName(column = "notes")
  @CsvBindByPosition(position = 6)
  String notes;



  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
