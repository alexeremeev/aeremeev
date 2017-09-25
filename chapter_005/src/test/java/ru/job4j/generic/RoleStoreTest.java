package ru.job4j.generic;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты хранилища RoleStore.
 */
public class RoleStoreTest {
    /**
     * Добавление новой User.
     */
    @Test
    public void whenAddRoleThenGetThisRole() {
        RoleStore roleStore = new RoleStore(5);
        Role role = new Role("test role");
        roleStore.add(role);

        assertThat(roleStore.get(0), is(role));
    }

    /**
     * Обвноление Id у существующей User.
     */
    @Test
    public void whenUpdateRoleThenGetNewRole() {
        RoleStore roleStore = new RoleStore(5);
        Role role = new Role("test id");
        roleStore.add(role);
        role.setId("new id");
        roleStore.update(role);

        assertThat(roleStore.get(0).getId(), is("new id"));
    }

    /**
     * Удаление Role из хранилища.
     */
    @Test
    public void whenDeleteRoleFromStoreThenGetNextRole() {
        RoleStore roleStore = new RoleStore(5);
        Role doctor = new Role("Doctor");
        Role teacher = new Role("Teacher");
        Role engineer = new Role("Engineer");
        roleStore.add(doctor);
        roleStore.add(teacher);
        roleStore.add(engineer);
        roleStore.remove(teacher);

        assertThat(roleStore.get(1), is(engineer));
    }

}