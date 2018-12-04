package fifth;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerateCsv {
    
	public PrintWriter pw;
	public StringBuilder sb;
	
	public GenerateCsv(String sFileName) throws IOException {
		pw = new PrintWriter(new File("src\\fifth\\" + sFileName));
	    sb = new StringBuilder();
		
	    sb.append("get");
	    sb.append(",");
	    sb.append("class");
	    sb.append(",");
	    sb.append("nThreads");
	    sb.append(",");
	    sb.append("totalTime");
	    sb.append("\n");
	    
	    pw.write(sb.toString());
	}
	
	public void addLine(String get, String classObj, String nThreads, String totalTime) throws IOException {
		sb = new StringBuilder();
		
		sb.append(get);
	    sb.append(",");
		sb.append(classObj);
		sb.append(",");
		sb.append(nThreads);
		sb.append(",");
		sb.append(totalTime);
		sb.append("\n");
		
		pw.write(sb.toString());
	}
	
	public void closeCsv() throws IOException {
		pw.flush();
		pw.close();
	}
	
}
