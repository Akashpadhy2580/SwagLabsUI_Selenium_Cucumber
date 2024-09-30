package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import StepDefs.BaseTest;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesFileUtility
{
    public static Properties p;
    public static void createPropertiesFile(String pfpath, String[] names,String[] values)
            throws Exception
    {
        Properties p=new Properties();
        for(int i=0;i<names.length; i++)
        {
            p.setProperty(names[i],values[i]);
        }
        File f=new File(pfpath);
        FileWriter fw=new FileWriter(f);
        p.store(fw,"My results Properties file");
    }

    public static String getValueFromPropertiesFile(String propertyname)
            throws Exception
    {
        p= BaseTest.initProperties();
        String value=p.getProperty(propertyname);
        return(value);
    }

    public static Map<String,String> getAllValuesFromPropertiesFile(String pfpath)
            throws Exception
    {
        FileInputStream fi=new FileInputStream(pfpath);
        Properties p=new Properties();
        p.load(fi);
        Set<Object> prs=p.keySet(); //get properties names
        Map<String,String> m=new HashMap<String,String>();
        for(Object pr:prs)
        {
            m.put(pr.toString(), p.getProperty(pr.toString()));
        }
        fi.close();
        return(m);
    }

    public static void updatePropertiesFile(String pfpath, String[] names,String[] values)
            throws Exception
    {
        File f=new File(pfpath);
        PropertiesConfiguration pc=new PropertiesConfiguration(f);
        for(int i=0;i<names.length; i++)
        {
            pc.setProperty(names[i],values[i]);
        }
        pc.save();
    }
}
