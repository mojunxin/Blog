package org.blog.utils;

import org.blog.model.IpAction;
import org.blog.service.IpActionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    public String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public String getDate(String date) {
//        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获得一个去掉"-"符号的UUID
     *
     * @return
     */
    public String getUuid() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    public void deletePic(HttpSession session, String pic) {
        String realPath = session.getServletContext().getRealPath("");
        File oldPicPath = new File(realPath + pic);
        oldPicPath.delete();
    }

    public static String getYesToDayDateStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1);
        Date start = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(start);
    }

    public static List<IpAction> readFileUtils(String path) {
        List<IpAction> ipActions = new ArrayList<>();
        try {
            java.io.File txtFile = new java.io.File(path);
            FileInputStream fis = new FileInputStream(txtFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String lineTxt = null;
            //82.221.105.6 - - [24/Dec/2019:01:03:27 +0800] "GET /robots.txt HTTP/1.1" 404 1084
            while ((lineTxt = br.readLine()) != null) {
                String[] split = lineTxt.split("\\s+");
                String ip = split[0];
                String action = split[6];
                String code = split[split.length - 2];
                IpAction ipAction = new IpAction();
                ipAction.setId(String.valueOf(UUID.randomUUID()));
                ipAction.setIp(ip);
                ipAction.setAction(action);
                ipAction.setCode(code);
                ipAction.setCreateDate(getYesToDayDateStr());
                String place = new AddressUtils().getPlace(ip);
                Thread.sleep(334);
                if (place == null || place == "") {
                    place = "暂无法解析";
                }
                ipAction.setPlace(place);
                if (!action.equals("\\")) {
                    if (action.equals("null") || action.equals("400") || action == null || action.equals("")) {
                        action = "/n";
                        ipAction.setAction(action);
                    } else {
                        action = action.length() > 250 ? action.substring(0, 249) : action;
                        ipAction.setAction(action);
                    }
                    ipActions.add(ipAction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ipActions;
    }

    public static String getLinuxDate(String createDate) {
        String[] split = createDate.split(":");
        String linuxDate = getYesToDayDateStr() + " " + split[1] + ":" + split[2] + ":" + split[3];
        return linuxDate;
    }


}
