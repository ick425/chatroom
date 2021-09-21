package thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class TestCallAble implements Callable<String> {
    private final String url;
    private final String fileName;

    TestCallAble(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public String call() throws Exception {
        TestThread2.WebDownloader downloader = new TestThread2.WebDownloader(url, fileName);
        System.out.println("下载文件，命名为：" + fileName);
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallAble t1 = new TestCallAble("https://pics6.baidu.com/feed/4afbfbedab64034fef0aeb20995875380b551d70.jpeg?token=38168c04eefced415ff2c848a991b602", "f1.jpg");
        TestCallAble t2 = new TestCallAble("https://pics4.baidu.com/feed/78310a55b319ebc41acb4fbcb5bdc3f51f171683.jpeg?token=59e83d67f59adcd41b60899652c78061", "f2.jpg");
        TestCallAble t3 = new TestCallAble("https://pics0.baidu.com/feed/9d82d158ccbf6c81052afb3f8ba5bd3c32fa4068.jpeg?token=fb84dc736aa8dcc207f9f49d6e80b4cd", "f3.jpg");

        // 创建执行服务
        ExecutorService es = Executors.newFixedThreadPool(3);

        // 提交执行
        Future<String> submit1 = es.submit(t1);
        Future<String> submit2 = es.submit(t2);
        Future<String> submit3 = es.submit(t3);

        // 获取结果
        String s1 = submit1.get();
        String s2 = submit2.get();
        String s3 = submit3.get();

        // 打印结果
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        // 关闭服务
        es.shutdownNow();
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
