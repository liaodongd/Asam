import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ParseJson {
	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
		File file = new File("conf");
		String[] paths = file.list();
		String abstractpath = file.getAbsolutePath();

		File fileOut = new File("output");
		String[] outPaths = file.list();
		String outAbstractpath = fileOut.getAbsolutePath();
		for (String p : outPaths) {
			String path = outAbstractpath + "\\" + p;
			File tempFile = new File(path);
			if(tempFile.exists())
				tempFile.delete();
		}
		test2(paths,abstractpath);
	}

//	private void test1(String[] paths,String abstractpath) throws IOException {
//
//		for (String fileName : paths) {
//			String fullPath = abstractpath + "\\" + fileName;
//			String JsonContext = new ReadFile().ReadFile(fullPath);
//			String[] temp = JsonContext.split("\n");
//			StringBuilder sb = new StringBuilder();
//			sb.append("Polygon\r\n");
//			sb.append("1 0\r\n");
//			for (int i = 0; i < temp.length; i++) {
//				sb.append(i + " ");
//				sb.append(temp[i].replace(",", " "));
//				sb.append(" 1.#QNAN 1.#QNAN");
//				sb.append("\r\n");
//			}
//			sb.append("END");
//
//			FileWriter fw = new FileWriter("output\\" + fileName, true);
//			PrintWriter pw = new PrintWriter(fw);
//			pw.print(sb);
//			pw.flush();
//		}
//	}

	private static void test2(String[] paths,String abstractpath) throws IOException {

		for (String fileName : paths) {
			String fullPath = abstractpath + "\\" + fileName;
			String JsonContext = new ReadFile().ReadFile(fullPath);
			String[] temp = JsonContext.split("\n");
			StringBuilder sb = new StringBuilder();
			sb.append("id,,x,y,z\r\n");
			for (int i = 0; i < temp.length; i++) {
				sb.append(i+1 + ",,");
				sb.append(temp[i].replace(" ", ","));
				if (temp[i].split(" ").length < 3) {
					sb.append(",0");
				}
				sb.append("\r\n");
			}
			FileWriter fw = new FileWriter("output\\" + fileName, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(sb);
			pw.flush();
		}
	}
}
