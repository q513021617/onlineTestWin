package util;

import model.User;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class FilePropertise {
    Properties prop = new Properties();
    public String readFile(String filename,String proper){

        String value="";
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            prop.load(in);
            value=(String) prop.get(proper);

           return value;
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }

    }

    public void writeFile(String key,String value,String filename){
        Properties pro = new Properties();
        try {
            FileOutputStream oFile = new FileOutputStream(filename, false);//true表示追加打开
            pro.setProperty(key,value);
            pro.store(oFile,"");
            oFile.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void writeFile(User user, String filename){
        Properties pro = new Properties();
        try {
            FileOutputStream oFile = new FileOutputStream(filename, false);//true表示追加打开
            pro.setProperty("userId",user.getId());
            pro.setProperty("userName",user.getUname());
            pro.setProperty("userPhone",user.getPhone());
            pro.setProperty("userStatus",user.getStatus().toString());
            pro.store(oFile,"userInfo");
            oFile.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


}
