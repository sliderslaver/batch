package aitho.example.batch.processor;

import aitho.example.batch.model.User;
import org.springframework.batch.item.ItemProcessor;


public class CustomProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        return User.builder()
                .firstName(user.getFirstName().toUpperCase())
                .lastName(user.getLastName().toUpperCase())
                .build();
    }
}
