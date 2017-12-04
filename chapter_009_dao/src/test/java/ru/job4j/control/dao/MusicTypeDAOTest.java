package ru.job4j.control.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.control.models.MusicType;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тесты MusicTypeDAO.
 */
public class MusicTypeDAOTest {
    private final PSQLpool pool = PSQLpool.getInstance();
    private final MusicTypeDAO dao = new MusicTypeDAO(pool);
    /**
     * Очитска таблицы.
     */
    @Before
    public void clearTable() {
        dao.clearTable();
    }

    /**
     * Тест добавления музыкального направления.
     */
    @Test
    public void whenAddMusicTypeThenGetMusicType() {
        MusicType musicType = new MusicType();
        musicType.setId(1);
        musicType.setName("Rock");
        dao.createMusicType(musicType);
        assertThat(dao.getAll().get(0), is(musicType));
    }

    /**
     * Тест редактирования музыкального направления.
     */
    @Test
    public void whenUpdateMusicTypeThenGetUpdatedMusicType() {
        MusicType musicType = new MusicType();
        musicType.setId(1);
        musicType.setName("Rock");
        dao.createMusicType(musicType);

        musicType.setName("R'n'B");

        dao.updateMusicType(musicType);

        assertThat(dao.getAll().get(0), is(musicType));
    }

    /**
     * Тест поиска музыкального направления по ID.
     */
    @Test
    public void whenSearchMusicTypeByIDThenGeMusicType() {
        MusicType expected = new MusicType();
        expected.setId(1);
        expected.setName("Rock");
        dao.createMusicType(expected);

        assertThat(dao.findMusicTypeById(1), is(expected));
    }

    /**
     * Тест поиска музыкального направления по названию.
     */
    @Test
    public void whenSearchMusicTypeByNameThenGetMusicType() {
        MusicType expected = new MusicType();
        expected.setId(1);
        expected.setName("Rock");
        dao.createMusicType(expected);

        assertThat(dao.findMusicTypeByName("Rock"), is(expected));
    }
    /**
     * Тест удаления музыкального направления.
     */
    @Test
    public void whenDeleteMusicTypesThenListIsEmpty() {
        MusicType musicType = new MusicType();
        musicType.setId(1);
        musicType.setName("Rock");
        dao.createMusicType(musicType);

        dao.deleteMusicType(1);

        assertThat(dao.getAll().isEmpty(), is(true));
    }
}
