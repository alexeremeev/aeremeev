package ru.job4j.socket;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.io.chat.StubIO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ServerTest {
    /**
     * Запускаем сервер в отдельном треде.
     */
    @Before
    public void initServer() {
        Server server = new Server("c:/projects/aeremeev/wiseman.txt", "c:/projects/aeremeev/wiselog.txt");
        Thread serverThread = new Thread() {
            public void run() {
                server.init();
                server.exchange();
            }
        };
        serverThread.start();
    }

    /**
     * Тест заглушками. После сравниваем логи сервера и клиента на идентичность.
     * @throws IOException IOException.
     */
    @Test
    public void whenExchangeMessagesThenLogFilesIdentical() throws IOException {
        String[] inputs = new String[] {"привет", "поделись своей мудростью", "прошу, еще раз поделись", "пока"};
        StubIO stubIO = new StubIO(inputs);
        Client client = new Client(stubIO, "c:/projects/aeremeev/userlog.txt");
        client.init();
        client.exchange();

        byte[] userBytes = Files.readAllBytes(Paths.get("c:/projects/aeremeev/userlog.txt"));
        byte[] wiseBytes = Files.readAllBytes(Paths.get("c:/projects/aeremeev/wiselog.txt"));

        String user = new String(userBytes, StandardCharsets.UTF_8);
        String wiseman = new String(wiseBytes, StandardCharsets.UTF_8);

        assertEquals("The content in the strings should match", user, wiseman);

    }
}