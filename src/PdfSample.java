import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * add some words to pdf file
 * e.g:http://blog.csdn.net/zmx729618/article/details/52150123
 * 通过用户输入的制图者与审核者的名字填写pdf表格内容
 */
public class PdfSample {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ztName = null;
        String shName = null;
        String type = null;
        String x1 = null;
        String x2 = null;
        String y = null;
        String font1 = null;
        System.out.println("Enter cartographer name :");
        ztName = br.readLine();
        System.out.println("Enter inspector name :");
        shName = br.readLine();
        System.out.println("Choose size type (default 0):");
        System.out.println("0: (x1:51, x2:321, y:28.5),font:10.5");
        System.out.println("1: (x1:130, x2:899, y:911.5),font:28.5");
        System.out.println("2: (x1:182, x2:1270, y:99.5),font:39");
        System.out.println("3: Customize type.(pdf measuring proportion ：1 inch = 72 point)");
        type = br.readLine();
        if (type.equals("3")) {
            System.out.println("Enter x1 :");
            x1 = br.readLine();
            if (x1.equals("")) {
                throw new IllegalArgumentException("x1 can't be null.");
            }
            System.out.println("Enter x2 :");
            x2 = br.readLine();
            if (x2.equals("")) {
                throw new IllegalArgumentException("x2 can't be null.");
            }
            System.out.println("Enter y :");
            y = br.readLine();
            if (y.equals("")) {
                throw new IllegalArgumentException("y can't be null.");
            }
            System.out.println("Enter font :");
            font1 = br.readLine();
            if (font1.equals("")) {
                throw new IllegalArgumentException("font can't be null.");
            }
        }
//        ztName = "六点多";
//        shName = "六点多";
//        type= "1";
        digui("d:/input", ztName, shName, type, x1, x2, y, font1);
    }

    private static void digui(String path1, String ztName, String shName, String type, String
        x1, String x2, String y, String font1) throws IOException, DocumentException {

        File root = new File(path1);  //这里写上发替换的文件夹路径,注意使用双斜杠
        String[] files = root.list();
        for (String file : files) {
            File f1 = new File(path1 + "//" + file);//注意,这里一定要写成File(fl,file)如果写成File(file)是行不通的,一定要全路径
            if (f1.isFile()) {
                addText(f1, ztName, shName, type, x1, x2, y, font1);
            }
            if (f1.isDirectory()) {
                new File("d:/output/" + file).mkdir();
                digui(f1.getAbsolutePath(), ztName, shName, type, x1, x2, y, font1);
            }
        }
    }

    private static void addText(File file, String ztName, String shName, String type, String
        x1, String x2, String y, String font1) throws IOException, DocumentException {
        //创建一个pdf读入流
        PdfReader reader = new PdfReader(file.getAbsolutePath());
        //根据一个pdfreader创建一个pdfStamper.用来生成新的pdf.
        PdfStamper stamper = new PdfStamper(reader,
            new FileOutputStream("d:/output/" + file.getAbsolutePath().split("input\\\\")[1]));

        //这个字体是itext-asian.jar中自带的 所以不用考虑操作系统环境问题.
        BaseFont bf = BaseFont.createFont("STSong-Light",
            "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // set font
        //baseFont不支持字体样式设定.但是font字体要求操作系统支持此字体会带来移植问题.
        Font font = new Font(bf, 10.5f);
        font.setStyle(Font.BOLD);
        font.getBaseFont();
        //页数是从1开始的
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {

            //获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上.
            PdfContentByte over = stamper.getOverContent(i);
            //用pdfreader获得当前页字典对象.包含了该页的一些数据.比如该页的坐标轴信息.
            PdfDictionary p = reader.getPageN(i);
            //拿到mediaBox 里面放着该页pdf的大小信息.
            PdfObject po = p.get(new PdfName("MediaBox"));
            //System.out.println(po.isArray());
            //po是一个数组对象.里面包含了该页pdf的坐标轴范围.
            PdfArray pa = (PdfArray) po;
            //System.out.println(pa.size());
            //看看y轴的最大值.
            //System.out.println("x:"+pa.getAsNumber(pa.size()-2)+",y:"+pa.getAsNumber(pa.size()-1));
            //开始写入文本
            over.beginText();
            //设置字体和大小
            over.setFontAndSize(font.getBaseFont(), 10.5f);
            //设置字体颜色
            over.setColorFill(BaseColor.BLACK);

            //设置字体的输出位置及内容
            if (ztName.length() != 0) {
                if ("".equals(type)) {
                    over.setTextMatrix(51, 28.5f);
                } else if (type.equals("1")) {
                    over.setFontAndSize(font.getBaseFont(), 28.5f);
                    over.setTextMatrix(130, 911.5f);
                } else if (type.equals("2")) {
                    over.setFontAndSize(font.getBaseFont(), 39f);
                    over.setTextMatrix(182, 99.5f);
                } else {
                    over.setFontAndSize(font.getBaseFont(), Float.valueOf(font1));
                    over.setTextMatrix(Float.valueOf(x1), Float.valueOf(y));
                }
                over.showText(ztName);

            }
            if (shName.length() != 0) {
                if ("".equals(type)) {
                    over.setTextMatrix(321, 28.5f);
                } else if (type.equals("1")) {
                    over.setFontAndSize(font.getBaseFont(), 28.5f);
                    over.setTextMatrix(899, 911.5f);
                } else if (type.equals("2")) {
                    over.setFontAndSize(font.getBaseFont(), 39f);
                    over.setTextMatrix(1270, 99.5f);
                } else {
                    over.setFontAndSize(font.getBaseFont(), Float.valueOf(font1));
                    over.setTextMatrix(Float.valueOf(x2), Float.valueOf(y));
                }
                over.showText(shName);
            }
            over.endText();
        }

        stamper.close();
    }
}
