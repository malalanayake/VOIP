package org.voip.service;

import java.io.FileInputStream;

/**
 * Interface which is used to process the data
 * 
 * @author malalanayake
 *
 */
public interface ProcessData {
	public boolean process(FileInputStream inputStream);
}
