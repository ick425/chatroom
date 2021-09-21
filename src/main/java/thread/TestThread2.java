package thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 多线程下载文件
 *
 * @author wcl
 */
public class TestThread2 implements Runnable {
    private final String url;
    private final String fileName;

    TestThread2(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        WebDownloader downloader = new WebDownloader(url, fileName);
        System.out.println("下载文件，命名为：" + fileName);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://pics6.baidu.com/feed/4afbfbedab64034fef0aeb20995875380b551d70.jpeg?token=38168c04eefced415ff2c848a991b602", "f1.jpg");
        TestThread2 t2 = new TestThread2("https://pics4.baidu.com/feed/78310a55b319ebc41acb4fbcb5bdc3f51f171683.jpeg?token=59e83d67f59adcd41b60899652c78061", "f2.jpg");
        TestThread2 t3 = new TestThread2("https://pics0.baidu.com/feed/9d82d158ccbf6c81052afb3f8ba5bd3c32fa4068.jpeg?token=fb84dc736aa8dcc207f9f49d6e80b4cd", "f3.jpg");

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }

    /**
     * web文件下载
     */
    static class WebDownloader {

        public WebDownloader(String url, String fileName) {
            try {
                FileUtils.copyURLToFile(new URL(url), new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("下载web图片出错");
            }
        }
    }
}
