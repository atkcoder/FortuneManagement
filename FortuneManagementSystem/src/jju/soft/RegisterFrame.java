package jju.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame {
    private JFrame registerFrame;
    private JTextField userName;
    private JPasswordField userPwd;
    private JPasswordField rePwd;
    public RegisterFrame(){
        registerFrame = new JFrame("用户注册");
        registerFrame.setBounds(400,100,500,500);
        registerFrame.setLayout(new GridLayout(8,1));

        JPanel Name = new JPanel();
        JPanel Pwd = new JPanel();

        JLabel name = new JLabel("用      户      名：");
        userName = new JTextField(20);
        Name.add(name);
        Name.add(userName);

        JLabel pwd = new JLabel("密              码：");
        JLabel repwdLable = new JLabel("重复你的密码：");
        rePwd = new JPasswordField(20);
        JPanel rePanel = new JPanel();
        rePanel.add(repwdLable);
        rePanel.add(rePwd);

        userPwd = new JPasswordField(20);
        Pwd.add(pwd);
        Pwd.add(userPwd);

        JLabel title = new JLabel("           请输入你要注册的用户名和密码：");

        Panel titlePanel = new Panel();
        titlePanel.setBackground(new Color(252, 250, 237));
        titlePanel.add(title);

        registerFrame.add(new Panel());
        registerFrame.add(titlePanel);
        registerFrame.add(Name);
        registerFrame.add(Pwd);
        registerFrame.add(rePanel);


        registerFrame.getContentPane().setBackground(new Color(252, 250, 237));


        registerFrame.setVisible(true);
    }

    public void action(){
        JButton ensureButton = new JButton("确认");

        ensureButton.setBackground(new Color(212, 212, 212));

        JPanel ensurePanel = new JPanel(new GridLayout(1,5));

        ensurePanel.add(new JPanel());
        ensurePanel.add(new JPanel());
        ensurePanel.add(ensureButton);
        ensurePanel.add(new JPanel());
        ensurePanel.add(new JPanel());
        registerFrame.add(ensurePanel);
        registerFrame.add(new Panel());

        //对 确认 事件进行监视
        ensureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un = userName.getText();
                String up = new String(userPwd.getPassword());
                String reup = new String(rePwd.getPassword());

                if(!up.equals(reup)){
                    JOptionPane.showMessageDialog(registerFrame,"两次密码不一致，请重新注册！","密码错误",JOptionPane.WARNING_MESSAGE);
                } else {
                    userData userdata = new userData();
                    userdata.setUserName(un);
                    userdata.setUserPwd(up);
                    ObjectIO io = new ObjectIO(userdata);
                    userData check = null;
                    try {
                        check = io.ObjectInputStream_(userdata.getUserName());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                    if(check == null){
                        try {
                            io.ObjectOutputStream_();
                            registerFrame.dispose();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(registerFrame,"用户已存在，请重新注册！","用户名错误",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }
}
