package aitho.example.batch.writer;

import aitho.example.batch.model.User;
import aitho.example.batch.repository.AithoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomWriter implements ItemWriter<User> {

    @Autowired
    private AithoRepository aithoRepository;

    @Override
    public void write(List<? extends User> list) throws Exception {
        aithoRepository.saveAll(list);
    }
}
