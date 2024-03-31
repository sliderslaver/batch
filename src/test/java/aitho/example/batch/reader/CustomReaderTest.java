package aitho.example.batch.reader;

import aitho.example.batch.model.User;
import aitho.example.batch.model.Users;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomReaderTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CustomReader customReader;

    @Test
    void read() {
        val user = User.builder()
                .age(18)
                .build();

        val users = Users.builder()
                .users(List.of(user))
                .build();

        when(restTemplate.getForObject(anyString(),any())).thenReturn(users);

        val out = customReader.read();

        assertNotNull(out);
        assertEquals(18, out.getAge());
    }
}