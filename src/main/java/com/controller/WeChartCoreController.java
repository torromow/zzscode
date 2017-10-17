package com.controller;

import com.service.CoreService;
import com.util.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by PC on 2017/9/2.
 */
@Controller
public class WeChartCoreController {
    @Resource
    private CoreService coreService;

    /**
     * 请求校验（确认请求来自微信服务器）
     */
    @RequestMapping(value="wechartcore",method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 请求校验与处理
     */
    @RequestMapping(value="wechartcore",method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 接收参数微信加密签名、 时间戳、随机数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        PrintWriter out = response.getWriter();
        // 请求校验
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            // 调用核心服务类接收处理请求
            String respXml = coreService.processRequest(request);
            out.print(respXml);
        }
        out.close();
        out = null;
    }


}
