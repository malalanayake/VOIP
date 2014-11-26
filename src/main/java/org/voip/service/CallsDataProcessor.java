package org.voip.service;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

/**
 * Process calls data
 * 
 * @author malalanayake
 *
 */
@Service
public class CallsDataProcessor implements ProcessData {

	@Override
	public boolean process(FileInputStream inputStream) {
		return false;
	}

			
}
