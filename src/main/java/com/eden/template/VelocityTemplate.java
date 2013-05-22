package com.eden.template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VelocityTemplate {
	private static final long serialVersionUID = -5753280431375877990L;
	private static Logger logger = LoggerFactory.getLogger(VelocityTemplate.class);

	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final String DEFAULT_PATH = "template/";

	private VelocityTemplate() {
		Properties p = new Properties();
		p.setProperty("input.encoding", DEFAULT_ENCODING);
		p.setProperty("output.encoding", DEFAULT_ENCODING);
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			Velocity.init(p);
		} catch (Exception e) {
			logger.debug("Velocity init error !");
			e.printStackTrace();
		}
	}

	public String parseVMContent(String templateContent,
			Map<String, Object> contextParameter) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("parse content : [ " + templateContent + " ]");
			logger.debug("context parameters : [ " + contextParameter + " ]");
		}
	    
	    try {
	    	VelocityContext context = new VelocityContext();
	 	    StringWriter writer = new StringWriter();
	 	    for (String key : contextParameter.keySet()) {
	 	    	context.put(key, contextParameter.get(key));
	 	    }
			Velocity.evaluate(context, writer, "fe", templateContent);
			String result = writer.toString();
			
			if (logger.isDebugEnabled()) {
				logger.debug("velocity parse result is : [ " + result + " ]");
			}
				
			return result;
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	public String parseVMTemplate(String templateFileName,
			Map<String, Object> contextParameter) {
		

		if (logger.isDebugEnabled()) {
			logger.debug("parse template file name : [ " + templateFileName + " ]");
			logger.debug("context parameters : [ " + contextParameter + " ]");
		}
		
		try {
			Template template = Velocity.getTemplate(DEFAULT_PATH + templateFileName, DEFAULT_ENCODING);
			VelocityContext context = new VelocityContext();
	 	    for (String key : contextParameter.keySet()) {
	 	    	context.put(key, contextParameter.get(key));
	 	    }
	 	    StringWriter writer = new StringWriter();
	 	    template.merge(context, writer);
	 	    
	 	   String result = writer.toString();
			
			if (logger.isDebugEnabled()) {
				logger.debug("velocity parse result is : [ " + result + " ]");
			}
				
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
