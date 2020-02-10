package controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.MsgBean;
import model.Score;
import model.Testing;
import util.FilePropertise;
import util.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreController {

    HttpUtil httpUtil=new HttpUtil();
    FilePropertise filePropertise=new FilePropertise();

    public Boolean putScore(String score,String subject){
        String userid=filePropertise.readFile("user.properties","userId");

        try {
            subject = java.net.URLEncoder.encode(subject, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String resjson=httpUtil.sendGet(HttpUtil.serverUrl+"testingScore/"+userid+"/"+score+"/"+subject,"utf8");

        ObjectMapper mapper = new ObjectMapper();
        //解析器支持解析单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//解析器支持解析结束符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        try {
            MsgBean msgBean;
            msgBean = mapper.readValue(resjson, MsgBean.class);
            boolean status=(boolean)msgBean.getData();

            return status;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }


    }


    public List getScoreList(){

        String userid=filePropertise.readFile("user.properties","userId");
        String resjson=httpUtil.sendGet(HttpUtil.serverUrl+"testingScoreList/"+userid+"/","utf8");

        ObjectMapper mapper = new ObjectMapper();
        //解析器支持解析单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//解析器支持解析结束符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        try {
            MsgBean msgBean;
            msgBean = mapper.readValue(resjson, MsgBean.class);
            System.out.println(msgBean.getData());
            List<Score> scoreList=new ArrayList<Score>();
            List<HashMap> datalist=new ArrayList<HashMap>();
            datalist=(List<HashMap>)msgBean.getData();
            if(datalist.size()<0){
                System.out.println("TestingPaperController 获取到数据为0");
                return new ArrayList<Testing>();
            }
            for(HashMap hashMap:datalist){
                Score score=new Score();
                score.setId((String) hashMap.get("id"));
                score.setStuid((String) hashMap.get("stuid"));
                score.setSubject(String.valueOf(hashMap.get("subject")) );
                score.setTestscore((Integer) hashMap.get("testscore"));
                score.setTimestr(String.valueOf(hashMap.get("timestr")));

                scoreList.add(score);
            }

            return scoreList;
        }catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList();
        }


    }

    public static void main(String args[]){

        ScoreController scoreController=new ScoreController();

        List<Score> scoreList=scoreController.getScoreList();
        System.out.println(scoreList);
    }

}
