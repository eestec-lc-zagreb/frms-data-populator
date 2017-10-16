package hr.eestec_zg.populator.tocsv;

import hr.eestec_zg.frmscore.domain.models.Company;

public class CsvPrepare {

  public static String[] companyToStringArray(Company company){
    return new String[]{company.getName(), company.getShortName(), company.getWebAddress(), company.getAddress(), company.getNotes(), company.getType().toString()};
  }

}
