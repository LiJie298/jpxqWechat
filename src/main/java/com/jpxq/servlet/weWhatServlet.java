package com.jpxq.servlet;

import com.jpxq.sign.SignUtil;
import com.jpxq.util.MessageUtil;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 李杰 on 2017/2/15.
 */
@javax.servlet.annotation.WebServlet(name = "weWhatServlet")
public class weWhatServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            MessageUtil messageUtil = new MessageUtil();
            String s = messageUtil.acceptMessage(request,response);
            out.print(s);
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }finally {
            out.close();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

}
