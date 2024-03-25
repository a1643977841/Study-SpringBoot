package com.net.mybatis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2023/12/29 12:17
 */
public class test {

    public static void main(String[] args) throws IOException {
        String inFile = "/Users/ahao/Downloads/testSql/"; // 输入文件路径
        String outFile = "/Users/ahao/Downloads/testout/"; // 输出文件路径
        File file = new File(inFile);
        File[] tempList = file.listFiles();
        if (tempList == null) {
            return;
        }
        List<String> outDatas = new ArrayList<>();
        for (File value : tempList) {
            if (value.isFile()) {
                tranformMethod(outDatas, value.toString(), value.getName());
            }
        }
        BufferedWriter bw = null;
        try {
            if (!outDatas.isEmpty()) {
                bw = new BufferedWriter(new FileWriter(outFile + "pri.sql"));
                for (String s : outDatas) {
                    bw.write(s + "\r\n");
                }
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }

    }

    public static void tranformMethod(List<String> outDatas, String inFile, String fileName) {
        String fix = "\"pk_" + fileName.replace(".sql", "_");
        try (BufferedReader bs = new BufferedReader(new FileReader(inFile))) {
            String line;
            StringBuilder lines = new StringBuilder();
            while ((line = bs.readLine()) != null) {
                // 先转为一行
                if (!line.toUpperCase().startsWith("--")) {
                    lines.append(line);
                }
            }

            String lowLine = getLine(lines, fix);
            outDatas.add(lowLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getLine(StringBuilder lines, String fix) {
        String lowLine = lines.toString();
        int i = lowLine.lastIndexOf(")");
        int i1 = lowLine.lastIndexOf("(");
        if(i != -1 && i1 != -1) {
            String field = lowLine.substring(i1 + 1, i);
            field = field.replace(" ", "").replace("\"", "");
            lowLine = lowLine.replace("xfgl1", "xfgl").replace("\"PRIMARY\"", fix + field + "\"");
        }
        if (!lowLine.endsWith(";")) {
            lowLine = lowLine + ";";
        }
        return lowLine;
    }
}
