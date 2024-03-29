package aitho.example.batch.reader;

import aitho.example.batch.model.User;
import aitho.example.batch.model.Users;
import org.springframework.batch.item.ItemReader;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Optional;

public class CustomReader implements ItemReader<User> {

    private static final String URL = "https://dummyjson.com/users?limit=1&skip=10&select=id,firstName,lastName,age";
    private final RestTemplate restTemplate;
    private Iterator<User> userIterator;
    private boolean dataFetched = false;

    public CustomReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User read() {
        if (!dataFetched) {
            Optional.ofNullable(restTemplate.getForObject(URL, Users.class))
                    .map(Users::getUsers)
                    .ifPresent(users -> userIterator = users.iterator());
            dataFetched = true;
        }
        return userIterator.hasNext() ? userIterator.next() : null;
    }
}
