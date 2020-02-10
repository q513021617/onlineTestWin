package view;
import controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private JLabel usernameLable;
    private JLabel userpasswordLabel;
    private JTextField username;
    private JPasswordField userpassword;
    private JPanel loginPanl;
    private JLabel title;

    private UserController userController=new UserController();

    public LoginFrame(){
        super("考试系统登录");
        this.loginButton = new JButton("登录");

        this.username=new JTextField(16);
        this.userpassword=new JPasswordField(16);
        this.usernameLable=new JLabel("用户名:");
        this.userpasswordLabel=new JLabel("密码:");
        this.title=new JLabel("考试系统登录",SwingConstants.CENTER);
        this.title.setSize(200,200);
        this.setLayout(new BorderLayout(20,50));
        this.add(this.title,BorderLayout.NORTH);
        JPanel tempJpanel=new JPanel();
        tempJpanel.setSize(250,50);
        this.add(tempJpanel,BorderLayout.EAST);
        JPanel tempJpanel1=new JPanel();
        tempJpanel1.setSize(250,50);
        this.add(tempJpanel1,BorderLayout.WEST);
        LoginFrame loginFrame=this;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.userlogin();
            }
        });
        loginPanl=new JPanel();
        loginPanl.setLayout(new GridLayout(3,2,2,50));
        loginPanl.add(this.usernameLable);
        loginPanl.add(this.username);
        loginPanl.add(this.userpasswordLabel);
        loginPanl.add(this.userpassword);
        this.loginButton.setBackground(new Color(28,167,45));
        loginPanl.add(this.loginButton);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(loginPanl);
        this.setSize(500,350);
        this.setVisible(true);
    }

    public void userlogin(){

        String usernmaestr=this.username.getText();
        String password=this.userpassword.getText();

        if(!userController.userlogin(usernmaestr,password)){
            JOptionPane.showMessageDialog(null, "登录失败!用户名或密码错误","提示信息", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        new ExamFrame();
        this.dispose();
    }

}
