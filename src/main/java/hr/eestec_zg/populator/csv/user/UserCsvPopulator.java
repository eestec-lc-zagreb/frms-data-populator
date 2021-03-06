package hr.eestec_zg.populator.csv.user;


import static hr.eestec_zg.populator.Populator.csvName;
import static hr.eestec_zg.populator.csv.CsvHelpers.createFileIfFileDoesNotExist;
import static hr.eestec_zg.populator.csv.CsvHelpers.userToStringArray;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import hr.eestec_zg.frmscore.domain.models.Role;
import hr.eestec_zg.frmscore.domain.models.User;
import hr.eestec_zg.frmscore.services.UserService;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCsvPopulator {


  public UserCsvPopulator() throws IOException {
    createFileIfFileDoesNotExist(csvName);
  }

  public void readFromCsv(UserService userService) throws FileNotFoundException {
    List<UserCsv> userList =
        new CsvToBeanBuilder<UserCsv>(new FileReader(csvName))
            .withType(UserCsv.class)
            .withOrderedResults(true)
            .build()
            .parse();

    userList.stream()
        .map(u -> new User(
            u.getFirstName(),
            u.getLastName(),
            u.getEmail(),
            u.getPassword(),
            u.getPhoneNumber(),
            Role.valueOf(u.getRole()),
            u.getNotes()
        )).forEach(userService::createUser);
  }

  public void writeToCsv(UserService userService) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(csvName), ',');
    List<String[]> data = new ArrayList<>();
    for (User user : userService.getAllUsers()) {
      data.add(userToStringArray(user));
    }

    writer.writeAll(data);
    writer.close();
  }
}