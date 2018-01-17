package mang.tools.txt.process;


public interface TxtFileProcessor {
	
	public void processSingleFile(String sourceFilePath,String targetFilePath);
	
	
	public void after();
}
