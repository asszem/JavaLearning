package FilesAndDirectories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadingAPropertiesFile {

    Properties properties = new Properties();
    InputStream inputStream = null;
    String propFilePath="propertyReader.properties"; //File stored in project root folder

    private void loadProperties() {
        try{
           inputStream = new FileInputStream(propFilePath);
           properties.load(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String readProperty(String key){
        return properties.getProperty(key);
    }

	public static void main(String[] args) {
		ReadingAPropertiesFile instance = new ReadingAPropertiesFile();
		instance.loadProperties();
		System.out.println(instance.properties.toString());
		
		System.out.println("URL = " +instance.readProperty("URL"));
	}

}
