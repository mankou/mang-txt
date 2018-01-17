package mang.tools.txt.process;

import java.io.File;
import mang.tools.txt.reader.SimpleTxtReader;
import mang.tools.txt.reader.TxtReader;
import mang.tools.txt.writer.SimpleTxtEncodeWriter;
import mang.tools.txt.writer.TxtWriter;

public class AbstractTxtProcessor implements TxtFileProcessor {

	/**
	 * 读取文件时编码
	 */
	private String readCharset = "UTF-8";

	/**
	 * 写文件时的编码
	 */
	private String writeCharset = "UTF-8";

	/**
	 * lineProcess
	 */
	private TxtLineProcessor lineHandleProcessor=new SimpleLineHandleProcessor();

	/**
	 * lineFilter
	 */
	private TxtLineFilterProcessor lineFilterProcessor=new AllTxtLineFilterProcessor();

	/**
	 * 源文件路径
	 */
	private String sourceFilePath;

	/**
	 * 目标文件路径
	 */
	private String targetFilePath;

	/**
	 * 源文件
	 */
	private File sourceFile;

	/**
	 * 目标文件
	 */
	private File targetFile;

	/**
	 * txtWriter 用它可以获取写了多少行等信息
	 */
	private TxtWriter txtWriter;
	
	
	/**
	 * 处理txt过程中的上下文信息
	 * */
	private TxtProcessContext context=new TxtProcessContext();
	
	/**
	 * txtReader
	 */
	private TxtReader txtReader;

	private void init(String sourceFilePath, String targetFilePath) {
		this.sourceFilePath = sourceFilePath;
		this.targetFilePath = targetFilePath;
		sourceFile = new File(sourceFilePath);
		targetFile = new File(targetFilePath);

		if (txtWriter == null) {
			txtWriter = new SimpleTxtEncodeWriter(targetFile, writeCharset);
		}

		if (txtReader == null) {
			txtReader = new SimpleTxtReader(sourceFile, readCharset);
		}
	}

	/**
	 * 处理入口
	 * */
	public void processSingleFile(String sourceFilePath, String targetFilePath) {
		init(sourceFilePath, targetFilePath);
		
		lineFilterProcessor.setContext(context);

		while (txtReader.hasNext()) {
			String currentLine = txtReader.readLine();
			
			this.context.addLine();
			
			// true 就是要,false就是不要
			if (!lineFilterProcessor.beforeFilter(currentLine)) {
				continue;
			}

			String processLine = null;
			processLine = lineHandleProcessor.processLine(currentLine);

			if (!lineFilterProcessor.afterFilter(processLine)) {
				continue;
			}
			txtWriter.writeLine(processLine);
		}
		txtReader.close();
		txtWriter.close();

		after();
	}

	public void after() {

	}
	
	
	public void setUserData(String key,Object value){
		this.context.putUserData(key, value);
	}
	
	public Object getUserData(String key){
		return this.context.getUserData(key);
	}
	
	public String getReadCharset() {
		return readCharset;
	}

	public void setReadCharset(String readCharset) {
		this.readCharset = readCharset;
	}

	public String getWriteCharset() {
		return writeCharset;
	}

	public void setWriteCharset(String writeCharset) {
		this.writeCharset = writeCharset;
	}

	public TxtLineProcessor getLineHandleProcessor() {
		return lineHandleProcessor;
	}

	public void setLineHandleProcessor(TxtLineProcessor lineHandleProcessor) {
		this.lineHandleProcessor = lineHandleProcessor;
	}

	public TxtLineFilterProcessor getLineFilterProcessor() {
		return lineFilterProcessor;
	}

	public void setLineFilterProcessor(TxtLineFilterProcessor lineFilterProcessor) {
		this.lineFilterProcessor = lineFilterProcessor;
	}

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public String getTargetFilePath() {
		return targetFilePath;
	}

	public void setTargetFilePath(String targetFilePath) {
		this.targetFilePath = targetFilePath;
	}

	public TxtWriter getTxtWriter() {
		return txtWriter;
	}

	public void setTxtWriter(TxtWriter txtWriter) {
		this.txtWriter = txtWriter;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public File getTargetFile() {
		return targetFile;
	}

	public void setTargetFile(File targetFile) {
		this.targetFile = targetFile;
	}

	public TxtReader getTxtReader() {
		return txtReader;
	}

	public void setTxtReader(TxtReader txtReader) {
		this.txtReader = txtReader;
	}
	
	

}
