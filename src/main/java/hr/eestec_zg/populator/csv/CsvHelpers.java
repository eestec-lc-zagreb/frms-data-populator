package hr.eestec_zg.populator.csv;

import hr.eestec_zg.frmscore.domain.models.Company;
import hr.eestec_zg.frmscore.domain.models.Event;
import hr.eestec_zg.frmscore.domain.models.User;
import java.io.File;
import java.io.IOException;

public class CsvHelpers {

  public static String[] companyToStringArray(Company company){
    return new String[]{
        company.getName(),
        company.getShortName(),
        company.getWebAddress(),
        company.getAddress(),
        company.getNotes(),
        company.getType().toString()
    };
  }

  public static String[] eventToStringArray(Event event) {
    return new String[]{
        event.getName(),
        event.getShortName(),
        event.getYear()
    };
  }

  public static String[] userToStringArray(User user) {
    return new String[]{
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getPassword(),
        user.getPhoneNumber(),
        user.getRole().toString(),
        user.getNotes()
    };
  }

  public static void createFileIfFileDoesNotExist(String path) throws IOException {
    File file = new File(path);
    if (!file.exists()) {
      file.createNewFile();
    }
  }

}
