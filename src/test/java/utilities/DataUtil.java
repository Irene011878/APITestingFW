package utilities;

import org.testng.annotations.DataProvider;
import setUp.BaseTest;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataUtil extends BaseTest {

    @DataProvider(name = "data")
    public Object[][] getData(Method m){

        String sheetName = "testdata";
        int rows = excel.getRowCount(sheetName);

        String testName = m.getName(); // 🔥 nombre del test

        // 🔎 1. Encontrar inicio del test
        int testCaseRowNum = 1;

        for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

            String testCaseName = excel.getCellData(sheetName, 0, testCaseRowNum);

            if (testCaseName.equalsIgnoreCase(testName)) {
                break;
            }
        }

        // 🔎 2. Headers y data
        int colStartRowNum = testCaseRowNum + 1;
        int dataStartRowNum = testCaseRowNum + 2;

        // 🔎 3. Contar filas
        int testRows = 0;

        while (!excel.getCellData(sheetName, 0, dataStartRowNum + testRows).equals("")) {
            testRows++;
        }

        // 🔎 4. Contar columnas
        int testCols = 0;

        while (!excel.getCellData(sheetName, testCols, colStartRowNum).equals("")) {
            testCols++;
        }

        // 🔎 5. Crear data
        Object[][] data = new Object[testRows][1];

        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

            Hashtable<String, String> table = new Hashtable<>();

            for (int cNum = 0; cNum < testCols; cNum++) {

                String key = excel.getCellData(sheetName, cNum, colStartRowNum);
                String value = excel.getCellData(sheetName, cNum, rNum);

                table.put(key, value);
            }

            data[rNum - dataStartRowNum][0] = table;
        }

        return data;
    }
}
