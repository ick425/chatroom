package com.wangcl.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 淘宝抢单
 *
 * @author wo
 */
public class Spike {
    /**
     * 商品全选标签XPath
     */
    private static final String XPATH = "//*[@id=\"J_Order_s_2214235872491_1\"]/div[1]/div/div/label";

    /**
     * 秒杀时间
     */
    private static final String DATE = "2022-08-26 10:00:00 000000000";

    /**
     * 驱动路径
     */
    private static final String DRIVER_PATH = "C:\\Users\\wo\\Desktop\\chromedriver.exe";


    public static void main(String[] args) {
        try {
            taoBao();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void taoBao() throws Exception {

        //浏览器驱动路径
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

        //设置秒杀时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSSSSSS");
        Date date = sdf.parse(DATE);

        //1、打开浏览器
        ChromeDriver browser = new ChromeDriver();
        Actions actions = new Actions(browser);
        //2、输入网址
        browser.get("https://www.taobao.com");
        Thread.sleep(2000);

        //3、点击登录
        browser.findElement(By.linkText("亲，请登录")).click();

        Thread.sleep(2000);

        //4、扫码登录
        browser.findElement(By.className("icon-qrcode")).click();
        Thread.sleep(4000);

        //5、进入购物车页面
        browser.get("https://cart.taobao.com/cart.htm");
        Thread.sleep(3000);

        //6、点击选择第一个按钮
        browser.findElement(By.xpath(XPATH)).click();

        Thread.sleep(2000);
        while (true) {
            //当前时间
            Date now = new Date();
            System.out.println(now);
            if (now.after(date)) {
                if (browser.findElement(By.linkText("结 算")).isEnabled()) {
                    browser.findElement(By.linkText("结 算")).click();
                    // 500ms之后点击提交订单
                    Thread.sleep(300);
                    while (browser.findElement(By.linkText("提交订单")).isEnabled()) {
                        browser.findElement(By.linkText("提交订单")).click();
                        System.out.println("结算成功");
                        break;
                    }
                }

            }
        }
    }
}
