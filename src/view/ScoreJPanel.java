package view;

import model.Score;
import model.Topic;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreJPanel extends JPanel {
    JLabel titleLabel;
    JScrollPane centerJpanel;
    JLabel jLabel;
    public ScoreJPanel(JButton jButton, List<Score> scoreList){

        titleLabel=new JLabel("考生成绩单情况",JLabel.CENTER);


        JPanel centerPannel=new JPanel();
        centerPannel.setSize(250,250);
        centerPannel.setLayout(new GridLayout(scoreList.size(),4));
        Object[] columnNames = {"序列", "所属课程", "考试分数", "考试时间"};
        Object[][] rowData=new Object[scoreList.size()][4];

        for(int index=0;index<scoreList.size();index++){
            Score score=scoreList.get(index);

            rowData[index][0]=score.getId();
            rowData[index][1]=score.getSubject();
            rowData[index][2]=score.getTestscore();
            rowData[index][3]=score.getTimestr();
        }
        JTable table = new JTable(rowData, columnNames);


        centerPannel.add(table);
        centerJpanel=new JScrollPane(centerPannel);

        this.setLayout(new BorderLayout());
        this.add(titleLabel,BorderLayout.NORTH);
        JPanel west=new JPanel();
        west.setSize(100,300);
        this.add(west,BorderLayout.WEST);
        this.add(centerJpanel,BorderLayout.CENTER);
        JPanel east=new JPanel();
        east.setSize(100,300);
        this.add(east,BorderLayout.EAST);

        jButton.setBackground(new Color(28,167,45));
        this.add(jButton,BorderLayout.SOUTH);

    }
}
