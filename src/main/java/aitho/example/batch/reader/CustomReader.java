package aitho.example.batch.reader;

import aitho.example.batch.model.User;
import aitho.example.batch.model.Users;
import lombok.val;
import org.springframework.batch.item.ItemReader;
import org.springframework.web.client.RestTemplate;
import java.util.Iterator;

public class CustomReader implements ItemReader<User> {

    private final RestTemplate restTemplate;
    private Iterator<User> userIterator;
    private boolean dataFetched = false;

    public CustomReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User read() throws Exception {
        if (!dataFetched) {
            fetchDataFromAPI();
            dataFetched = true;
        }
        return userIterator != null && userIterator.hasNext() ? userIterator.next() : null;
    }

    private void fetchDataFromAPI() {
        val url = "https://dummyjson.com/users?limit=3&skip=10&select=id,firstName,lastName,age";
        val users = restTemplate.getForObject(url, Users.class);
        if (users != null) {
            userIterator = users.getUsers().iterator();
        }
    }
}
