package com.jpxq.ChatMesageEntity;

import com.jpxq.util.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by 李杰 on 2017/2/15.
 */

@XStreamAlias("xml")
public class OutPutMessage {
    @XStreamAlias("ToUserName")
    @XStreamCDATA
    private String ToUserName;

    @XStreamAlias("FromUserName")
    @XStreamCDATA
    private String FromUserName;

    @XStreamAlias("CreateTime")
    private Long CreateTime;

    @XStreamAlias("MsgType")
    @XStreamCDATA
    private String MsgType;

    @XStreamAlias("MsgId")
    @XStreamCDATA
    private String MsgId;

    @XStreamAlias("Content")
    @XStreamCDATA
    private String Content;
    @XStreamAlias("Event")
    @XStreamCDATA
    private String Event;
    //地理位置纬度
    @XStreamAlias("Latitude")
    private double Latitude;
    //地理位置经度
    @XStreamAlias("Longitude")
    private double Longitude;
    //地理位置精度
    @XStreamAlias("Precision")
    private double Precision;

    private ImageMessage Image;


    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public ImageMessage getImage() {
        return Image;
    }

    public void setImage(ImageMessage image) {
        Image = image;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getdoubleitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getPrecision() {
        return Precision;
    }

    public void setPrecision(double precision) {
        Precision = precision;
    }
}

