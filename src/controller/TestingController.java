package controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.MsgBean;
import model.Testing;
import model.User;
import util.FilePropertise;
import util.HttpUtil;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestingController {
    HttpUtil httpUtil=new HttpUtil();
    FilePropertise filePropertise=new FilePropertise();

    public List getTestingList(){
        String resjson=httpUtil.sendGet(HttpUtil.serverUrl+"testingpage/","utf8");

        ObjectMapper mapper = new ObjectMapper();
        //解析器支持解析单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//解析器支持解析结束符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        try {

            MsgBean msgBean;
            msgBean = mapper.readValue(resjson, MsgBean.class);
            System.out.println(msgBean.getData());
            List<Testing> testingList=new ArrayList<Testing>();
            List<HashMap> datalist=new ArrayList<HashMap>();
            datalist=(List<HashMap>)msgBean.getData();

            if(datalist.size()<0){
                System.out.println("getTestingList 获取到数据为0");
                return new ArrayList<Testing>();
            }
            for(HashMap hashMap:datalist){
                Testing testing=new Testing();
                testing.setId((String) hashMap.get("id"));
                testing.setTestname((String) hashMap.get("testname"));
                testing.setStarttime(String.valueOf(hashMap.get("starttime")) );
                testing.setEndtime(String.valueOf(hashMap.get("endtime")));
                testing.setTestpaperid((String) hashMap.get("testpaperid"));
                testing.setType((Integer) hashMap.get("type"));
                testingList.add(testing);
            }


            return testingList;
        }catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList();
        }


    }



    public static void main(String args[]){

        TestingController testingController=new TestingController();
       List<Testing> testingList=testingController.getTestingList();
        System.out.println();
    }

}
