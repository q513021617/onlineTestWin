package controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.MsgBean;
import model.Testing;
import model.Testpaper;
import util.FilePropertise;
import util.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestingPaperController {
    HttpUtil httpUtil=new HttpUtil();
    FilePropertise filePropertise=new FilePropertise();

    public List getTestingPaperList(String id){
        String resjson=httpUtil.sendGet(HttpUtil.serverUrl+"testingTestingPaper/"+id,"utf8");

        ObjectMapper mapper = new ObjectMapper();
        //解析器支持解析单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//解析器支持解析结束符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        try {
            MsgBean msgBean;
            msgBean = mapper.readValue(resjson, MsgBean.class);
            System.out.println(msgBean.getData());
            List<Testpaper> testingPapers=new ArrayList<Testpaper>();
            List<HashMap> datalist=new ArrayList<HashMap>();
            datalist=(List<HashMap>)msgBean.getData();
            if(datalist.size()<0){
                System.out.println("TestingPaperController 获取到数据为0");
                return new ArrayList<Testing>();
            }
            for(HashMap hashMap:datalist){
                Testpaper testingPaper=new Testpaper();
                testingPaper.setId((String) hashMap.get("id"));
                testingPaper.setPapername((String) hashMap.get("papername"));
                testingPaper.setSubject(String.valueOf(hashMap.get("subject")) );
                testingPaper.setTotaltime(String.valueOf(hashMap.get("totaltime")));

                testingPapers.add(testingPaper);
            }


            return testingPapers;
        }catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList();
        }


    }



    public static void main(String args[]){

        TestingPaperController testingPaper=new TestingPaperController();
        List<Testpaper> testingList=testingPaper.getTestingPaperList("7ed3ce79d9094d85b31fa7748bc2218a");
        System.out.println();
    }
}
