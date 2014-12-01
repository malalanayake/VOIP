package org.voip.service.processor;

import org.springframework.beans.factory.BeanFactory;



/**
 * Interface which is used to process the data
 * 
 * @author malalanayake
 *
 */
public interface DataProcessor {
	public void wireBeans(BeanFactory beanFactory);
	public boolean process();
	
}
