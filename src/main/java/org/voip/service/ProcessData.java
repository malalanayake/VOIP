package org.voip.service;

import java.io.InputStream;

/**
 * Interface which is used to process the data
 * 
 * @author malalanayake
 *
 */
public interface ProcessData {
	public boolean process(InputStream inputStream);
}
