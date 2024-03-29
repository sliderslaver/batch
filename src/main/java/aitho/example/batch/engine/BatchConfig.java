package aitho.example.batch.engine;

import aitho.example.batch.listener.CustomJobListener;
import aitho.example.batch.model.User;
import aitho.example.batch.reader.CustomReader;
import aitho.example.batch.writer.CustomWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;


@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public CustomReader apiItemReader() {
        return new CustomReader(new RestTemplate());
    }

    @Bean
    public CustomWriter customWriter() {
        return new CustomWriter(jdbcTemplate());
    }

    @Bean
    public CustomJobListener customJob() {
        return new CustomJobListener();
    }

    @Bean
    public Step step1(CustomReader reader, CustomWriter writer) {
        return stepBuilderFactory.get("step1")
                .<User, User>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importJob(Step step1) {
        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer())
                .listener(customJob())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return new JdbcTemplate(dataSource);
    }
}
