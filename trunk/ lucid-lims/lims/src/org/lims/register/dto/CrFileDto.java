/**
 * 
 */
package org.lims.register.dto;

import java.io.InputStream;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class CrFileDto {
	
	private InputStream crFile;
	private String fileExt;
	
	
	/**
	 * @return the crFile
	 */
	public InputStream getCrFile() {
		return crFile;
	}
	/**
	 * @param crFile the crFile to set
	 */
	public void setCrFile(InputStream crFile) {
		this.crFile = crFile;
	}
	/**
	 * @return the fileExt
	 */
	public String getFileExt() {
		return fileExt;
	}
	/**
	 * @param fileExt the fileExt to set
	 */
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
}
