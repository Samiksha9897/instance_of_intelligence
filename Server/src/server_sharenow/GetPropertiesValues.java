package server_sharenow;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetPropertiesValues {
    static Properties properties;
    public static void getPropValues() throws IOException{
        FileReader fileReader;
        properties=new Properties();
        String propFileName = "config.properties";
        fileReader=new FileReader(propFileName);
        if (fileReader!=null){
            try {
                properties.load(fileReader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            throw new FileNotFoundException("File Not Found");
    }
    public static String getUserName(){
        try {
            getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String user= properties.getProperty("user");
        return user;
    }
    protected static String getMysqlPassword(){
        try {
            getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String password= properties.getProperty("password");
        return password;
    }
    public static String getAdminUserName(){
        try {
            getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String adminUserName=properties.getProperty("adminuser");
        return adminUserName;
    }
    public static String getAdminPassword(){
        try {
            getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String adminPassword=properties.getProperty("adminpass");
        return adminPassword;
    }
}
