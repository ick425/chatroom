package io;

import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 读取指定路径下的文件，递归读取文件夹
 *
 * @author wcl
 */
public class TestReadFile {
    /**
     * 本地文件夹地址
     */
    private static final String LOCAL_FILE_PATH = "C:\\Users\\Administrator\\Desktop\\PDF缴纳地";

    public static void main(String[] args) {
        readFile();
    }

    public static void readFile() {
        File file = new File(LOCAL_FILE_PATH);
        List<String> pathList = new ArrayList<>();
        getFile(file, pathList);
        if (CollectionUtils.isNotEmpty(pathList)) {
            // 共有1408个pdf
            System.out.println(pathList.size());
            System.out.println(pathList.get(0));
        }
    }

    /**
     * 获取指定路径下的文件
     *
     * @param file     文件
     * @param pathList 本地路径集合
     * @return 本地路径集合
     */
    private static void getFile(File file, List<String> pathList) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (Objects.nonNull(files)) {
                for (File file1 : files) {
                    getFile(file1, pathList);
                }
            }
        } else if (file.isFile()) {
            String path1 = file.getPath();
            pathList.add(path1);
        }
    }
}
