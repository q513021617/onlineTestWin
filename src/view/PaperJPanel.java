package view;

import controller.TestingController;
import controller.TestingPaperController;
import model.PaperItem;
import model.TestItem;
import model.Testing;
import model.Testpaper;
import util.FilePropertise;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class PaperJPanel extends JPanel {

    JLabel titleLabel;
    JComboBox jComboBox;
    FilePropertise filePropertise=new FilePropertise();
    TestingPaperController testingPaperController=new TestingPaperController();
    List<Testpaper> testpaperList=new ArrayList<>();
    public PaperJPanel(JButton jButton){
        jComboBox=new JComboBox();
        titleLabel=new JLabel("在线考试系统",JLabel.CENTER);


        String testid=filePropertise.readFile("app.properties","testingId");
        testpaperList=testingPaperController.getTestingPaperList(testid);

        for(Testpaper testpaper:testpaperList){
            jComboBox.addItem(new PaperItem(testpaper.getSubject(),testpaper.getPapername()));
        }
        jComboBox.addActionListener(new PaperActionListener());

        this.setLayout(new GridLayout(3,1,50,120));
        this.add(titleLabel);
        this.add(jComboBox);
        jButton.setBackground(new Color(28,167,45));
        this.add(jButton);
    }



    class PaperActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PaperItem paperItem=(PaperItem)jComboBox.getSelectedItem();
            System.out.println(paperItem.getSubject()+" "+paperItem.getPapername());
            filePropertise.writeFile("subject",paperItem.getSubject(),"app.properties");
            for(Testpaper testpaper:testpaperList){
                if(testpaper.getPapername().equals(paperItem.getPapername())){
                    filePropertise.writeFile("time",testpaper.getTotaltime(),"time.properties");
                }
            }
        }

    }

}
