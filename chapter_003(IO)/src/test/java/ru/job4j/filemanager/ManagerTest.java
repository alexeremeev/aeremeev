package ru.job4j.filemanager;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Тесты класса Manager.
 */
public class ManagerTest {
    /**
     * Корневой каталогю
     */
    private File rootDir;
    /**
     * Разделитель строк.
     */
    private final static String SEPARATOR = System.getProperty("line.separator");
    /**
     * Разделитель в файловой системе.
     */
    private final static String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Предварительно создать структуру в temp.dir.
     * @throws IOException IOException.
     */
    @Before
    public void createTestDirectory() throws IOException {
        File rootDir = new File(System.getProperty("java.io.tmpdir"), "rootTestDir");
        File firstDir = new File(rootDir, "firstDir");
        File secondDir = new File(rootDir, "secondDir");
        rootDir.mkdir();
        firstDir.mkdir();
        secondDir.mkdir();
        File rootFile = new File(rootDir, "rootFile.txt");
        File firstFile = new File(firstDir, "firstFile.txt");
        File secondFile = new File(secondDir, "rootFile.txt");
        rootFile.createNewFile();
        firstFile.createNewFile();
        secondFile.createNewFile();

        FileWriter writer = new FileWriter(rootFile);
        writer.write("Test file");
        writer.flush();
        writer.close();
        this.rootDir = rootDir;
    }

    /**
     * Тест вывода содежимого директории.
     * @throws IOException IOException.
     */
    @Test
    public void whenUseShowThenGetTestDir() throws IOException {
        StringBuilder expected = new StringBuilder();
        expected.append(String.format("---DIR--- firstDir"));
        expected.append(SEPARATOR);
        expected.append(String.format("--FILE-- rootFile.txt"));
        expected.append(SEPARATOR);
        expected.append(String.format("---DIR--- secondDir"));
        expected.append(SEPARATOR);
        Socket socket = Mockito.mock(Socket.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        Manager manager = new Manager(this.rootDir, socket);
        manager.show();
        assertThat(byteArrayOutputStream.toString().substring(2), is(expected.toString()));
    }

    /**
     * Тест перехода в директорию.
     * @throws IOException IOException.
     */
    @Test
    public void whenMoveToDirThenGetDirContent() throws IOException {
        String dirToGo = String.format("%sfirstDir", FILE_SEPARATOR);
        StringBuilder expected = new StringBuilder();
        expected.append(String.format("--FILE-- firstFile.txt"));
        expected.append(SEPARATOR);
        Socket socket = Mockito.mock(Socket.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        Manager manager = new Manager(this.rootDir, socket);
        manager.goToDir(dirToGo);
        assertThat(byteArrayOutputStream.toString().substring(2), is(expected.toString()));
    }

    /**
     * Тест возврата в корневой каталог.
     * @throws IOException IOException.
     */
    @Test
    public void whenMoveFromInnerDirToRootThenGetTestDir() throws IOException {
        String dirToGo = String.format("%sfirstDir", FILE_SEPARATOR);
        StringBuilder expected = new StringBuilder();
        expected.append(String.format("---DIR--- firstDir"));
        expected.append(SEPARATOR);
        expected.append(String.format("--FILE-- rootFile.txt"));
        expected.append(SEPARATOR);
        expected.append(String.format("---DIR--- secondDir"));
        expected.append(SEPARATOR);
        Socket socket = Mockito.mock(Socket.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        Manager manager = new Manager(this.rootDir, socket);
        manager.goToDir(dirToGo);
        byteArrayOutputStream.reset();
        manager.goToRoot();
        assertThat(byteArrayOutputStream.toString().substring(2), is(expected.toString()));
    }

    /**
     * Тест скачивания файла.
     * @throws IOException IOException.
     */
    @Test
    public void whenDownloadFileThenGetExpectedFile() throws IOException {
        String fileName = "DownloadFile.txt";
        byte[] input = {0, 0, 0, 0, 0, 0, 0, 9, 'T', 'e', 's', 't', ' ', 'f', 'i', 'l', 'e'};
        byte size = 9;
        byte[] expected = "Test file".getBytes();
        byte[] result = new byte[size];
        Socket socket = Mockito.mock(Socket.class);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input);
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        Manager manager = new Manager(this.rootDir, socket);
        manager.download(fileName);

        File file = new File(this.rootDir, fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(result);
        fileInputStream.close();

        assertThat(result, is(expected));
        file.delete();
    }

    /**
     * Тест загрузки файла.
     * @throws IOException IOException.
     */
    @Test
    public void whenUploadFileThenGetExpectedFile() throws IOException {
        final String fileName = "rootFile.txt";
        final byte[] expected = {0, 0, 0, 0, 0, 0, 0, 9, 'T', 'e', 's', 't', ' ', 'f', 'i', 'l', 'e'};

        Socket socket = Mockito.mock(Socket.class);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);

        Manager manager = new Manager(this.rootDir, socket);
        manager.upload(fileName);

      assertThat(byteArrayOutputStream.toByteArray(), is(expected));
    }
    /**
     * Тест отправки сообщения.
     * @throws IOException IOException.
     */
    @Test
    public void whenSendMessageThenGetStream() throws IOException {
        final String message = "Testing Send";
        final byte[] expected = ArrayUtils.addAll(new byte[]{0, 12}, message.getBytes());

        Socket socket = Mockito.mock(Socket.class);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Mockito.when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);

        Manager manager = new Manager(this.rootDir, socket);
        manager.sendMessage(message);

        assertThat(byteArrayOutputStream.toByteArray(), is(expected));
    }

    /**
     * Тест получуния сообщения.
     * @throws IOException IOException.
     */
    @Test
    public void whenReadMessageThenSetStream() throws IOException {
        final String expected = "Testing Read\r\n";
        final byte[] input = ArrayUtils.addAll(new byte[]{0, 12}, expected.getBytes());

        Socket socket = Mockito.mock(Socket.class);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input);
        Mockito.when(socket.getInputStream()).thenReturn(byteArrayInputStream);

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.setOut(new PrintStream(result));

        Manager manager = new Manager(this.rootDir, socket);
        manager.readMessage();

        assertThat(result.toString(), is(expected));
    }
}