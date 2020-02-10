package view;

import controller.TestingController;
import model.TestItem;
import model.Testing;
import util.FilePropertise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ExamJpannel extends JPanel {

    JLabel titleLabel;
    TestingController testingController=new TestingController();
    JComboBox jComboBox;
    FilePropertise filePropertise=new FilePropertise();
    public ExamJpannel(JButton jButton){
        jComboBox=new JComboBox();
        titleLabel=new JLabel("在线考试系统",JLabel.CENTER);

        List<model.Testing> testingList=testingController.getTestingList();

        for(Testing test:testingList){

            jComboBox.addItem(new TestItem(test.getId(),test.getTestname()));
        }

        jComboBox.addActionListener(new ItemAction());
        this.setLayout(new GridLayout(3,1,50,120));
        this.add(titleLabel);
        this.add(jComboBox);
        jButton.setBackground(new Color(28,167,45));
        this.add(jButton);
    }



    class ItemAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TestItem testItem=(TestItem)jComboBox.getSelectedItem();
            System.out.println(testItem.getTestid()+" "+testItem.getTestingname());
            filePropertise.writeFile("testingId",testItem.getTestid(),"app.properties");
        }

    }



}
