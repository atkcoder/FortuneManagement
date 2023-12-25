package jju.soft;

import java.io.Serializable;


public class userData extends Product {
    private String userName, userPwd;
    private double money, totalAdd, totalGet;

    Product lbwFortune, tljFortune, au, ag, cu, al;

    public userData(){
        super();
        money = 0;

        lbwFortune = new Product("卢本伟基金",new double[]{0.021, 0.031, -0.057, 0.011, 0.033, -0.055, -0.038});
        tljFortune = new Product("谭立俊基金",new double[]{0.031, 0.044, -0.007, 0.021, 0.035, 0.055, -0.008});
        au = new Product("金",new double[]{0.011, 0.001, -0.007, 0.021, 0.013, -0.015, 0.008});
        ag = new Product("银",new double[]{0.013, 0.012, 0.022, -0.021, -0.003, 0.005, -0.008});
        cu = new Product("铜",new double[]{0.031, 0.011, -0.027, 0.021, 0.011, 0.005, -0.040});
        al = new Product("铝",new double[]{0.013, 0.021, -0.037, -0.021, -0.023, -0.025, 0.022});
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public boolean ensurePwd(String userPwd){
        return this.userPwd.equals(userPwd);
    }

    public String toString(){
        return "usName:"+userName+"  usPwd:"+userPwd;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getTotalAdd() {
        return totalAdd;
    }

    public void setTotalAdd(double totalAdd) {
        this.totalAdd = totalAdd;
    }
}

class Product implements Serializable {
    Product(){
    }

    public Product(String s, double[] ist ){
        setName(s);
        setAmount(0);
        setInterest(ist);
    }
    private String name;
    private double amount;
    private double[] interest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterest(int n) {
        return interest[n];
    }

    public void setInterest(double[] interest) {
        this.interest = interest;
    }


}