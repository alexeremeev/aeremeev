package ru.job4j.control.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.control.models.Address;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты AddressDAO.
 */
public class AddressDAOTest {

    private final PSQLpool pool = PSQLpool.getInstance();
    private final AddressDAO dao = new AddressDAO(pool);

    /**
     * Очитска таблицы.
     */
    @Before
    public void clearTable() {
        dao.clearTable();
    }

    /**
     * Тест добавления адреса.
     */
    @Test
    public void whenAddAddressThenGetAddress() {
        Address address = new Address();
        address.setId(1);
        address.setName("Moscow");
        dao.createAddress(address);
        assertThat(dao.getAll().get(0), is(address));
    }

    /**
     * Тест редактирования адреса.
     */
    @Test
    public void whenUpdateAddressThenGetUpdatedAddress() {
        Address address = new Address();
        address.setId(1);
        address.setName("SPB");
        dao.createAddress(address);

        address.setName("Saint-Petersburg");

        dao.updateAddress(address);

        assertThat(dao.getAll().get(0), is(address));
    }

    /**
     * Тест поиска адреса по ID.
     */
    @Test
    public void whenSearchAddressByIDThenGetAddress() {
        Address expected = new Address();
        expected.setName("Moscow");
        expected.setId(1);
        dao.createAddress(expected);

        assertThat(dao.findAddressById(1), is(expected));
    }

    /**
     * Тест поиска пользователя по названию.
     */
    @Test
    public void whenSearchAddressByNameThenGetAddress() {
        Address expected = new Address();
        expected.setId(1);
        expected.setName("Saint-Petersburg");
        dao.createAddress(expected);

        assertThat(dao.findAddressByName("Saint-Petersburg"), is(expected));
    }

    /**
     * Тест удаления адреса.
     */
    @Test
    public void whenDeleteAddressesThenListIsEmpty() {
        Address address = new Address();
        address.setId(1);
        address.setName("Moscow");
        dao.createAddress(address);

        dao.deleteAddress(1);

        assertThat(dao.getAll().isEmpty(), is(true));
    }
}
