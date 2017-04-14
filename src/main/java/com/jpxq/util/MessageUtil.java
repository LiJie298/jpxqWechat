package com.jpxq.util;

import com.jpxq.ChatMesageEntity.InputMessage;
import com.jpxq.ChatMesageEntity.OutPutMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by 李杰 on 2017/2/16.
 */
public class MessageUtil {

    public String acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 处理接收消息
        ServletInputStream in = request.getInputStream();
        // 将POST流转换为XStream对象
        XStream xs = new XStream(new DomDriver());
//        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(InputMessage.class);
        xs.processAnnotations(OutPutMessage.class);
        // 将指定节点下的xml节点数据映射为对象
        xs.alias("xml", InputMessage.class);
        // 将流转换为字符串
        StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            xmlMsg.append(new String(b, 0, n, "UTF-8"));
        }
        // 将xml内容转换为InputMessage对象
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());

        String servername = inputMsg.getToUserName();// 服务端
        String custermname = inputMsg.getFromUserName();// 客户端
        long createTime = inputMsg.getCreateTime();// 接收时间

        OutPutMessage outPutMessage = new OutPutMessage();
        outPutMessage.setFromUserName(inputMsg.getToUserName());
        outPutMessage.setCreateTime(new Date().getTime());
        outPutMessage.setToUserName(inputMsg.getFromUserName());

        // 取得消息类型
        String msgType = inputMsg.getMsgType();
        // 根据消息类型获取对应的消息内容
        if (msgType.equals(MsgType.TEXT)) {
            // 文本消息
            outPutMessage = sendTextMessage(outPutMessage,inputMsg);
        } else if (msgType.equals(MsgType.IMAGE)) {
            // 获取并返回多图片消息
            outPutMessage = sendImageMessage(outPutMessage,inputMsg);
        } else if (msgType.equals(MsgType.SUBSCRIBE)) {
            outPutMessage = sendSubscribeMessage(outPutMessage);
        } else if (msgType.equals(MsgType.UNSUBSCRIBE)) {
            outPutMessage = sendUnSubsrcibeMessage(outPutMessage);
        }
        XStream xStream = SerializeXmlUtil.createXstream();
        xStream.processAnnotations(OutPutMessage.class);
        String s = xStream.toXML(outPutMessage);
        return s;
    }


    private OutPutMessage sendSubscribeMessage(OutPutMessage outPutMessage) {
        outPutMessage.setContent("感谢您关注俊萍喜庆，我们致力于为您提供更好的服务！！！请按照下面的提示了解详情(输入对应的数字)\n" +
                "1、地址\n" +
                "2、电话\n" +
                "3、经营范围\n" +
                "4、关于我们\n" +
                "5、重新查看");
        outPutMessage.setMsgType(MsgType.TEXT);
        return outPutMessage;
    }

    private OutPutMessage sendUnSubsrcibeMessage(OutPutMessage outPutMessage) {

        return outPutMessage;
    }

    private OutPutMessage sendTextMessage(OutPutMessage outPutMessage,InputMessage inputMessage) {
        if (inputMessage.getContent().equals("1")) {
//            outPutMessage.setMsgType(MsgType.EVENT);
//            outPutMessage.setEvent("LOCATION");
//            outPutMessage.setLatitude(36.0690030000);
//            outPutMessage.setLongitude(111.4974780000);
//            outPutMessage.setPrecision(119.385040);
            outPutMessage.setMsgType(MsgType.TEXT);
            outPutMessage.setContent("地址是：\n" +
                    "临汾市尧都区新百汇市场负一层7区\n" +
                    "B7211");
        } else if (inputMessage.getContent().equals("2")) {
            outPutMessage.setMsgType(MsgType.TEXT);
            outPutMessage.setContent("联系方式：\n" +
                    "\t 李老板：13753792047\n" +
                    "\t 郭老板：13734082169\n" +
                    "欢迎您的致电");
        } else if (inputMessage.getContent().equals("3")) {
            outPutMessage.setMsgType(MsgType.TEXT);
            outPutMessage.setContent("主要经营范围：\n" +
                    "    灯类： 各种规格的大红灯笼、霓虹灯、数码灯、LED灯、彩灯、彩带\n" +
                    "    旗帜类：国旗、彩旗、串旗、刀旗、桌旗、敬仪旗\n" +
                    "    婚庆类：背景、香槟塔、烛台、许愿球、气球机、风机、点火器、彩虹门、拱门、花门、对联、冷焰火......\n" +
                    "    年货等等");
        } else if (inputMessage.getContent().equals("4")) {
            outPutMessage.setContent("关于我们");

        } else {
            sendSubscribeMessage(outPutMessage);
        }
        return outPutMessage;
    }


    private OutPutMessage sendImageMessage(OutPutMessage outPutMessage, InputMessage inputMessage) {

        return outPutMessage;
    }

    private OutPutMessage sendVoiceMessage(OutPutMessage outPutMessage) {
        return outPutMessage;
    }

    private OutPutMessage sendVideoMessage(OutPutMessage outPutMessage) {
        return outPutMessage;
    }

    private OutPutMessage sendLocationMessage(OutPutMessage outPutMessage) {
        return outPutMessage;
    }

    private OutPutMessage sendLinkMessage(OutPutMessage outPutMessage) {
        return outPutMessage;
    }

}
