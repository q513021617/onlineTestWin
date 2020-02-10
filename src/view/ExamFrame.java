package view;

import controller.ScoreController;
import controller.TestTopicItemController;
import controller.TestingController;
import model.PaperItem;
import model.Score;
import model.TestItem;
import model.Topic;
import util.FilePropertise;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class ExamFrame extends JFrame implements ActionListener {

    private JMenuBar testingMenuBar;
    private JMenu jMenu1;

    private JMenuItem queryScoreListMenuItem;

    private ExamJpannel examJpannel;

    private PaperJPanel paperJPanel;
    private Testing testingJPanel;
    private ScoreJPanel scoreJPanel;
    JComboBox examlist;
    private JButton jButton;
    private JButton startjButton;
    private JButton submitButton;
    private JButton backButton;
    private JComboBox jComboBox=new JComboBox();
    private JComboBox paperjComboBox;
    ExamFrame examFrame;
    private TestingController testingController=new TestingController();
    private TestTopicItemController itemController=new TestTopicItemController();
    private ScoreController scoreController=new ScoreController();
    FilePropertise filePropertise=new FilePropertise();
    List<Topic>  topicList;
    public ExamFrame()
    {
        super("在线考试系统");
        initGUI();
    }

    private void initGUI(){
        examFrame=this;
        examFrame.setSize(500,500);
        testingMenuBar=new JMenuBar();
        jMenu1=new JMenu("主菜单");
        queryScoreListMenuItem=new JMenuItem("查看历史分数列表");
        queryScoreListMenuItem.addActionListener(new MeanuKeyListener());
        jMenu1.add(queryScoreListMenuItem);
        testingMenuBar.add(jMenu1);
        examFrame.add(testingMenuBar,BorderLayout.NORTH);
        jButton=new JButton("进入选择试卷");
        jButton.addActionListener(this);
        examJpannel=new ExamJpannel(jButton);
        JPanel west=new JPanel();
        west.setSize(100,300);
        examFrame.add(west,BorderLayout.WEST);
        examFrame.add(examJpannel,BorderLayout.CENTER);
        JPanel east=new JPanel();
        east.setSize(100,300);
        examFrame.add(east,BorderLayout.EAST);
        examFrame.add(new JPanel(),BorderLayout.SOUTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        examFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        examFrame.remove(examJpannel);
        startjButton=new JButton("开始考试");


        startjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilePropertise filePropertise=new FilePropertise();
                String subject=filePropertise.readFile("app.properties","subject");
                examFrame.startTesting(subject);
            }
        });
        paperJPanel=new PaperJPanel(startjButton);
        examFrame.add(paperJPanel,BorderLayout.CENTER);

        examFrame.revalidate();
    }

    public void startTesting(String subject){
        examFrame.remove(paperJPanel);
        examFrame.remove(startjButton);
        examFrame.setSize(800,500);
        submitButton=new JButton("提交试卷");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String score=examFrame.getTotalScore();
                examFrame.queryScore(subject,score);
            }
        });

        topicList=itemController.getTestTopicItemList(subject);
        testingJPanel=new Testing(submitButton,topicList,subject);
        examFrame.add(testingJPanel,BorderLayout.CENTER);

        examFrame.revalidate();
        System.out.println("调用成功！");
    }

    public String getTotalScore(){
            int count=0;
            int itemscore=100/topicList.size();
        for (int i=0;i<topicList.size();i++) {
            if(topicList.get(i).getAnswer().equals(filePropertise.readFile("topic"+(i+1)+".properties",(i+1)+""))){
                count=count+itemscore;
            }
        }
        return count+"";
    }

    public void queryScore(String subject,String score){
        if(!scoreController.putScore(score,subject)){
            System.out.println("查询分数失败");
        }


        examFrame.remove(testingJPanel);
        examFrame.remove(submitButton);
        examFrame.setSize(800,500);

        backButton=new JButton("返回首页");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                examFrame.backIndex();
            }
        });
        List<Score>  scorelist=new ArrayList<>();
        scorelist= scoreController.getScoreList();

        scoreJPanel=new ScoreJPanel(backButton,scorelist);
        examFrame.add(scoreJPanel,BorderLayout.CENTER);

        examFrame.revalidate();
    }

    public void backIndex(){
        examFrame.remove(scoreJPanel);
        examFrame.setSize(500,500);
        examFrame.add(examJpannel,BorderLayout.CENTER);

        examFrame.revalidate();
    }


    class MeanuKeyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            examFrame.remove(examJpannel);

            examFrame.setSize(800,500);

            backButton=new JButton("返回首页");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    examFrame.backIndex();
                }
            });
            List<Score>  scorelist=new ArrayList<>();
            scorelist= scoreController.getScoreList();

            scoreJPanel=new ScoreJPanel(backButton,scorelist);
            examFrame.add(scoreJPanel,BorderLayout.CENTER);

            examFrame.revalidate();
        }
    }

}
