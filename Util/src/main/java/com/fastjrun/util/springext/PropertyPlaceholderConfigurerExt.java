package com.fastjrun.util.springext;

import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.fastjrun.helper.DESedeCodec;

public class PropertyPlaceholderConfigurerExt extends
		PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory, Properties props)

	throws BeansException {
		Set<String> propsName = props.stringPropertyNames();
		for (String propName : propsName) {
			if (propName.endsWith(".encrypt")) {
				String propValue_enc = props.getProperty(propName);
				String propName_source = propName.substring(0, 
						propName.indexOf(".encrypt"));
				// 解密并删除密文
				try {
					props.setProperty(propName_source, DESedeCodec.decrypt(
							propValue_enc));
					props.remove(propName);
				} catch (Exception e) {
				}
			}
		}
		super.processProperties(beanFactory, props);

	}

}
