package ru.job4j.control.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.job4j.control.dao.PSQLpool;
import ru.job4j.control.models.Address;
import ru.job4j.control.models.MusicType;
import ru.job4j.control.models.Role;
import ru.job4j.control.models.User;
import ru.job4j.control.repository.RoleRepository;
import ru.job4j.control.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * RepositoryController - сервлет обработки запросов в репозиторий и отображения JSON.
 */
public class RepositoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        String address = req.getParameter("address");
        String music = req.getParameter("music");
        String role = req.getParameter("role");
        String roleAll = req.getParameter("roleAll");

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        JsonObject jsonObject = new JsonObject();

        boolean filledField = false;

        if (!address.isEmpty() && !filledField) {
            jsonObject.addProperty("user", getAddress(address).toString());
            filledField = true;
        }

        if (!music.isEmpty() && !filledField) {
            jsonObject.addProperty("user", getMusic(music).toString());
            filledField = true;
        }

        if (!role.isEmpty() && !filledField) {
            jsonObject.addProperty("user", getRole(role).toString());
            filledField = true;
        }

        if (!roleAll.isEmpty() && !filledField) {
            jsonObject.addProperty("user", getAllRole().toString());
        }

        writer.append(jsonObject.toString());
        writer.flush();
    }


    /**
     * Получить JSON массив по запросу в репозиторий с параметром Address.
     * @param address название адреса.
     * @return JSON массив.
     */
    public JsonArray getAddress(String address) {

        UserRepository userRepository = new UserRepository();
        Address searchAddress = new Address();
        searchAddress.setName(address);
        List<UserRepository.Reference> users = userRepository.getReferences(searchAddress);
        JsonArray result = new JsonArray();
        for (UserRepository.Reference userReferences : users) {
            JsonObject json = new JsonObject();
            List<String> allUserMusic = new ArrayList<>();
            for (int musicTypeID: userReferences.getMusicType()) {
                MusicType musicType = PSQLpool.getInstance().getMusicTypeDAO().findMusicTypeById(musicTypeID);
                allUserMusic.add(musicType.getName());
            }
            json.addProperty("name", userReferences.getUser().getName());
            json.addProperty("address", userReferences.getAddress().getName());
            json.addProperty("musictypes", allUserMusic.toString());
            json.addProperty("role", userReferences.getRole().getName());
            result.add(json);
        }
        return result;
    }

    /**
     * Получить JSON массив по запросу в репозиторий с параметром MusicType.
     * @param music название музыкального стиля.
     * @return JSON массив.
     */
    public JsonArray getMusic(String music) {
        UserRepository userRepository = new UserRepository();
        MusicType searchMusic = new MusicType();
        searchMusic.setName(music);
        List<UserRepository.Reference> users = userRepository.getReferences(searchMusic);
        JsonArray result = new JsonArray();
        for (UserRepository.Reference userReferences : users) {
            JsonObject json = new JsonObject();
            List<String> allUserMusic = new ArrayList<>();
            for (int musicTypeID: userReferences.getMusicType()) {
                MusicType musicType = PSQLpool.getInstance().getMusicTypeDAO().findMusicTypeById(musicTypeID);
                allUserMusic.add(musicType.getName());
            }
            json.addProperty("name", userReferences.getUser().getName());
            json.addProperty("address", userReferences.getAddress().getName());
            json.addProperty("musictypes", allUserMusic.toString());
            json.addProperty("role", userReferences.getRole().getName());
            result.add(json);
        }
        return result;
    }

    /**
     * Получить JSON массив по запросу в репозиторий с параметром Role.
     * @param role название роли
     * @return JSON массив.
     */
    public JsonArray getRole(String role) {
        UserRepository userRepository = new UserRepository();
        Role searchRole = new Role();
        searchRole.setName(role);
        List<UserRepository.Reference> users = userRepository.getReferences(searchRole);
        JsonArray result = new JsonArray();
        for (UserRepository.Reference userReferences : users) {
            JsonObject json = new JsonObject();
            List<String> allUserMusic = new ArrayList<>();
            for (int musicTypeID: userReferences.getMusicType()) {
                MusicType musicType = PSQLpool.getInstance().getMusicTypeDAO().findMusicTypeById(musicTypeID);
                allUserMusic.add(musicType.getName());
            }
            json.addProperty("name", userReferences.getUser().getName());
            json.addProperty("address", userReferences.getAddress().getName());
            json.addProperty("musictypes", allUserMusic.toString());
            json.addProperty("role", userReferences.getRole().getName());
            result.add(json);
        }
        return result;
    }
    /**
     * Получить JSON массив по запросу в репозиторий по всем ролям.
     * @return JSON массив.
     */
    public JsonArray getAllRole() {
        RoleRepository roleRepository = new RoleRepository();
        List<User> users = roleRepository.getAllRoleReferences();
        JsonArray result = new JsonArray();
        for (User user : users) {
            JsonObject json = new JsonObject();
            List<String> music = new ArrayList<>();
            for (Integer integer : user.getMusicTypeId()) {
                MusicType musicType = PSQLpool.getInstance().getMusicTypeDAO().findMusicTypeById(integer);
                music.add(musicType.getName());
            }
            Address address = PSQLpool.getInstance().getAddressDAO().findAddressById(user.getAddressId());
            Role role = PSQLpool.getInstance().getRoleDAO().findRoleById(user.getRoleId());
            json.addProperty("name", user.getName());
            json.addProperty("address", address.getName());
            json.addProperty("musictypes", music.toString());
            json.addProperty("role", role.getName());
            result.add(json);
        }
        return result;
    }

}

