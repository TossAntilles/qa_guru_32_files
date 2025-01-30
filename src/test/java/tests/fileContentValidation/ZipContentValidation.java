package tests.fileContentValidation;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipContentValidation {

    private ClassLoader cl = ZipContentValidation.class.getClassLoader();


    @Test
    @Tag("Normal")
    @DisplayName("Проверяем совпадение csv в архиве - сравниваем с ожидаемыми значениями")
    void csvFromZipCompareFiles() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("randomFiles.zip")
        )){
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().endsWith(".csv")){

                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> csvData = csvReader.readAll();

                    Assertions.assertEquals(4, csvData.size());
                    Assertions.assertArrayEquals(
                            new String[] {"John","Doe","1138"},
                            csvData.get(0));
                    Assertions.assertArrayEquals(
                            new String[] {"Alex","Row","1887"},
                            csvData.get(1));
                    Assertions.assertArrayEquals(
                            new String[] {"Lelouch","vi Britannia","2010"},
                            csvData.get(2));
                    Assertions.assertArrayEquals(
                            new String[] {"Vash","Stampede","3211"},
                            csvData.get(3));

                }
            }
        }

    }


    @Test
    @Tag("Normal")
    @DisplayName("Проверяем совпадение csv в архиве - сравниваем с файлом в ресурсах")
    void csvFromZipCompareFilesWithLocalCsv() throws Exception {

        try (InputStream is1 = cl.getResourceAsStream("randomFiles.zip");
            ZipInputStream zis = new ZipInputStream(is1);
            InputStream is2 = cl.getResourceAsStream("expected/characters.csv")
        ){
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().endsWith(".csv")){

                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    CSVReader csvReaderExpected = new CSVReader(new InputStreamReader(is2));
                    List<String[]> csvData = csvReader.readAll();
                    List<String[]> csvDataExpected = csvReaderExpected.readAll();

                    Assertions.assertArrayEquals(csvData.get(0), csvDataExpected.get(0));
                    Assertions.assertArrayEquals(csvData.get(1), csvDataExpected.get(1));
                    Assertions.assertArrayEquals(csvData.get(2), csvDataExpected.get(2));
                    Assertions.assertArrayEquals(csvData.get(3), csvDataExpected.get(3));




                }
            }
        }

    }


    @Test
    @Tag("Normal")
    @DisplayName("Проверяем совпадение pdf в архиве - сравниваем с ожидаемыми значениями")
    void pdfFromZipCompareFiles() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("randomFiles.zip")
        )){
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().endsWith(".pdf")){

                    PDF pdfReader = new PDF(zis);
                    Assertions.assertTrue(pdfReader.text.contains("Кошка, которая...."));
                    Assertions.assertTrue(pdfReader.text.contains("18 ноября 2023, 11:00"));
                }
            }
        }

    }


    @Test
    @Tag("Normal")
    @DisplayName("Проверяем совпадение xlsx в архиве - сравниваем с ожидаемыми значениями")
    void xsltFromZipCompareFiles() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("randomFiles.zip")
        )){
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().endsWith(".xlsx")){
                    XLS xlsTest = new XLS(zis);
                    Assertions.assertTrue(xlsTest.excel.getSheetAt(2).getRow(2).getCell(0).getStringCellValue().contains("DEUS Jasmine Pear 100гр МР"));
                    Assertions.assertTrue(xlsTest.excel.getSheetAt(2).getRow(3).getCell(0).getStringCellValue().contains("FAKE Chistaya Liniya 100 г МРК"));
                    Assertions.assertTrue(xlsTest.excel.getSheetAt(2).getRow(4).getCell(0).getStringCellValue().contains("MustHave Cinnamon Roll 125гр МРК"));
                }
            }
        }

    }

}
