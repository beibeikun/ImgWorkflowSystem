package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCsvFile {
    /**
     * 读取 CSV 文件，并将其内容存储到二维数组中
     *
     * @param csvPath CSV 文件的路径
     * @return 存储 CSV 数据的二维数组
     */
    public static String[][] csvToArray(String csvPath) {
        List<String[]> dataList = new ArrayList<>(); // 存储 CSV 数据的动态数组
        int maxColumns = 0; // 最大的列数

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line;
            String cvsSplitBy = ",";

            while ((line = br.readLine()) != null) {
                String[] row = line.split(cvsSplitBy); // 使用逗号作为分隔符拆分行数据
                dataList.add(row); // 将拆分的行数据添加到动态数组中
                maxColumns = Math.max(maxColumns, row.length); // 更新最大列数
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0][0]; // 文件读取出错时，返回空数组
        }

        // 将动态数组转换为二维数组，使用最大列数来填充行数据
        String[][] dataArray = new String[maxColumns][dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            String[] row = dataList.get(i);
            for (int j = 0; j < maxColumns; j++) {
                if (j < row.length) {
                    dataArray[j][i] = row[j];
                } else {
                    dataArray[j][i] = ""; // 对于不满列数的行，用空字符串填充
                }
            }
        }

        return dataArray;
    }
}
