package ru.job4j.control.repository;

import ru.job4j.control.dao.*;
import ru.job4j.control.models.Address;
import ru.job4j.control.models.MusicType;
import ru.job4j.control.models.Role;
import ru.job4j.control.models.User;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * class UserRepository - репозиторий для модели User.
 */
public class UserRepository {

    private PSQLpool pool = PSQLpool.getInstance();
    private AddressDAO addressDAO = pool.getAddressDAO();
    private MusicTypeDAO musicTypeDAO = pool.getMusicTypeDAO();
    private RoleDAO roleDAO = pool.getRoleDAO();
    private UserDAO userDAO = pool.getUserDAO();

    /**
     * class Reference - модель перекрестной ссылки, собирающей модели Address, MusicType, Role, User по заданным критериям.
     */
    public static class Reference {
        private User user;
        private Role role;
        private Address address;
        private Set<Integer> musicType;
        private AddressDAO addressDAO;
        private MusicTypeDAO musicTypeDAO;
        private RoleDAO roleDAO;
        private UserDAO userDAO;

        public Reference(AddressDAO addressDAO, MusicTypeDAO musicTypeDAO, RoleDAO roleDAO, UserDAO userDAO) {
            this.addressDAO = addressDAO;
            this.musicTypeDAO = musicTypeDAO;
            this.roleDAO = roleDAO;
            this.userDAO = userDAO;
        }

        public User getUser() {
            return user;
        }

        public Role getRole() {
            return role;
        }

        public Address getAddress() {
            return address;
        }

        public Set<Integer> getMusicType() {
            return musicType;
        }

        /**
         * Заолнить ссылку.
         * @param userId ID пользовтеля.
         * @return ссылка с заполенными моделями Address, MusicType, Role, User.
         */
        public boolean fillReference(int userId) {
            boolean result = false;
            this.user = this.userDAO.findUserById(userId);
            if (this.user != null) {
                result = true;
                this.role = this.roleDAO.findRoleById(this.user.getRoleId());
                this.address = this.addressDAO.findAddressById(this.user.getAddressId());
                this.musicType = this.musicTypeDAO.findUserMusicTypeByUserID(this.user.getId());
            }
            return result;
        }

        @Override
        public String toString() {
            return String.format("Reference{user=%s, role=%s, address=%s, musicType=%s}",
                    this.user, this.role, this.address, this.musicType);
        }
    }

    /**
     * Создать пользователя и все связанные с ним сущности.
     * @param name имя.
     * @param password пароль.
     * @param address адрес.
     * @param role роль.
     * @param musicTypes музыкальные стили.
     * @return количество вставленных в БД строк.
     */
    public int createReference(String name, String password, String address, String role, String ...musicTypes) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Address findAddress = this.addressDAO.findAddressByName(address);
        if (findAddress != null) {
            user.setAddressId(findAddress.getId());
        } else {
            Address insert = new Address();
            insert.setName(address);
            user.setAddressId(this.addressDAO.createAddress(insert));
        }

        Role findRole = this.roleDAO.findRoleByName(role);
        if (findRole != null) {
            user.setRoleId(findRole.getId());
        } else {
            Role insert = new Role();
            insert.setName(role);
            user.setRoleId(this.roleDAO.createRole(insert));
        }

        for (String musicType : musicTypes) {
            MusicType findMusicType = this.musicTypeDAO.findMusicTypeByName(musicType);
            if (findMusicType != null) {
                user.setMusicTypeId(findMusicType.getId());
            } else {
                MusicType insert = new MusicType();
                insert.setName(musicType);
                user.setMusicTypeId(this.musicTypeDAO.createMusicType(insert));
            }
        }
        return this.userDAO.createUser(user);

    }

    /**
     * Получить все ссылки по адресу.
     * @param address адрес.
     * @return список ссылок.
     */
    public List<Reference> getReferences(Address address) {
        List<Reference> result = new CopyOnWriteArrayList<>();

        Address find = this.addressDAO.findAddressByName(address.getName());

        if (find != null) {
            List<User> users = this.userDAO.getAll();
            for (User user : users) {
                if (find.getId() == user.getAddressId()) {
                    Reference reference = new Reference(this.addressDAO, this.musicTypeDAO, this.roleDAO, this.userDAO);
                    reference.fillReference(user.getId());
                    result.add(reference);
                    break; // address is unique for each user, so there's only one user ???
                }
            }
        }
        return result;
    }

    /**
     * Получить все ссылки по музыкальному стилю.
     * @param musicType музыкальный стиль.
     * @return список ссылок.
     */
    public List<Reference> getReferences(MusicType musicType) {
        List<Reference> result = new CopyOnWriteArrayList<>();

        MusicType find = this.musicTypeDAO.findMusicTypeByName(musicType.getName());

        if (find != null) {
            List<User> users = this.userDAO.getAll();
            for (User user : users) {
                Set<Integer> musicTypes = user.getMusicTypeId();
                if (musicTypes.contains(find.getId())) {
                    Reference reference = new Reference(this.addressDAO, this.musicTypeDAO, this.roleDAO, this.userDAO);
                    reference.fillReference(user.getId());
                    result.add(reference);
                }
            }
        }
        return result;
    }

    /**
     * Получить список всех ссылок по роли.
     * @param role роль.
     * @return список ссылок.
     */
    public List<Reference> getReferences(Role role) {

        List<Reference> result = new CopyOnWriteArrayList<>();

        Role find = this.roleDAO.findRoleByName(role.getName());

        if (find != null) {
            List<User> users = this.userDAO.getAll();
            for (User user : users) {
                if (find.getId() == user.getRoleId()) {
                    Reference reference = new Reference(this.addressDAO, this.musicTypeDAO, this.roleDAO, this.userDAO);
                    reference.fillReference(user.getId());
                    result.add(reference);
                }
            }
        }
        return result;
    }

}
