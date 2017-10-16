package hr.eestec_zg.populator.tocsv;

import static hr.eestec_zg.populator.tocsv.CsvPrepare.companyToStringArray;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import hr.eestec_zg.frmscore.domain.models.Company;
import hr.eestec_zg.frmscore.services.CompanyService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompanyPopulator {

  private static String companiesCSV = "companies.csv";

  public CompanyPopulator() throws IOException {
    createFileIfFileDoesNotExists(companiesCSV);
  }

  //TODO
  public void readFromCSV(CompanyService companyService) throws FileNotFoundException {
    CSVReader reader = new CSVReader(new FileReader(companiesCSV), ';');
    List<String[]> data = new ArrayList<>();
    List<Company> companyList = new ArrayList<>();

  }

  public void writeToCSV(CompanyService companyService) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(companiesCSV), ';');
    List<String[]> data = new ArrayList<>();
    //add header
    data.add(new String[]{"name", "shortName", "webAddress", "address", "notes", "type"});
    for (Company company : companyService.getCompanies()) {
      data.add(companyToStringArray(company));
    }

    writer.writeAll(data);
    writer.close();
  }

  private void createFileIfFileDoesNotExists(String url) throws IOException {
    File file = new File(url);
    if (!file.exists()) {
      file.createNewFile();
    }
  }
}
