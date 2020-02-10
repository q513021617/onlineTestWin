package controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import util.FilePropertise;
import util.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestTopicItemController {

    HttpUtil httpUtil=new HttpUtil();
    FilePropertise filePropertise=new FilePropertise();

    public List getTestTopicItemList(String subject){

        try {
            subject = java.net.URLEncoder.encode(subject, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String resjson=httpUtil.sendGet(HttpUtil.serverUrl+"testingTopicItem/"+subject,"utf8");

        ObjectMapper mapper = new ObjectMapper();
        //解析器支持解析单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//解析器支持解析结束符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        try {
            MsgBean msgBean;
            msgBean = mapper.readValue(resjson, MsgBean.class);
            System.out.println(msgBean.getData());
            List<Topic> topicItems=new ArrayList<Topic>();
            List<HashMap> datalist=new ArrayList<HashMap>();
            datalist=(List<HashMap>)msgBean.getData();
            if(datalist.size()<0){
                System.out.println("TestingPaperController 获取到数据为0");
                return new ArrayList<Testing>();
            }
            for(HashMap hashMap:datalist){
                Topic topicItem=new Topic();
                topicItem.setId((String) hashMap.get("id"));
                topicItem.setDescription((String) hashMap.get("description"));
                topicItem.setAnswer(String.valueOf(hashMap.get("answer")) );
                topicItem.setAnsItemsA(String.valueOf(hashMap.get("ansItemsA")));
                topicItem.setAnsItemsB(String.valueOf(hashMap.get("ansItemsB")));
                topicItem.setAnsItemsC(String.valueOf(hashMap.get("ansItemsC")));
                topicItem.setAnsItemsD(String.valueOf(hashMap.get("ansItemsD")));
                topicItems.add(topicItem);
            }


            return topicItems;
        }catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList();
        }


    }



    public static void main(String args[]){

        TestTopicItemController topicItemController=new TestTopicItemController();

        List<Topic> topicItems=topicItemController.getTestTopicItemList("编译原理");
        System.out.println();
    }

}
