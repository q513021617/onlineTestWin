package controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.MsgBean;
import model.User;
import util.FilePropertise;
import util.HttpUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;

public class UserController {

    HttpUtil httpUtil=new HttpUtil();
    FilePropertise filePropertise=new FilePropertise();
    public boolean userlogin(String username,String password){
        try {
            username=java.net.URLEncoder.encode(username, "UTF-8");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        String resjson=httpUtil.sendGet(HttpUtil.serverUrl+"userlogin/"+username+"/"+password,"utf8");

        System.out.println(resjson);
        ObjectMapper mapper = new ObjectMapper();
        //解析器支持解析单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//解析器支持解析结束符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        try {

            MsgBean msgBean;
            msgBean = mapper.readValue(resjson, MsgBean.class);
            System.out.println(msgBean.getData());

            User user=httpUtil.injectBean(User.class,(HashMap)msgBean.getData());


            filePropertise.writeFile(user,"user.properties");

            if(msgBean.isStatus()){
                return true;
            }
            return false;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String args[]){

        UserController userController=new UserController();
        userController.userlogin("xiaohuang","123456");
    }

}
