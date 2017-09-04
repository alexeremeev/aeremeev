package ru.job4j.professions;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестируем медицину.
 */
public class DoctorTest {
    /**
     * Тест для дианостики.
     */
    @Test
    public void tryDoctorToDiagnose() {
        Doctor doctor = new Doctor("Руслан", 45);
        String expected = "Доктор Руслан диагностирует болезнь у Алексей";
        String actual = doctor.diagnose(new Human("Алексей")).toString();
        assertThat(actual, is(expected));
    }
    /**
     * Тест для лечения.
     */
    @Test
    public void tryDoctorToHeal() {
        Doctor doctor = new Doctor("Руслан", 45);
        String expected = "Доктор Руслан лечит грипп";
        String actual = doctor.heal(new Disease("грипп")).toString();
        assertThat(actual, is(expected));
    }
    /**
     * Тест для выписавания рецептов.
     */
    @Test
    public void tryDoctorToGetMedicine() {
        Doctor doctor = new Doctor("Руслан", 45);
        String expected = "Доктор Руслан выписывает лекарство от грипп";
        String actual = doctor.getMedicine(new Disease("грипп")).toString();
        assertThat(actual, is(expected));
    }
}
