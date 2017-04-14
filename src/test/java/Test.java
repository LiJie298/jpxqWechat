import com.jpxq.ChatMesageEntity.OutPutMessage;
import com.jpxq.util.MsgType;
import com.jpxq.util.SerializeXmlUtil;
import com.thoughtworks.xstream.XStream;

import java.util.Calendar;

/**
 * Created by 李杰 on 2017/2/16.
 */
public class Test {
    @org.junit.Test
    public void getTest(){
        OutPutMessage outPutMessage = new OutPutMessage();
        outPutMessage.setFromUserName("jack");
        outPutMessage.setToUserName("lijie");
        outPutMessage.setCreateTime(Calendar.getInstance().getTimeInMillis());
        outPutMessage.setMsgType(MsgType.TEXT);
        outPutMessage.setContent(MsgType.TEXT);
//        XStream stream = new XStream(new DomDriver());
//        stream.alias("xml",OutPutMessage.class);
        XStream xStream =SerializeXmlUtil.createXstream();
        xStream.processAnnotations(OutPutMessage.class);
        String s = xStream.toXML(outPutMessage);
        System.out.println(s);

        String s1 =
                "<xml>" +
                "  <ToUserName><![CDATA[lijie]]></ToUserName>" +
                "  <FromUserName><![CDATA[jack]]></FromUserName>" +
                "  <CreateTime><![CDATA[1487298465860]]></CreateTime>" +
                "  <MsgType>text</MsgType>" +
                "</xml>";
        xStream.processAnnotations(OutPutMessage.class);
        xStream.alias("xml",OutPutMessage.class);
        StringBuilder xmlMsg = new StringBuilder();
        OutPutMessage outPutMessage1 = (OutPutMessage) xStream.fromXML(s1);
        return;
    }
}
