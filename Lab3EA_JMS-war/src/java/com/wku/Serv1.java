/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wku;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP PRO BOOK
 */
public class Serv1 extends HttpServlet {

    @Resource(mappedName = "LabQDest1")
    private Queue labQDest1;
//    @Inject
//    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
//    private JMSContext context;
    @Resource(mappedName = "LAbCF11")
    private ConnectionFactory queue;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mes = request.getParameter("m1");
        sendJMSMessageToLabQDest1(mes);
        out.println("<h1>ur message is queued</h1>");

    }

    private void sendJMSMessageToLabQDest1(String messageData) {

        try {
            Connection con = queue.createConnection();
            Session s = con.createSession();
            MessageProducer mp = s.createProducer(labQDest1);
            TextMessage tm = s.createTextMessage(messageData);
            tm.setText(messageData);
            mp.send(tm);

        } catch (JMSException ex) {
            Logger.getLogger(Serv1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
