package aitho.example.batch.writer;

import aitho.example.batch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@RequiredArgsConstructor
public class CustomWriter implements ItemWriter<User> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void write(List<? extends User> list) {
        list.forEach(user ->
                jdbcTemplate.update("INSERT INTO user_table (id, firstName, lastName, age) VALUES (?, ?, ?, ?)",
                user.getId(), user.getFirstName(), user.getLastName(), user.getAge())
        );
    }
}
