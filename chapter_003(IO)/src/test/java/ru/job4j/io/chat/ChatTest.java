package ru.job4j.io.chat;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест класса Chat.
 */
public class ChatTest {
    /**
     * Проверка лога чата при вводе заглушками.
     */
    @Test
    public void whenUseChatThenOutputNotEmpty() {
        String[] inputs = new String[] {"привет", "скажи что-нибудь", "стоп", "продолжить", "закончить"};
        StubIO stubIO = new StubIO(inputs);
        Chat chat = new Chat(stubIO, "c:/projects/aeremeev/chat.txt", "c:/projects/aeremeev/log.txt");
        chat.exchangeMessages();

        int index = 0;
        File logFile = new File("c:/projects/aeremeev/log.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {

            assertThat(reader.readLine(), is(String.format("user: %s", inputs[index++])));
            assertNotNull(reader.readLine());
            assertThat(reader.readLine(), is(String.format("user: %s", inputs[index++])));
            assertNotNull(reader.readLine());
            assertThat(reader.readLine(), is(String.format("user: %s", inputs[index++])));
            assertThat(reader.readLine(), is(String.format("user: %s", inputs[index++])));
            assertNotNull(reader.readLine());
            logFile.delete();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}