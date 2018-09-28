package com.manheim.kmeans;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Run {
    public static void main(String[] args) throws Exception {
        setHadoopHomeEnvironmentVariable();
        new KMeansUI();
    }

    private static void setHadoopHomeEnvironmentVariable() throws Exception {
    	 HashMap<String, String> hadoopEnvSetUp = new HashMap<>();
    	try
    	{
       
        hadoopEnvSetUp.put("HADOOP_HOME", new File("winutils-master/hadoop-2.8.1").getAbsolutePath());
        Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
        Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
        theEnvironmentField.setAccessible(true);
        Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
        env.clear();
        env.putAll(hadoopEnvSetUp);
        Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
        theCaseInsensitiveEnvironmentField.setAccessible(true);
        Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
        cienv.clear();
        cienv.putAll(hadoopEnvSetUp);
    	
    	 } catch (NoSuchFieldException e) {
    		    Class[] classes = Collections.class.getDeclaredClasses();
    		    Map<String, String> env = System.getenv();
    		    for(Class cl : classes) {
    		      if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
    		        Field field = cl.getDeclaredField("m");
    		        field.setAccessible(true);
    		        Object obj = field.get(env);
    		        Map<String, String> map = (Map<String, String>) obj;
    		        map.clear();
    		        map.putAll(hadoopEnvSetUp);
    		      }
    		    }
    		  }
    	}
        
        
    
}
