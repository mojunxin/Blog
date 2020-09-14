package org.blog.utils;

import org.blog.model.IpAction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class LinuxFileService implements Runnable{
    private ScheduledExecutorService scheduledExecutorService;
    private Map<String,ScheduledFuture> futureMap;

    public LinuxFileService() {
        scheduledExecutorService = Executors.newScheduledThreadPool(Integer.MAX_VALUE);
        futureMap = new HashMap<>();
    }

    @Override
    public void run() {
//        String path = "D:\\localhost_access_log.2020-01-09.txt";
        String path = "/usr/local/tomcat/logs/localhost_access_log."+Utils.getYesToDayDateStr()+".txt";
        List<IpAction> ipActions = Utils.readFileUtils(path);
        int i = this.saveIpActionToMysql(ipActions);
        System.out.println(i);
    }

    public int saveIpActionToMysql(List<IpAction> ipActions) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://111.229.42.116:3306/blog?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "Mjx1101_";
        Connection conn = null;
        Statement stat = null;
        int i = 0;
        String sql = "";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stat = conn.createStatement();
            sql = "insert into ip_action(id,ip,action,code,create_date,place) values ";
            int a = 0;
            for (IpAction ipAction : ipActions) {
                if (!ipAction.getAction().equals("/")) {
                    if (a == 0) {
                        sql += "('" + ipAction.getId() + "','" + ipAction.getIp() + "','" + ipAction.getAction().replace("'","") + "','" + ipAction.getCode() + "','"
                                + ipAction.getCreateDate() + "','" + ipAction.getPlace() + "')";
                    } else {
                        sql += ",('" + ipAction.getId() + "','" + ipAction.getIp() + "','" + ipAction.getAction().replace("'","") + "','" + ipAction.getCode() + "','"
                                + ipAction.getCreateDate() + "','" + ipAction.getPlace() + "')";
                    }
                    a++;
                }
            }
            sql += ";";
            System.out.println("sql=====>>>>>  "+sql);
            i = stat.executeUpdate(sql);
            stat.close();
            conn.close();
        } catch (Exception e) {
            try {
                saveSqlToFile(sql);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return i;
    }
    // 增加或减少天数
    public Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }

    public void startTig() {
        String invokeTime = "00:00:00";
        String[] invokeTimeSections = invokeTime.split(":");
        if(null == invokeTimeSections || invokeTimeSections.length != 3){
            return;
        }
        int ss = Integer.parseInt(invokeTimeSections[2]);
        int mm = Integer.parseInt(invokeTimeSections[1]);
        int hh = Integer.parseInt(invokeTimeSections[0]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hh);
        calendar.set(Calendar.MINUTE,mm);
        calendar.set(Calendar.SECOND,ss);
        Date date = calendar.getTime();

        long millisecond = date.getTime() - (new Date()).getTime();
        if(millisecond < 0){
            date = this.addDay(date,1);
            millisecond = date.getTime() - (new Date()).getTime();
        }
        long sec = millisecond / 1000;
        System.out.println("定时执行处理文件剩余====>>>>>"+sec+" 秒");
        //启动定时任务前先停掉之前的
        ScheduledFuture fileLinux = futureMap.get("fileLinux");
        ScheduledFuture future = fileLinux;
        if (future != null) {
            future.cancel(false);
        }
        //进行新的定时任务
        ScheduledFuture<?> scheduledFuture = this.scheduledExecutorService.scheduleAtFixedRate(new LinuxFileService(), sec, 3600 * 24, TimeUnit.SECONDS);
        this.futureMap.put("fileLinux",scheduledFuture);
    }

    public static void saveSqlToFile(String txt) throws IOException {
        File file =new File("/usr/local/tomcat/logs/failSqlFile.txt\n");
        Writer out =new FileWriter(file);
        out.write(txt);
        out.close();
    }
}
