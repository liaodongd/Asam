import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by WIN on 12-17 0017.
 */
public class ChangFileName {

    private static String path = System.getProperty("user.dir");

    public static void main(String args[]) {
        path = path + "//temp";
        System.out.println(path);
        digui(path);
//        String str = "t_DOM3098-510.tif.aux";
//        if (str.contains("t_DOM")) {
//            str = str.replace("t_DOM", "");
//            str = str.substring(0, str.indexOf(".")) + ".0." + str.substring(str.indexOf(".") + 1, str.length());
//
//        }

    }

    private static void digui(String path1) {
        File root = new File(path1);  //这里写上发替换的文件夹路径,注意使用双斜杠
        String[] files = root.list();
        for (String file : files) {
            File f1 = new File(path1 + "//" + file);//注意,这里一定要写成File(fl,file)如果写成File(file)是行不通的,一定要全路径
            if (f1.isFile()&& f1.getName().contains(".0.")) {
                String str = f1.getName();
                str = str.substring(0, str.indexOf(".0.")+2) + "DOM" + str.substring(str.indexOf(".0.")+2, str.length());
                f1.renameTo(new File(f1.getParent() + "//" + str));
            }
            if (f1.isFile() && f1.getName().contains("t_DOM")) {
                String str = f1.getName().replace("t_DOM", "");
                str = str.substring(0, str.indexOf(".")) + ".0DOM" + str.substring(str.indexOf("."), str.length());
                str = str.substring(0, str.indexOf("-")) + ".0" + str.substring(str.indexOf("-"), str.length());
                f1.renameTo(new File(f1.getParent() + "//" + str));
            }
            if (f1.isDirectory()) {
                digui(f1.getAbsolutePath());
            }
        }
    }


}