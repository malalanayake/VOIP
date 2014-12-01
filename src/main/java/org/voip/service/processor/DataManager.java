package org.voip.service.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

/**
 * This is going to manage the data processing strategy
 * 
 * @author malalanayake
 *
 */
@Service
public class DataManager implements BeanFactoryAware{
	private BeanFactory beanFactory;
	public boolean executeDataProcessor(DataProcessor dataProcessor){
		dataProcessor.wireBeans(beanFactory);
		return dataProcessor.process();
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory =beanFactory;
		
	}
	
}
