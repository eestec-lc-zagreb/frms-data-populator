package hr.eestec_zg.populator;

import static hr.eestec_zg.populator.Populator.CsvPopulators.readCompaniesFromCsv;
import static hr.eestec_zg.populator.Populator.CsvPopulators.readEventsFromCsv;
import static hr.eestec_zg.populator.Populator.CsvPopulators.readUsersFromCsv;
import static hr.eestec_zg.populator.Populator.CsvPopulators.writeCompaniesToCsv;
import static hr.eestec_zg.populator.Populator.CsvPopulators.writeEventsToCsv;
import static hr.eestec_zg.populator.Populator.CsvPopulators.writeUsersToCsv;

import hr.eestec_zg.frmscore.config.CoreConfig;
import hr.eestec_zg.frmscore.services.CompanyService;
import hr.eestec_zg.frmscore.services.EventService;
import hr.eestec_zg.frmscore.services.UserService;
import hr.eestec_zg.populator.config.AppConfig;
import hr.eestec_zg.populator.csv.company.CompanyCsvPopulator;
import hr.eestec_zg.populator.csv.event.EventCsvPopulator;
import hr.eestec_zg.populator.csv.user.UserCsvPopulator;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * 1. parameter: "export" or "import"
 * 2. parameter: "companies" or "users" or "events"
 * 3. parameter: file name in which data will be saved (export)
 * or file name from which data will be read (import)
 *
 * java -jar populator.jar export companies ./companies.csv
 */
public class Populator {

    private static final Logger logger = LoggerFactory.getLogger(Populator.class);
    private static final String export = "export";
    private static final String input = "import"; //it's input because import is reserved word
    private static final String companies = "companies";
    private static final String users = "users";
    private static final String events = "events";

    public static String csvName = "";

    public static void main(String[] args) {
        logger.info("Starting...");

        if (System.getProperty("spring.profiles.active") == null) {
            System.setProperty("spring.profiles.active", "production");
        }

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            AppConfig.class, CoreConfig.class);

        CompanyService companyService = ctx.getBean(CompanyService.class);
        EventService eventService = ctx.getBean(EventService.class);
        UserService usersService = ctx.getBean(UserService.class);

        String IO = args[0];
        String type = args[1];
        csvName = args[2];

        switch (type) {
            case companies:
                switch (IO) {
                    case input:
                        writeCompaniesToCsv(companyService);
                        break;
                    case export:
                        readCompaniesFromCsv(companyService);
                        break;
                    default:
                        System.out.println("You should choose either \"import\" or \"export\"");
                        break;
                }
                break;
            case events:
                switch (IO) {
                    case input:
                        readEventsFromCsv(eventService);
                        break;
                    case export:
                        writeEventsToCsv(eventService);
                        break;
                    default:
                        System.out.println("You should choose either \"import\" or \"export\"");
                        break;
                }
                break;
            case users:
                switch (IO) {
                    case input:
                        readUsersFromCsv(usersService);
                        break;
                    case export:
                        writeUsersToCsv(usersService);
                        break;
                    default:
                        System.out.println("You should choose either \"import\" or \"export\"");
                        break;
                }
                break;
            default:
                System.out.println("Data type not entered correctly!");
                break;
        }

        ctx.close();

        logger.info("Finishing...");
    }


    public static class CsvPopulators {


        static void readCompaniesFromCsv(CompanyService companyService) {
            try {
                new CompanyCsvPopulator().readFromCSV(companyService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static void readEventsFromCsv(EventService eventService) {
            try {
                new EventCsvPopulator().readFromCsv(eventService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static void readUsersFromCsv(UserService userService) {
            try {
                new UserCsvPopulator().readFromCsv(userService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static void writeCompaniesToCsv(CompanyService companyService) {
            try {
                new CompanyCsvPopulator().writeToCSV(companyService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static void writeEventsToCsv(EventService eventService) {
            try {
                new EventCsvPopulator().writeToCsv(eventService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static void writeUsersToCsv(UserService userService) {
            try {
                new UserCsvPopulator().writeToCsv(userService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



