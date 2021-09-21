import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 合成图片
 */
public class CompoundImg {

    /**
     * 像素宽度
     */
    private static int IMG_WIDTH_PX = 430;

    /**
     * 像素高度
     */
    private static int IMG_HEIGHT_PX = 430;


    public static void main(String[] args) {
        createImage();
    }


    public static File createImage() {
        // 图标
        String icon = "C:\\Users\\Administrator\\Desktop\\2.png";
        // 二维码
        String qrcode_img = "C:\\Users\\Administrator\\Desktop\\1.png";
        // 输出路径
        String output = "C:\\Users\\Administrator\\Desktop";

        return overlapImage(icon, qrcode_img, output);
    }

    /**
     * @param iconPath   图标路径
     * @param qrCodePath 二维码路径
     * @param outPutPath 输出路径
     * @return
     */
    public static File overlapImage(String iconPath, String qrCodePath, String outPutPath) {
        try {
            BufferedImage qrCode = resizeImage(IMG_WIDTH_PX, IMG_HEIGHT_PX, ImageIO.read(new File(qrCodePath)));
            BufferedImage icon = resizeImage(IMG_WIDTH_PX / 2, IMG_HEIGHT_PX / 2, ImageIO.read(new File(iconPath)));
            icon = convertCircular(icon);
            Graphics2D g = qrCode.createGraphics();
            //在背景图片上添加二维码图片
            // 左上角坐标(0, 0)
            g.drawImage(icon,
                    qrCode.getWidth() / 2 - icon.getWidth() / 2,
                    qrCode.getHeight() / 2 - icon.getHeight() / 2,
                    icon.getWidth(),
                    icon.getHeight(),
                    null);
            g.dispose();
            File out = new File(outPutPath + "/" + System.currentTimeMillis() + ".png");
            ImageIO.write(qrCode, "png", out);
            return out;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }

    /**
     * 将正方形设置为圆形
     *
     * @param bi1 正方形
     * @return 圆形
     */
    public static BufferedImage convertCircular(BufferedImage bi1) {
        // 透明底的图片
        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
        Graphics2D g2 = bi2.createGraphics();
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1, 0, 0, null);
        // 设置颜色
        g2.setBackground(Color.green);
        g2.dispose();

        return bi2;
    }
}