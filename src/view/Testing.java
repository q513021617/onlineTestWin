package view;

import model.Topic;
import util.FilePropertise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class Testing extends JPanel {

    JLabel titleLabel;
    JScrollPane centerJpanel;
    JLabel jLabel;
    List<JRadioButton> buttonArrayList=new ArrayList<JRadioButton>();
    String[] topiclist=new String[buttonArrayList.size()];
    FilePropertise filePropertise=new FilePropertise();
    public Testing(JButton jButton, List<Topic> topicList,String subject){

        titleLabel=new JLabel("编译原理期末考试",JLabel.CENTER);


        JPanel centerPannel=new JPanel();
        centerPannel.setSize(250,250);
        centerPannel.setLayout(new GridLayout(topicList.size(),1));

        for(int index=0;index<topicList.size();index++){
            JPanel temJpanel=new JPanel();
            temJpanel.setLayout(new BorderLayout());
            JLabel descrip=new JLabel((index+1)+","+topicList.get(index).getDescription());
            ButtonGroup tembuttonGroup=new ButtonGroup();
            JRadioButton jrb1=new JRadioButton("A,"+topicList.get(index).getAnsItemsA());
            jrb1.addActionListener(new RadioButtonGroupActionListener());
            JRadioButton jrb2=new JRadioButton("B,"+topicList.get(index).getAnsItemsB());
            jrb2.addActionListener(new RadioButtonGroupActionListener());
            JRadioButton jrb3=new JRadioButton("C,"+topicList.get(index).getAnsItemsC());
            jrb3.addActionListener(new RadioButtonGroupActionListener());
            JRadioButton jrb4=new JRadioButton("D,"+topicList.get(index).getAnsItemsD());
            jrb4.addActionListener(new RadioButtonGroupActionListener());

            JPanel temJpane2=new JPanel();
            temJpane2.setLayout(new GridLayout(4,1));
            temJpane2.add(jrb1);
            temJpane2.add(jrb2);
            temJpane2.add(jrb3);
            temJpane2.add(jrb4);

            buttonArrayList.add(jrb1);
            buttonArrayList.add(jrb2);
            buttonArrayList.add(jrb3);
            buttonArrayList.add(jrb4);

            tembuttonGroup.add(jrb1);
            tembuttonGroup.add(jrb2);
            tembuttonGroup.add(jrb3);
            tembuttonGroup.add(jrb4);

            temJpanel.add(descrip,BorderLayout.NORTH);
            temJpanel.add(temJpane2,BorderLayout.CENTER);
            centerPannel.add(temJpanel);

        }


        centerJpanel=new JScrollPane(centerPannel);

        this.setLayout(new BorderLayout());
        this.add(titleLabel,BorderLayout.NORTH);
        JPanel west=new JPanel();
        String timestr="30分00秒";
        jLabel=new JLabel("剩余时间:"+timestr);
        jLabel.setFont(new Font("宋体",Font.PLAIN,25));
        jLabel.setForeground(new Color(255,0,0));
        west.setSize(100,300);
        west.add(jLabel);
        this.add(west,BorderLayout.WEST);
        this.add(centerJpanel,BorderLayout.CENTER);
        JPanel east=new JPanel();
        east.setSize(100,300);
        this.add(east,BorderLayout.EAST);

        jButton.setBackground(new Color(28,167,45));
        this.add(jButton,BorderLayout.SOUTH);
        new Timer().start();
    }

    class RadioButtonGroupActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int num=0,selectitem=0;

            for (int i=0;i<buttonArrayList.size();i++) {

                if(buttonArrayList.get(i).isSelected()){
                  num=i/4;
                  selectitem=i%4;
                }


            }
            String str="";
            switch (selectitem){
                case 0:str="A";break;
                case 1:str="B";break;
                case 2:str="C";break;
                case 3:str="D";break;
            }


//            System.out.println("题号："+(num+1)+" 选项: "+str);
            filePropertise.writeFile(String.valueOf((num+1)),str,"topic"+String.valueOf((num+1))+".properties");


        }
    }

    class Timer extends Thread{

        @Override
        public void run() {
            int min=Integer.parseInt(filePropertise.readFile("time.properties","time"));
            long time=min*60;
            while(true){
                //当前时间
                //目标时间距离当前时间差的秒
                if(time<=0){
                    System.out.println("到时间");
                    break;
                }

                //根据time算出天，时，分，秒

                int mins=(int) (time/60);
                int second=(int) (time%60);

                jLabel.setText(mins+"分"+second+"秒");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time=time-1;
            }

        }
    }


}
