package hr.eestec_zg.populator;

import hr.eestec_zg.frmscore.config.CoreConfig;
import hr.eestec_zg.frmscore.domain.models.TaskStatus;
import hr.eestec_zg.frmscore.services.CompanyService;
import hr.eestec_zg.frmscore.services.EventService;
import hr.eestec_zg.frmscore.services.TaskService;
import hr.eestec_zg.frmscore.services.UserService;
import hr.eestec_zg.populator.config.AppConfig;
import hr.eestec_zg.populator.csv.company.CompanyCsvPopulator;
import hr.eestec_zg.populator.csv.event.EventCsvPopulator;
import hr.eestec_zg.populator.csv.user.UserCsvPopulator;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Populator {
    private static final Logger logger = LoggerFactory.getLogger(Populator.class);
    private static final String export = "export";
    private static final String input = "import"; //it's input because import is reserved word

    public static void main(String[] args) {
        logger.info("Starting...");

        if (System.getProperty("spring.profiles.active") == null) {
            System.setProperty("spring.profiles.active", "production");
        }

        // Starting Spring Application context
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                AppConfig.class, CoreConfig.class);

        //-----------------------------------------------------------------------------

        UserService userService = ctx.getBean(UserService.class); // the way of getting object instances from Spring
        CompanyService companyService = ctx.getBean(CompanyService.class);
        EventService eventService = ctx.getBean(EventService.class);

        //check is it export or import
        if(args[0].compareTo(export) == 0){

            //Try writing all users to CSV
            try{
                new UserCsvPopulator().writeToCsv(userService);
            } catch (IOException e) {
                e.printStackTrace();
            }


            //Try writing all companies to CSV
            try {
                new CompanyCsvPopulator().writeToCSV(companyService);
            } catch (IOException e) {
                e.printStackTrace();
            }


            //Try writing all events to CSV
            try {
                new EventCsvPopulator().writeToCsv(eventService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(args[0].compareTo(input) == 0){


            //import all users
            try{
                new UserCsvPopulator().readFromCsv(userService);
            } catch (IOException e) {
                e.printStackTrace();
            }


            //Import all companies
            try {
                new CompanyCsvPopulator().readFromCSV(companyService);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Import all events
            try {
                new EventCsvPopulator().readFromCsv(eventService);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Invalid input, valid options are: " + input + " and " + export);
        }

        //---------------------------------------------------------------------------------------

        TaskService taskService = ctx.getBean(TaskService.class);
        taskService.getTaskByStatus(TaskStatus.ACCEPTED);

        companyService.getCompanies()
                .forEach(System.out::println);

        userService
            .getAllUsers()
            .forEach(System.out::println);

        eventService.getEvents()
            .forEach(System.out::println);

        //---------------------------------------------------------------------------------------

        ctx.close(); // you should always close context in the end

        logger.info("Finishing...");
    }

}
