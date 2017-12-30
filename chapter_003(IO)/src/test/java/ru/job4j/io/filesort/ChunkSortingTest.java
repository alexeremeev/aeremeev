package ru.job4j.io.filesort;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тесты класса ChunkSorting.
 */
public class ChunkSortingTest {
    /**
     * Тест сортировки.
     */
    @Test
    public void whenSortViaComparatorThenGetSortedArray() {
        long[][] original = {{2, 2342}, {8, 3345}, {1, 451}, {6, 4656}, {4, 55}, {9, 3}, {8, 228}, {0, 0}, {0, 0}};
        long[][] expected = {{0, 0}, {0, 0}, {1, 451}, {2, 2342}, {4, 55}, {6, 4656}, {8, 3345}, {8, 228}, {9, 3}};
        new ChunkSorting().comparatorSort(original);

        assertThat(original, is(expected));
    }

    /**
     * Тест записи массива в файл.
     * @throws IOException IOE.
     */
    @Test
    public void whenWriteArrayToFileThenGetFile() throws IOException {

        final long[][] actual = {{1, 451}, {2, 321}, {0, 0}, {5, 661}, {0, 0}, {0, 0}};
        final String result = "1 451\r\n2 321\r\n5 661\r\n";

        final String pathToFile = System.getProperty("java.io.tmpdir") + "/temp.tmp";
        File file = new File(pathToFile);
        new ChunkSorting().writeChunkMappingToFile(actual, file);

        StringBuilder builder = new StringBuilder();
        FileReader fileReader = new FileReader(file);
        int readByte;
        while ((readByte = fileReader.read()) != -1) {
            builder.append((char) readByte);
        }
        fileReader.close();

        Assert.assertThat(builder.toString(), Is.is(result));

        file.delete();
    }

    /**
     * Тест слияния двух массивов.
     * @throws IOException IOE.
     */
    @Test
    public void whenMergeTwoArraysThenGetMergedArray() throws IOException {

        final String pathToFirstFile = System.getProperty("java.io.tmpdir") + "/FirstTestFile.tmp";
        File firstFile = new File(pathToFirstFile);
        final String pathToSecondFile = System.getProperty("java.io.tmpdir") + "/SecondTestFile.tmp";
        File secondFile = new File(pathToSecondFile);

        final String firstArray = "1 43\r\n17 6432\r\n32 7653\r\n46 9801\r\n73 2312";
        final String secondArray = "6 34\r\n29 333\r\n33 101\r\n46 9906\r\n71 2243\r\n85 33\r\n96 114\r\n96 224";

        final String resultArray = "1 43\r\n6 34\r\n17 6432\r\n29 333\r\n32 7653\r\n33 101\r\n46 9801\r\n46 9906\r\n71 2243\r\n73 2312\r\n85 33\r\n96 114\r\n96 224\r\n";

        FileWriter firstFileWriter = new FileWriter(pathToFirstFile);
        firstFileWriter.write(firstArray);
        firstFileWriter.flush();
        firstFileWriter.close();

        FileWriter secondFileWriter = new FileWriter(pathToSecondFile);
        secondFileWriter.write(secondArray);
        secondFileWriter.flush();
        secondFileWriter.close();

        final String pathResultFile = System.getProperty("java.io.tmpdir") + "/ResultTestFile.tmp";
        File resultFile = new File(pathResultFile);
        new ChunkSorting().mergeFiles(firstFile, secondFile, resultFile);

        StringBuilder builder = new StringBuilder();
        FileReader fileReader = new FileReader(resultFile);
        int readByte;
        while ((readByte = fileReader.read()) != -1) {
            builder.append((char) readByte);
        }
        fileReader.close();

        Assert.assertThat(builder.toString(), Is.is(resultArray));

        firstFile.delete();
        secondFile.delete();
        resultFile.delete();
    }

    /**
     * Тест слияния пяти массивов.
     * @throws IOException IOE.
     */
    @Test
    public void whenMergeManyArraysThenGetMergedSortedArray() throws IOException {
        
        int size = 5;
        File[] files = new File[size];
        int index = 0;
        
        files[index++] = new File(System.getProperty("java.io.tmpdir") + "/tmp0.tmp");
        files[index++] = new File(System.getProperty("java.io.tmpdir") + "/tmp1.tmp");
        files[index++] = new File(System.getProperty("java.io.tmpdir") + "/tmp2.tmp");
        files[index++] = new File(System.getProperty("java.io.tmpdir") + "/tmp3.tmp");
        files[index++] = new File(System.getProperty("java.io.tmpdir") + "/tmp4.tmp");
        
        index = 0;
        FileWriter firstFileWriter = new FileWriter(files[index++]);
        firstFileWriter.write("8 441\r\n9 661\r\n43 751\r\n64 321\r\n71 765\r\n79 554\r\n83 4138\r\n84 68");
        firstFileWriter.flush();
        firstFileWriter.close();

        FileWriter secondFileWriter = new FileWriter(files[index++]);
        secondFileWriter.write("9 353\r\n9 9562\r\n44 3341\r\n59 7423\r\n75 1260\r\n79 3321\r\n81 3344\r\n91 881");
        secondFileWriter.flush();
        secondFileWriter.close();

        FileWriter thirdFileWriter = new FileWriter(files[index++]);
        thirdFileWriter.write("3 3433\r\n9 123\r\n12 778\r\n32 4535\r\n56 1320\r\n57 3334\r\n76 541\r\n79 9887");
        thirdFileWriter.flush();
        thirdFileWriter.close();

        FileWriter fourthFileWriter = new FileWriter(files[index++]);
        fourthFileWriter.write("3 3322\r\n3 2333\r\n3 5455\r\n9 1233\r\n12 3331\r\n53 753\r\n65 1651\r\n77 1261");
        fourthFileWriter.flush();
        fourthFileWriter.close();


        FileWriter fifthFileWriter = new FileWriter(files[index++]);
        fifthFileWriter.write("1 3231\r\n17 4324\r\n43 132\r\n47 22\r\n83 1113");
        fifthFileWriter.flush();
        fifthFileWriter.close();
        File resultFile = new ChunkSorting().mergeArrayOfFiles(files);

        StringBuilder builder = new StringBuilder();
        FileReader fileReader = new FileReader(resultFile);
        int readByte;
        while ((readByte = fileReader.read()) != -1) {
            builder.append((char) readByte);
        }
        fileReader.close();

        String expected = "1 3231\r\n3 3433\r\n3 3322\r\n3 2333\r\n3 5455\r\n8 441\r\n9 661\r\n9 123\r\n"
               + "9 353\r\n9 1233\r\n9 9562\r\n12 778\r\n12 3331\r\n17 4324\r\n32 4535\r\n43 751\r\n43 132\r\n44 3341\r\n"
               + "47 22\r\n53 753\r\n56 1320\r\n57 3334\r\n59 7423\r\n64 321\r\n65 1651\r\n71 765\r\n75 1260\r\n76 541\r\n"
               + "77 1261\r\n79 554\r\n79 9887\r\n79 3321\r\n81 3344\r\n83 4138\r\n83 1113\r\n84 68\r\n91 881\r\n";
        Assert.assertThat(builder.toString(), is(expected));

        resultFile.delete();
    }
}