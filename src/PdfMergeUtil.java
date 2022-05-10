import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfMergeUtil {

    /**
     * 合并pdf
     *
     * @param files   需要合并的pdf路径
     * @param newfile 合并成新的文件的路径
     * @return
     */
    public static boolean mergePdfFiles(String[] files, String newfile) {
        boolean retValue = false;
        Document document = null;
        PdfCopy copy = null;
        PdfReader reader = null;
        try {
            document = new Document(new PdfReader(files[0]).getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(newfile));
            document.open();
            for (int i = 0; i < files.length; i++) {
                reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (copy != null) {
                copy.close();
            }
            if (document != null) {
                document.close();
            }
        }
        return retValue;
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        File root = new File(dir + "\\input");
        String[] files = root.list();
        Map<String, List<String>> map = new HashMap<>();
        for (String file : files) {
            if (!file.endsWith(".pdf")) {
                continue;
            }
            String prefix = file.substring(0, 24);
            List<String> names = map.get(prefix);
            if (null == names) {
                names = new ArrayList<>();
            }
            names.add(dir + "\\input\\" + file);
            map.put(prefix, names);
        }
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            String[] fileList = list.toArray(new String[list.size()]);
            String savepath = dir + "\\output\\" + key + "ALL.pdf";
            boolean b = mergePdfFiles(fileList, savepath);
        }
        System.out.println("OK");

    }
}
