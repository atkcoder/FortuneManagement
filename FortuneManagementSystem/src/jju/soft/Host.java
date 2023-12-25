package jju.soft;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Host {
    public Host() throws InterruptedException, IOException, ClassNotFoundException {
        File dir = new File("src\\jju\\soft\\info");

        Date date = new Date();
        int day = date.getDay();
        while (true){
            date = new Date();
            if (day != date.getDay()){
                System.out.println("day:"+day+"  data:"+date.getDay());
                day = date.getDay();
                for (String file: dir.list()){

                    int dotIndex = file.lastIndexOf('.');
                    ObjectIO io = new ObjectIO(new userData());
                    userData userdata = io.ObjectInputStream_(file.substring(0,dotIndex));
                    userdata.setUserName(file.substring(0,dotIndex));
                    io = new ObjectIO(userdata);

                    userdata.lbwFortune.setAmount(Math.round(userdata.lbwFortune.getAmount()*(1+userdata.lbwFortune.getInterest(((day-2)+7)%7))*100.0)/100.0);
                    userdata.tljFortune.setAmount(Math.round(userdata.tljFortune.getAmount()*(1+userdata.tljFortune.getInterest(((day-2)+7)%7))*100.0)/100.0);
                    userdata.au.setAmount(Math.round(userdata.au.getAmount()*(1+userdata.au.getInterest(((day-2)+7)%7))*100.0)/100.0);
                    userdata.ag.setAmount(Math.round(userdata.ag.getAmount()*(1+userdata.ag.getInterest(((day-2)+7)%7))*100.0)/100.0);
                    userdata.cu.setAmount(Math.round(userdata.cu.getAmount()*(1+userdata.cu.getInterest(((day-2)+7)%7))*100.0)/100.0);
                    userdata.al.setAmount(Math.round(userdata.al.getAmount()*(1+userdata.al.getInterest(((day-2)+7)%7))*100.0)/100.0);
                    io.ObjectOutputStream_();
                }
            }
            Thread.sleep(1000);
        }

    }
}
