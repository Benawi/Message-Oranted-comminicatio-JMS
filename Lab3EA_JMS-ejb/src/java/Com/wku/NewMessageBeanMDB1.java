/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.wku;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author HP PRO BOOK
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "LabQDest1"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NewMessageBeanMDB1 implements MessageListener {

    public NewMessageBeanMDB1() {
    }

    @Override
    public void onMessage(Message message) {
    try {
        TextMessage msg = (TextMessage) message;
    
            System.out.println("this is  the message bean received by mdb:" + msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(NewMessageBeanMDB1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
