package jju.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

class loginBuff {
    private JFrame Loginframe;
    private JTextField userName;
    private JPasswordField userPwd;

    /*
    *一个带有JFrame的构造方法
     */

    public loginBuff(JFrame Loginframe){
        this.Loginframe = Loginframe;
    }

    /*
    *设计框架的基本信息
     */
    protected void frameBuff(){
        Loginframe.setBounds(450,150,500,500);
        Loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Loginframe.setLayout(new GridLayout(8,1));

    }

   /*
   *菜单条 （分为两部分）：
   * 1. 关于我们，我的一些个人信息，以及我的个人网站，Github
   * 2. 支持我们，向我们捐款
   *
    */
    protected void menuBuff(){


        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("更  多");
        JMenuItem aboutUs = new JMenuItem("关于我们");
        JMenu donateUs = new JMenu("支持我们");

        JMenuItem wePay = new JMenuItem("微信支付",new ImageIcon("src\\jju\\soft\\pic\\wepay.png"));
        JMenuItem aliPay = new JMenuItem("支付宝",new ImageIcon("src\\jju\\soft\\pic\\alipay.png"));
        donateUs.add(wePay);
        donateUs.add(aliPay);
        jMenu.add(aboutUs);
        jMenu.add(donateUs);
        jMenuBar.add(jMenu);
        Loginframe.setJMenuBar(jMenuBar);

        //对  关于我们  做事件监视
        aboutUs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFrame abUsFrame = new JFrame("关于我们");
                abUsFrame.setBounds(450,150,500,500);
                abUsFrame.setLayout(new GridLayout(2,2));

                JPanel infoPanel = new JPanel(new GridLayout(5,1));

                infoPanel.add(new JLabel("Made By: 九江学院 计算机与大数据科学学院 谭立俊"));
                infoPanel.add(new JLabel("                  计科A2253 学号：20220207388 44"));
                infoPanel.add(new JLabel("联系我们："));
                infoPanel.add(new JLabel("Email:tanlijun0302@qq.com"));
                infoPanel.add(new JLabel("Q   Q：2837458092"));
                abUsFrame.add(infoPanel);

                JLabel labelOfWeb;
                labelOfWeb = new JLabel("<html><a href=\"\">我的个人网站</a></html>");
                labelOfWeb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                labelOfWeb.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        try {
                            Desktop.getDesktop().browse(new URL("https://rusheet.top").toURI());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                JLabel labelOfGithub;
                labelOfGithub = new JLabel("<html><a href=\"\">我的Github</a></html>");
                labelOfGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                labelOfGithub.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        try {
                            Desktop.getDesktop().browse(new URL("https://github.com/atkcoder").toURI());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                JPanel superLinkPanel = new JPanel(new GridLayout(5,1));
                superLinkPanel.add(labelOfWeb);
                superLinkPanel.add(labelOfGithub);
                abUsFrame.add(superLinkPanel);
                abUsFrame.setVisible(true);
            }
        });

        //对  微信支付  做事件监视
        wePay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame code = new JFrame();
                code.setBounds(450,150,360,490);
                JLabel wepaycode = new JLabel();
                wepaycode.setIcon(new ImageIcon("src\\jju\\soft\\pic\\wepayCode.png"));
                code.add(wepaycode);
                code.setVisible(true);

            }
        });

        //对  支付宝支付  做事件监视
        aliPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame code = new JFrame();
                code.setBounds(450,150,313,487);
                JLabel alipaycode = new JLabel();
                alipaycode.setIcon(new ImageIcon("src\\jju\\soft\\pic\\alipayCode.jpg"));
                code.add(alipaycode);
                code.setVisible(true);
            }
        });
    }

    protected void infoBuff(){

        /*
         *  获取用户登入信息的面板
         */
        JPanel Name = new JPanel();
//        Name.setBackground(new Color(255, 255, 228));
        JPanel Pwd = new JPanel();
//        Pwd.setBackground(new Color(255, 255, 228));

        JLabel name = new JLabel("用    户    名：");
        userName = new JTextField(20);
        Name.add(name);
        Name.add(userName);

        JLabel pwd = new JLabel("密           码：");
        userPwd = new JPasswordField(20);
        Pwd.add(pwd);
        Pwd.add(userPwd);

        Loginframe.add(new Panel());
        Loginframe.add(new Panel());
        Loginframe.add(Name);
        Loginframe.add(Pwd);
        Loginframe.add(new Panel());
    }
    /*
     * 创建两个按钮（登入 和 注册）
     * 并进行监视
     *
    */
    protected void buttonBuff(){

        JButton loginButton = new JButton("登入");

        loginButton.setBackground(new Color(212, 212, 212));

        JPanel loginPanel = new JPanel(new GridLayout(1,5));

        loginPanel.add(new JPanel());
        loginPanel.add(new JPanel());
        loginPanel.add(loginButton);
        loginPanel.add(new JPanel());
        loginPanel.add(new JPanel());
        Loginframe.add(loginPanel);
        Loginframe.add(new Panel());

        //对登入事件进行监视
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un = userName.getText();
                String up = new String(userPwd.getPassword());

                userData userdata = new userData();
                userdata.setUserName(un);

                ObjectIO io = new ObjectIO();//userdata
                userData ud;
                try {
                    ud = io.ObjectInputStream_(un);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                if (ud == null){
                    JOptionPane.showMessageDialog(Loginframe,"用户不存在！","用户名错误",JOptionPane.WARNING_MESSAGE);
                } else{
                    if(!ud.ensurePwd(up)){
                        JOptionPane.showMessageDialog(Loginframe,"密码错误！","密码错误",JOptionPane.WARNING_MESSAGE);
                    } else {
                        Loginframe.dispose();

                        try {
                            fortuneManagement fortune = new fortuneManagement(ud);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }


                    }
                }
            }
        });

        //注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBackground(new Color(212, 212, 212));

        JPanel registerPanel = new JPanel(new GridLayout(1,5));
        registerPanel.add(registerButton);
        registerPanel.add(new Panel());
        registerPanel.add(new Panel());
        registerPanel.add(new Panel());
        registerPanel.add(new Panel());
        Loginframe.add(registerPanel);

        //对注册按钮进行监视
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFrame registerFrame = new RegisterFrame();
                registerFrame.action();
            }
        });


    }


}


public class LoginFrame {
    public void login(){
        JFrame Loginframe = new JFrame("理财管理系统");

        loginBuff buff = new loginBuff(Loginframe);

        buff.menuBuff();
        buff.frameBuff();
        buff.infoBuff();
        buff.buttonBuff();

        Loginframe.setVisible(true);

    }
}
