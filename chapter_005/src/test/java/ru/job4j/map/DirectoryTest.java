package ru.job4j.map;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Directory.
 */
public class DirectoryTest {
    /**
     * Проверка методов insert(), get().
     */
    @Test
    public void whenAddToDirectoryThenGetValue() {
        Directory<String, Long> phonebook = new Directory<>(18);
        phonebook.insert("Еремеев Александр Юрьевич", 75554441111L);
        phonebook.insert("Иванов Иван Иванович", 79998887777L);
        phonebook.insert("Петров Петр Петрович", 70007776666L);
        phonebook.insert("Сергеев Сергей Сергеевич", 72223335555L);

        for (String s : phonebook) {
            System.out.println(s + " " + phonebook.get(s));
        }

        assertThat(phonebook.get("Петров Петр Петрович"), is(70007776666L));
    }

    /**
     * Попытка добавить дубль в справочник.
     */
    @Test
    public void whenAddDoubleToDirectoryThenResultIsFalse() {
        Directory<String, Long> phonebook = new Directory<>(18);

        phonebook.insert("Еремеев Александр Юрьевич", 75554441111L);

        assertThat(phonebook.insert("Еремеев Александр Юрьевич", 75554441111L), is(false));
    }

    /**
     * Проверка удаления пары ключ-значение из справочника.
     * @throws NoSuchElementException выкидывает исключение, если пара не найдена.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFromDirectoryWhenGetThrowsException() throws NoSuchElementException {
        Directory<String, Long> phonebook = new Directory<>(18);
        phonebook.insert("Еремеев Александр Юрьевич", 75554441111L);
        phonebook.insert("Иванов Иван Иванович", 79998887777L);
        phonebook.insert("Петров Петр Петрович", 70007776666L);
        phonebook.insert("Сергеев Сергей Сергеевич", 72223335555L);

        phonebook.delete("Петров Петр Петрович");

        assertThat(phonebook.get("Петров Петр Петрович"), is(70007776666L));
    }

}
