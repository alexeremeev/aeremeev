package ru.job4j.filemanager;

import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 * Тесты класса Menu.
 */
public class MenuTest {

    private final static String SEPARATOR = System.getProperty("line.separator");

    /**
     * Проверка методов меню заклушками.
     * @param messages массив сообщений отправляемый во входящий поток меню.
     * @param expected ожидаемая строка.
     * @throws IOException IOException.
     */
    public void whenInputMockCommandsThenGetExpectedOutput(String[] messages, String expected) throws IOException {
        byte[] bytes = new byte[0];

        for (String message : messages) {
            bytes = ArrayUtils.addAll(bytes, ArrayUtils.addAll(new byte[]{0, (byte) message.length()}, message.getBytes()));
        }

        Socket socket = Mockito.mock(Socket.class);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Mockito.when(socket.getInputStream()).thenReturn(byteArrayInputStream);

        ManagerTest managerTest = new ManagerTest(null, socket);
        Menu menu = new Menu(socket);
        menu.init(managerTest);

        Assert.assertThat(managerTest.getMockMethods(), Is.is(expected));
    }

    /**
     * Проверить поочередно все методы меню заглушками.
     * @throws IOException IOException.
     */
    @Test
    public void whenRunThroughAllMenuActionsThenGetExpectedResult() throws IOException {
        String expected = String.format("exitMock%s", SEPARATOR);
        this.whenInputMockCommandsThenGetExpectedOutput(new String[]{"EXIT"}, expected);
        expected = String.format("showMock%sexitMock%s", SEPARATOR, SEPARATOR);
        this.whenInputMockCommandsThenGetExpectedOutput(new String[]{"SHOW", "EXIT"}, expected);
        expected = String.format("goToDirectoryMock%sexitMock%s", SEPARATOR, SEPARATOR);
        this.whenInputMockCommandsThenGetExpectedOutput(new String[]{"/CD DIR", "EXIT"}, expected);
        expected = String.format("toHomeDirMock%sexitMock%s", SEPARATOR, SEPARATOR);
        this.whenInputMockCommandsThenGetExpectedOutput(new String[]{"HOME", "EXIT"}, expected);
        expected = String.format("uploadMock%sexitMock%s", SEPARATOR, SEPARATOR);
        this.whenInputMockCommandsThenGetExpectedOutput(new String[]{"DWNL FileName", "EXIT"}, expected);
        expected = String.format("downloadMock%sexitMock%s", SEPARATOR, SEPARATOR);
        this.whenInputMockCommandsThenGetExpectedOutput(new String[]{"UPLD FileName", "EXIT"}, expected);
        expected = String.format("unknownCommandMock%sexitMock%s", SEPARATOR, SEPARATOR);
        this.whenInputMockCommandsThenGetExpectedOutput(new String[]{"123", "EXIT"}, expected);
    }

    /**
     * Переопределение методов для тестирования.
     */
    class ManagerTest extends Manager {
        /**
         * Спсок выбранных методов.
         */
        private StringBuilder mockMethods = new StringBuilder();

        /**
         * Получить заглушку выбранных методов.
         * @return заглушка выбранного методов.
         */
        public String getMockMethods() {
            return this.mockMethods.toString();
        }

        /**
         * Конструктор.
         * @param rootDir корневой каталог.
         * @param socket socket.
         */
        ManagerTest(File rootDir, Socket socket) {
            super(rootDir, socket);
        }
        @Override
        public void exit() {
            this.mockMethods.append("exitMock");
            this.mockMethods.append(SEPARATOR);
        }
        @Override
        public void show() {
            this.mockMethods.append("showMock");
            this.mockMethods.append(SEPARATOR);
        }
        @Override
        public void goToDir(String select) {
            this.mockMethods.append("goToDirectoryMock");
            this.mockMethods.append(SEPARATOR);
        }
        @Override
        public void goToRoot() {
            this.mockMethods.append("toHomeDirMock");
            this.mockMethods.append(SEPARATOR);
        }
        @Override
        public void upload(String fileName) {
            this.mockMethods.append("uploadMock");
            this.mockMethods.append(SEPARATOR);
        }
        @Override
        public void download(String fileName) {
            this.mockMethods.append("downloadMock");
            this.mockMethods.append(SEPARATOR);
        }
        @Override
        public void unknownCommand() {
            this.mockMethods.append("unknownCommandMock");
            this.mockMethods.append(SEPARATOR);
        }
        @Override
        public void sendMessage(String message) {
            this.mockMethods.append("sendMessageMock");
            this.mockMethods.append(SEPARATOR);
        }
        @Override
        public void readMessage() {
            this.mockMethods.append("readMessageMock");
            this.mockMethods.append(SEPARATOR);
        }
    }
}