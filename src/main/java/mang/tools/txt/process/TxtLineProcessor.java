package mang.tools.txt.process;

import mang.tools.txt.linehandle.LineHandler;

public interface TxtLineProcessor {

	/**
	 * 行转换
	 * 
	 * */
	public String processLine(String line);
	
	
	/**
	 * 添加处理器
	 * */
	public TxtLineProcessor addHandler(LineHandler lineHandler);
	
	
	/**
	 * 处理了多少行
	 * */
	public int getProcessCount();
	
}
