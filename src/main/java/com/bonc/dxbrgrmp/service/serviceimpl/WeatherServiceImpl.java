package com.bonc.dxbrgrmp.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.bonc.dxbrgrmp.dao.mybatis.mapper.TWeatherCityCodeMapper;
import com.bonc.dxbrgrmp.pojo.po.CityWeatherInfo;
import com.bonc.dxbrgrmp.pojo.po.DailyWeatherInfo;
import com.bonc.dxbrgrmp.pojo.po.HourWeatherInfo;
import com.bonc.dxbrgrmp.service.IWeatherService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class WeatherServiceImpl implements IWeatherService {

    @Resource
    private TWeatherCityCodeMapper tWeatherCityCodeMapper;

    private Document getWeathInfofromHtmlByCityId(String cityId,String weatherurl1) {
        HttpGet hg = null;
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(60000).setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000)
                .setStaleConnectionCheckEnabled(true).build();
        // 创建httpClient对象
        CloseableHttpClient chC = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig).build();
        String url = weatherurl1;
        url = url + cityId + ".shtml";
        URIBuilder uri = null;
        try {
            uri = new URIBuilder(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            hg = new HttpGet(uri.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        hg.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        hg.setHeader("Accept-Encoding", "gzip, deflate");
        hg.setHeader("Accept-Language",
                "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        hg.setHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");

        // 发送请求
        HttpEntity entity = null;
        String line = "";
        String Sline = "";
        InputStream inputstream = null;
        CloseableHttpResponse response = null;
        try {
            response = chC.execute(hg);
            // 获取请求结果
            entity = response.getEntity();
            inputstream = entity.getContent();
            BufferedReader bufferedreader = new BufferedReader(
                    new InputStreamReader(inputstream, "UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedreader.readLine()) != null) {
                stringBuffer.append(line);
            }
            Document htmlDoc = Jsoup.parse(stringBuffer.toString());
            return htmlDoc;
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
            return null;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        } finally {
            try {
                inputstream.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public CityWeatherInfo get7DaysWeather(String cityId,String weatherurl) {
       // String weatherurl = "http://www.weather.com.cn/weather/";
        String weatherurl1=weatherurl+"/weather/";
        Document htmlDoc  = getWeathInfofromHtmlByCityId(cityId,weatherurl1);
        if(htmlDoc == null)
            return null;
        List<Node> nodes_7d = htmlDoc.getElementById("7d").child(3).childNodes();
        CityWeatherInfo cityWeatherInfo = new CityWeatherInfo();
        cityWeatherInfo.setCityId(cityId);
        List<DailyWeatherInfo> dailyweatherList = cityWeatherInfo.getDailyweatherList();
        for (Node eachnode : nodes_7d) {
            Element elementNode = (Element) eachnode;
            DailyWeatherInfo dailyWeatherInfo = new DailyWeatherInfo();
            String daystr = elementNode.getElementsByTag("h1").text();
            String temp = elementNode.getElementsByClass("tem").text();
            String win = elementNode.getElementsByClass("win").tagName("i").text();
            String wea = elementNode.getElementsByClass("wea").text();
            String[] tempArray = temp.split("/");

            dailyWeatherInfo.setDayStr(daystr.substring(0, daystr.indexOf("日")));
            dailyWeatherInfo.setTempL(tempArray[1].substring(0, tempArray[1].length() - 1));
            dailyWeatherInfo.setTempH(tempArray[0]);
            dailyWeatherInfo.setWin(win);
            dailyWeatherInfo.setWea(wea);
            dailyweatherList.add(dailyWeatherInfo);
        }
        cityWeatherInfo.setDailyweatherList(dailyweatherList);
        return cityWeatherInfo;
    }

    public CityWeatherInfo get15DaysWeather(String cityId,String weatherurl) {
        CityWeatherInfo cityWeatherInfo = get7DaysWeather(cityId,weatherurl);
       // String weatherurl = "http://www.weather.com.cn/weather15d/";
        String weatherurl1=weatherurl+"/weather15d/";
        Document htmlDoc  = getWeathInfofromHtmlByCityId(cityId,weatherurl1);
        if(htmlDoc == null)
            return null;
        List<Node> nodes_15d = htmlDoc.getElementById("15d").child(3).childNodes();
        List<DailyWeatherInfo> dailyweatherList = cityWeatherInfo.getDailyweatherList();
        for (Node eachnode : nodes_15d) {
            Element elementNode = (Element) eachnode;
            DailyWeatherInfo dailyWeatherInfo = new DailyWeatherInfo();
            String daystr = elementNode.getElementsByClass("time").text();
            String temp = elementNode.getElementsByClass("tem").text();
            String win = elementNode.getElementsByClass("win").tagName("i").text();
            String wea = elementNode.getElementsByClass("wea").text();
            String[] tempArray = temp.split("/");

            dailyWeatherInfo.setDayStr(daystr.substring(daystr.indexOf("（")+1, daystr.lastIndexOf("）")));
            dailyWeatherInfo.setTempL(tempArray[1].substring(0, tempArray[1].length() - 1));
            dailyWeatherInfo.setTempH(tempArray[0]);
            dailyWeatherInfo.setWin(win);
            dailyWeatherInfo.setWea(wea);
            dailyweatherList.add(dailyWeatherInfo);
        }
        cityWeatherInfo.setDailyweatherList(dailyweatherList);
//        nodes_15d.forEach(eachnode ->{
//            Element elementNode = (Element) eachnode;
//            DailyWeatherInfo dailyWeatherInfo = new DailyWeatherInfo();
//            String daystr = elementNode.getElementsByClass("time").text();
//            String temp = elementNode.getElementsByClass("tem").text();
//            String win = elementNode.getElementsByClass("win").tagName("i").text();
//            String wea = elementNode.getElementsByClass("wea").text();
//            String[] tempArray = temp.split("/");}
//            );
        return cityWeatherInfo;
    }


    /**
     *
     * @param cityId
     * @param weatherurl
     * @return
     * 所获得的json中：ja：天气  jb：温度  jc：风力 jd：风向 jf：时间
     * 经过实践可知:中国天气网的24小时预报，更新出的内容为早上8点开始，和晚上8点开始
     */
    public List<HourWeatherInfo> get24HourFuture (String cityId, String weatherurl){
        String weatherurl1=weatherurl+"/weather1dn/";
        Document htmlDoc  = getWeathInfofromHtmlByCityId(cityId,weatherurl1);
        String str = htmlDoc.getElementsByClass("todayRight").get(0).child(0).toString();//获取到信息
        System.out.println(str);
        String[] time = str.split("],\\[", 3);
        String timeTemp = time[0].substring(time[0].indexOf("[")+1)+","+time[1]+"]"; //获取今天和明天的json数据
        timeTemp = timeTemp.replaceAll("jf","hour").replaceAll("ja", "wea").replaceAll("jb", "temp").replaceAll("jc", "winB").replaceAll("jd", "winA");
        //将天气信息封装至对象hwiList中。该对象类型为链表
        LinkedList<HourWeatherInfo> hwiList = new LinkedList<>();
        List<HourWeatherInfo> hwiJson = JSONObject.parseArray(timeTemp,com.bonc.dxbrgrmp.pojo.po.HourWeatherInfo.class);
        System.out.println("当前获取到的信息为"+hwiJson);
        //获取当前信息
        HourWeatherInfo hwiFirst = hwiJson.get(0);
        int JsonNow=Integer.parseInt(hwiFirst.getHour().substring(8)); //获取json中的开始信息
        int hourNow =Calendar.getInstance().get(Calendar.HOUR_OF_DAY);  //获取当前时间-小时
        //思路：根据获取的信息：08  20 进行判断 补全信息或截取信息
        if(JsonNow == 8) { //无论何时访问都是同一天
            if(hourNow<8) {  //早上8点前访问(同一天) 补全信息
                String day=hwiFirst.getHour().substring(0,8);
                for(int i=0;i<7-hourNow;i++) {  //将前面的值设置为8点一样的值
                    HourWeatherInfo hwiNew = new HourWeatherInfo(day+0+(hourNow+1),hwiFirst.getWea(),hwiFirst.getTemp(),hwiFirst.getWinA(),hwiFirst.getWinB());
                    hwiList.add(hwiNew);  //补上没有的信息
                }
                int count=1;
                for(HourWeatherInfo hwiTemp:hwiJson) { //使用foreach循环，提高LinkedList查询效率
                    hwiList.add(hwiTemp);	//添加上正确的24小时信息
                    count++;
                    if(count > 17+hourNow) {//6点的话24-（7-hourNow)
                        break;
                    }
                }
            }else {	//早上8点后访问(同一天) 删减信息
                int count=1;
                boolean status =false;
                for(HourWeatherInfo hwiTemp:hwiJson) { //使用foreach循环，提高LinkedList查询效率
                    int temphour=Integer.parseInt(hwiTemp.getHour().substring(8));
                    if(status || temphour > hourNow) { //短路或 提高判断效率
                        status = true; //主要是用来表示一旦开始增加数据，后续数据就不再判断小时时间了
                        hwiList.add(hwiTemp);	//添加上正确的24小时信息
                    }else {
                        continue;
                    }
                    count++;
                    if(count > 24) {
                        break;
                    }
                }
            }
        }else if(JsonNow == 20) { //会出现不同天的情况,逻辑思考：只要日期不一样，就表示不是同一天
            String dayJson = hwiFirst.getHour().substring(0,8);
            String dayNow = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
            if(dayJson.equals(dayNow)) { //同一天情况
                if(hourNow<20) {	//补全信息
                    for(int i=0;i<19-hourNow;i++) {  //将前面的值设置为20点一样的值
                        HourWeatherInfo hwiNew = new HourWeatherInfo(dayJson+(hourNow+1),hwiFirst.getWea(),hwiFirst.getTemp(),hwiFirst.getWinA(),hwiFirst.getWinB());
                        hwiList.add(hwiNew);  //补上没有的信息
                    }
                    int count=1;
                    for(HourWeatherInfo hwiTemp:hwiJson) { //使用foreach循环，提高LinkedList查询效率
                        hwiList.add(hwiTemp);	//添加上正确的24小时信息
                        count++;
                        if(count > hourNow+5) {  //24-（19-hourNow）
                            break;
                        }
                    }
                }else { //20点之后  截取信息了
                    int count=1;
                    boolean status =false;
                    for(HourWeatherInfo hwiTemp:hwiJson) { //使用foreach循环，提高LinkedList查询效率
                        int temphour=Integer.parseInt(hwiTemp.getHour().substring(8));
                        if(status || temphour == hourNow) {  //加等于：为排除23点的特殊性
                            status = true;
                            if(count!=1)
                                hwiList.add(hwiTemp);	//添加上正确的24小时信息
                        }else {
                            continue;
                        }
                        count++;
                        if(count > 25) {  //控制个数
                            break;
                        }
                    }
                }
            }else { //不同天的情况 晚上1 2点访问。
                int count=1;
                boolean status =false;
                for(HourWeatherInfo hwiTemp:hwiJson) { //使用foreach循环，提高LinkedList查询效率
                    int temphour=Integer.parseInt(hwiTemp.getHour().substring(8));
                    if( status || temphour == hourNow+1) {
                        status = true;
                        hwiList.add(hwiTemp);	//添加上正确的24小时信息
                    }else {
                        continue;
                    }
                    count++;
                    if(count > 24) {  //控制个数
                        break;
                    }
                }
            }
        }else {
            System.out.println("24小时天气获取json信息有误！！！");
        }
        System.out.println("最终结果："+hwiList);
        return hwiList;
    }
       public static void main(String[] args)  {
        WeatherServiceImpl WeatherServiceImpl = new WeatherServiceImpl();
//        WeatherServiceImpl.getWeatherInfo();
        WeatherServiceImpl.get24HourFuture ("101010600","http://www.weather.com.cn/");
    }
}
