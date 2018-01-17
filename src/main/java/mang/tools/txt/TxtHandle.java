package mang.tools.txt;

import mang.tools.txt.linehandle.LineHandler;

public interface TxtHandle {
	public void init();
	
	public boolean hasNext();
	
	public String readLine();
	
	public String processLine(String line,LineHandler lineHandle);
	
	public void finish();
	
}
