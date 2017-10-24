package hr.eestec_zg.populator.csv.company;

import static hr.eestec_zg.populator.Populator.csvName;
import static hr.eestec_zg.populator.csv.CsvHelpers.companyToStringArray;
import static hr.eestec_zg.populator.csv.CsvHelpers.createFileIfFileDoesNotExist;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import hr.eestec_zg.frmscore.domain.models.Company;
import hr.eestec_zg.frmscore.domain.models.CompanyType;
import hr.eestec_zg.frmscore.services.CompanyService;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompanyCsvPopulator {


  public CompanyCsvPopulator() throws IOException {
    createFileIfFileDoesNotExist(csvName);
  }

  public void readFromCSV(CompanyService companyService) throws IOException {
    List<CompanyCsv> companyList;
    FileReader file = new FileReader(csvName);
    CsvToBeanBuilder<CompanyCsv> csvToBeanBuilder = new CsvToBeanBuilder<>(file);
    CsvToBean<CompanyCsv> csvToBean = csvToBeanBuilder
        .withType(CompanyCsv.class)
        .withOrderedResults(true)
        .build();
    companyList = csvToBean.parse();
    file.close();

    System.out.println(companyList);

    companyList.stream()
        .map(c -> new Company(
            c.getName(),
            c.getShortName(),
            c.getWebAddress(),
            c.getAddress(),
            c.getNotes(),
            CompanyType.valueOf(c.getType())))
        .forEach(companyService::createCompany);
  }

  public void writeToCSV(CompanyService companyService) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(csvName), ',');
    List<String[]> data = new ArrayList<>();
    //add header
    //data.add(new String[]{"name", "shortName", "webAddress", "address", "notes", "type"});
    for (Company company : companyService.getCompanies()) {
      data.add(companyToStringArray(company));
    }

    writer.writeAll(data);
    writer.close();
  }
}
