package jju.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


class fortuneBuff{
    Calendar calendar = Calendar.getInstance();
    JFrame fortuneFrame;
    userData userdata;
    JMenuItem money;
    JMenuItem reFreah;

    ObjectIO io;
    public fortuneBuff(userData userdata){
        this.userdata = userdata;
        io = new ObjectIO(userdata);
    }
    public void frameBuff(JFrame frame){
        fortuneFrame = frame;
        fortuneFrame.setBounds(450,150,500,500);
        fortuneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fortuneFrame.setLayout(new GridLayout(1,1));
    }

    public void menuBuff(){
        JMenuBar me = new JMenuBar();
        JMenu menu = new JMenu("我");
        money = new JMenuItem("余额："+userdata.getMoney());

        JMenuItem addMoney = new JMenuItem("充值");
        JMenuItem drawMoney = new JMenuItem("提现");
        reFreah = new JMenuItem("刷新");

        menu.add(money);
        menu.add(addMoney);
        menu.add(drawMoney);
        menu.add(reFreah);
        me.add(menu);


        fortuneFrame.setJMenuBar(me);


        addMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame("充值窗口");
                addFrame.setBounds(450,150,500,500);

                JLabel title = new JLabel("请输入充值金额：");
                JTextField text = new JTextField(9);
                JButton button = new JButton("确定");

                JPanel panel = new JPanel();
                panel.add(title);
                panel.add(text);
                panel.add(button);
                addFrame.add(panel);

                addFrame.setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double get = Double.parseDouble(text.getText());
                        userdata.setMoney(get+userdata.getMoney());
                        io.ObjectOutputStream_();
                        money.setText("余额："+userdata.getMoney());
                    }
                });

            }
        });



//        reFreah.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                money.setText("余额："+userdata.getMoney());
//                fortuneFrame.repaint();
//            }
//        });

        reFreah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fortuneFrame.dispose();

                ObjectIO io = new ObjectIO(userdata);
                try {
                    userdata = io.ObjectInputStream_(userdata.getUserName());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                JFrame fortuneFrame = new JFrame("理财管理系统");
                fortuneBuff buff = new fortuneBuff(userdata);
                buff.frameBuff(fortuneFrame);
                buff.menuBuff();
                buff.purchasePanel();
                fortuneFrame.setVisible(true);
            }
        });

        drawMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame drawFrame = new JFrame("提现窗口");
                drawFrame.setBounds(450,150,500,500);

                JLabel title = new JLabel("请输入提现金额：");
                JTextField text = new JTextField(9);
                JButton button = new JButton("确定");

                JPanel panel = new JPanel();
                panel.add(title);
                panel.add(text);
                panel.add(button);
                drawFrame.add(panel);

                drawFrame.setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double get = Double.parseDouble(text.getText());

                        if (get > userdata.getMoney()){
                            JOptionPane.showMessageDialog(drawFrame,"提现金额超出余额，请重试！","余额不足",JOptionPane.WARNING_MESSAGE);
                        } else{
                            userdata.setMoney(userdata.getMoney()-get);
                            io.ObjectOutputStream_();
                            money.setText("余额："+userdata.getMoney());
                        }
                    }
                });

            }
        });

    }

    public void purchasePanel(){

        JTextArea price = new JTextArea();
        Date date = new Date();

        price.append("*** 昨日利率 *** \n\n");
        price.append(userdata.lbwFortune.getName()+": "+userdata.lbwFortune.getInterest((date.getDate()-2)%7)+"\n");
        price.append(userdata.tljFortune.getName()+": "+userdata.tljFortune.getInterest((date.getDate()-2)%7)+"\n");
        price.append(userdata.au.getName()+": "+userdata.au.getInterest((date.getDate()-2)%7)+"\n");
        price.append(userdata.ag.getName()+": "+userdata.ag.getInterest((date.getDate()-2)%7)+"\n");
        price.append(userdata.cu.getName()+": "+userdata.cu.getInterest((date.getDate()-2)%7)+"\n");
        price.append(userdata.al.getName()+": "+userdata.al.getInterest((date.getDate()-2)%7)+"\n");

        price.setEditable(false);
        JScrollPane pricePane = new JScrollPane(price);


        JPanel purchasePanel = new JPanel(new GridLayout(6,1));
        purchasePanel.add(new productPanel(userdata.lbwFortune.getName(),userdata.lbwFortune.getAmount(),userdata,userdata.lbwFortune,money,fortuneFrame));
        purchasePanel.add(new productPanel(userdata.tljFortune.getName(),userdata.tljFortune.getAmount(),userdata,userdata.tljFortune,money,fortuneFrame));
        purchasePanel.add(new productPanel(userdata.au.getName(),userdata.au.getAmount(),userdata,userdata.au,money,fortuneFrame));
        purchasePanel.add(new productPanel(userdata.ag.getName(),userdata.ag.getAmount(),userdata,userdata.ag,money,fortuneFrame));
        purchasePanel.add(new productPanel(userdata.cu.getName(),userdata.cu.getAmount(),userdata,userdata.cu,money,fortuneFrame));
        purchasePanel.add(new productPanel(userdata.al.getName(),userdata.al.getAmount(),userdata,userdata.al,money,fortuneFrame));

        JScrollPane purchasePane = new JScrollPane(purchasePanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pricePane,purchasePane);
        fortuneFrame.add(splitPane);
    }

}

class productPanel extends JPanel{
    JLabel name, amount, label;
    productPanel(String name, double amounts, userData userdata, Product product,JMenuItem money, JFrame frame){

        setLayout(new GridLayout(4,1));
        JPanel firstPanel = new JPanel();
        JPanel secondPanel = new JPanel();
        JPanel thirdPanel = new JPanel();
        JPanel fourthPanel = new JPanel();

        this.name = new JLabel("产品："+name);
        this.amount = new JLabel("已购入："+amounts+" ￥");
        label = new JLabel("购入：");
        JTextField textField = new JTextField(5);
        JButton button = new JButton("确定");

        firstPanel.add(this.name);
        secondPanel.add(this.amount);
        thirdPanel.add(label);
        thirdPanel.add(textField);
        thirdPanel.add(button);

        JLabel drawLabel = new JLabel("取出：");
        JTextField drawText = new JTextField(5);
        JButton drawButton = new JButton("确定");

        add(firstPanel);
        add(secondPanel);
        add(thirdPanel);

        fourthPanel.add(drawLabel);
        fourthPanel.add(drawText);
        fourthPanel.add(drawButton);
        add(fourthPanel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                double num = Double.parseDouble(textField.getText());

                if (userdata.getMoney() < num){
                    JOptionPane.showMessageDialog(frame,"余额不足，请充值！","余额不足",JOptionPane.WARNING_MESSAGE);
                } else {
                    userdata.setMoney(userdata.getMoney()-num);
                    product.setAmount(product.getAmount()+num);
                    money.setText("余额："+userdata.getMoney());
                    amount.setText("已购入："+product.getAmount()+" ￥");
                    ObjectIO io = new ObjectIO(userdata);
                    io.ObjectOutputStream_();
                }

            }
        });

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double num = Double.parseDouble(drawText.getText());
                if (product.getAmount() < num){
                    JOptionPane.showMessageDialog(frame,"超出限额！","超出限额",JOptionPane.WARNING_MESSAGE);
                } else {
                    userdata.setMoney(userdata.getMoney()+num);
                    product.setAmount(product.getAmount()-num);
                    money.setText("余额："+userdata.getMoney());
                    amount.setText("已购入："+product.getAmount()+" ￥");
                    ObjectIO io = new ObjectIO(userdata);
                    io.ObjectOutputStream_();
                }
            }
        });
    }
}

public class fortuneManagement {
    public fortuneManagement(userData userdata) throws IOException, ClassNotFoundException {
        JFrame fortuneFrame = new JFrame("理财管理系统");
        fortuneBuff buff = new fortuneBuff(userdata);
        buff.frameBuff(fortuneFrame);
        buff.menuBuff();
        buff.purchasePanel();
        fortuneFrame.setVisible(true);

    }
}