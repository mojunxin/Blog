package org.blog.utils;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressUtils {

    public String getAddresses(String content, String encodingString) {
        // 这里调用pconline的接口
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = this.getResult(urlStr, content, encodingString);
        return returnStr;
    }
    private String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }
    // 测试
    public String getPlaceFromTaoBao(String ip) {
        try {
            AddressUtils addressUtils = new AddressUtils();
            String address = addressUtils.getAddresses("ip=" + ip, "utf-8");
            JSONObject jsonObject = (JSONObject) JSONObject.parse(address);
            JSONObject data = jsonObject.getJSONObject("data");
            String area = data.getString("area");
            String country = data.getString("country");
            String region = data.getString("region");
            String city = data.getString("city");
            String county = data.getString("county");
            String isp = data.getString("isp");
            String str = country + " " + area + " " + region + " " + city + " " + county + " " + isp;
            return str;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }



    public String getAddressesFromBaidu(String ip, String encoding) {
        String url = "http://api.map.baidu.com/location/ip?ak=D1xLE96UiqMcZtKk9NYc9p9yVBPmHzxI&ip="+ip+"&coor=bd09ll";
        String resultFromBaidu = this.getResultFromBaidu(url, encoding);
        return resultFromBaidu;
    }


    private String getResultFromBaidu(String urlStr, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("GET");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    public String getPlace(String ip) {
        String place = "";
        try {
            String addressesFromBaidu = getAddressesFromBaidu(ip, "utf-8");
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(addressesFromBaidu);
            net.sf.json.JSONObject content = jsonObject.getJSONObject("content");
            place = content.getString("address");
        } catch (Exception e) {
            place = getPlaceFromTaoBao(ip);
        }
        return place;
    }


//    public static void main(String[] args) {
//        AddressUtils addressUtils = new AddressUtils();
//        String place = addressUtils.getPlace("222.209.35.6");
//        System.out.println(place);
//    }
}