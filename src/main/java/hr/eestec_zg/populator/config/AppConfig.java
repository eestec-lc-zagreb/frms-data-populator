package hr.eestec_zg.populator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = {AppConfig.class})
@PropertySource(name = "application-props", value = "${APP_PROPS:classpath:application.properties}",
        ignoreResourceNotFound = true)
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Profile("production")
    @Primary
    public DataSource dataSource(Environment env) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(env.getProperty("frmsdb.url"));
        dataSource.setUsername(env.getProperty("frmsdb.username"));
        dataSource.setPassword(env.getProperty("frmsdb.password"));

        return dataSource;
    }
}
