package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Image DAO tests HSQL DB based.
 * @author aeremeev.
 * @version 1.1
 * @since 01.02.2018
 */
public class ImageDAOTest {

    private DAOInterface<Image> dao = new GenericDAO<>();

    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        dao.executeQuery("Truncate table image restart identity and commit no check");
    }
    /**
     * Test of adding new image.
     */
    @Test
    public void whenAddImageThenGetCorrectImage() {
        Image image = new Image();
        image.setData("Picture".getBytes());
        dao.saveOrUpdate(image);
        List<Image> result = dao.getAll(Image.class);
        assertThat(result.get(0), is(image));
    }
    /**
     * Test of updating image.
     */
    @Test
    public void whenUpdateImageThenGetUpdatedResult() {
        Image image = new Image();
        image.setData("Picture".getBytes());
        dao.saveOrUpdate(image);
        image.setData("Another picture".getBytes());
        dao.saveOrUpdate(image);
        List<Image> result = dao.getAll(Image.class);
        assertThat(result.get(0), is(image));
    }
    /**
     * Test of deleting image.
     */
    @Test
    public void whenDeleteImageThenResultListIsEmpty() {
        Image image = new Image();
        image.setData("Picture".getBytes());
        dao.saveOrUpdate(image);
        dao.delete(image);
        List<Image> result = dao.getAll(Image.class);
        assertTrue(result.isEmpty());

    }
    /**
     * Test of getting images list.
     */
    @Test
    public void whenAddCoupleImagesThenGetAllImages() {
        Image picture = new Image();
        picture.setData("Picture".getBytes());
        Image anotherPicture = new Image();
        anotherPicture.setData("Another picture".getBytes());
        dao.saveOrUpdate(picture);
        dao.saveOrUpdate(anotherPicture);

        List<Image> expected = new ArrayList<>(Arrays.asList(picture, anotherPicture));
        List<Image> result = dao.getAll(Image.class);

        assertThat(result, is(expected));
    }
    /**
     * Test of searching image by ID.
     */
    @Test
    public void whenSearchImageByIDThenGetImage() {
        Image expected = new Image();
        expected.setData("Picture".getBytes());
        dao.saveOrUpdate(expected);

        Image result = dao.findById(Image.class, expected.getId());

        assertThat(result, is(expected));
    }
}