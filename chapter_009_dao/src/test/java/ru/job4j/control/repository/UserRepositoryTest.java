package ru.job4j.control.repository;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.control.dao.PSQLpool;
import ru.job4j.control.models.Address;
import ru.job4j.control.models.MusicType;

import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    private PSQLpool pool = PSQLpool.getInstance();
    UserRepository repository = new UserRepository();

    @Before
    public void clearAndFill() {

        pool.getRoleDAO().fillRoles();
        pool.getMusicTypeDAO().fillMusicType();
        repository.createReference("admin", "admin", "Default city", "admin", "Pop");
        repository.createReference("user", "user", "Not Default city", "user", "Rock");
        repository.createReference("mandator", "mandator", "Overseas", "mandator", "Classic");
    }
    @Test
    public void test() {
        MusicType musicType = new MusicType();
        musicType.setName("Rock");
        List<UserRepository.Reference> result = repository.getReferences(musicType);
        for (UserRepository.Reference reference : result) {
            System.out.println(reference);
        }
    }

}