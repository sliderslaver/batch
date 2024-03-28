package aitho.example.batch.engine;

import aitho.example.batch.model.User;
import aitho.example.batch.reader.CustomReader;
import aitho.example.batch.util.CustomJobListener;
import aitho.example.batch.writer.CustomWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CustomReader apiItemReader() {
        return new CustomReader(restTemplate());
    }

    @Bean
    public CustomWriter customWriter() {
        return new CustomWriter();
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

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:h2:mem:testdb");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }

//    @Bean
//    public JobRepository jobsRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setTransactionManager(transactionManager);
//        factory.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE");
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManagers() {
//        return new ResourcelessTransactionManager();
//    }

}