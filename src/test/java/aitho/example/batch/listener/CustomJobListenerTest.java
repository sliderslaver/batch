package aitho.example.batch.listener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CustomJobListenerTest {

    @InjectMocks
    private CustomJobListener customJobListener;

    private JobExecution jobExecution;

    @BeforeEach
    public void setUp(){
        jobExecution = new JobExecution(1L, new JobParameters());
        jobExecution.setStatus(BatchStatus.STARTING);
    }

    @Test
    void beforeJob() {
        assertDoesNotThrow(() -> customJobListener.beforeJob(jobExecution));
    }

    @Test
    void afterJob() {
        assertDoesNotThrow(() -> customJobListener.afterJob(jobExecution));
    }
}