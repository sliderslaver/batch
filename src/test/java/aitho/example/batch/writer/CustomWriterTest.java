package aitho.example.batch.writer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@ExtendWith(MockitoExtension.class)
public class CustomWriterTest {

    @InjectMocks
    private CustomWriter customWriter;

    @Test
    void testWrite() {
        assertDoesNotThrow(() -> customWriter.write(List.of()));
    }
}
