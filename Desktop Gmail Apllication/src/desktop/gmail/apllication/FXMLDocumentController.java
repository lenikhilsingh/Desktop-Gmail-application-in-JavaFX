/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.gmail.apllication;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikhil
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXTextField emailid;
    @FXML
    private JFXTextField subject;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextArea message;
    @FXML
    private JFXTextField recieverid;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSendButton(ActionEvent event) {
        
        SendEmail(emailid.getText(), password.getText(), recieverid.getText(), subject.getText() ,message.getText());
    }
    
    private void   SendEmail(String user, String pass, String to, String sub, String msg)
    {
        Properties prop= new Properties();
        
         prop.put("mail.smtp.ssl.trust","smtp.gmail.com");
         prop.put("mail.smtp.auth",true);
         prop.put("mail.smtp.starttls.enable",true);
         prop.put("mail.smtp.host","smtp.gmail.com");
         prop.put("mail.smtp.port","587");
         
         
         Session session= Session.getInstance(prop, new javax.mail.Authenticator()
         {
             @Override
             protected javax.mail.PasswordAuthentication getPasswordAuthentication()
             {
             return new javax.mail.PasswordAuthentication(user, pass);
       
             }
             
         });
         
         try
         {
             Message message1= new MimeMessage(session);
             
             message1.setFrom( new InternetAddress("no-reply@gmail.com"));
             message1.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
             message1.setSubject(sub);
             message1.setText(msg);
             
             Transport.send(message1);
             
              JOptionPane.showMessageDialog(null,"message sent");
         
         }   
         
         catch(Exception e)
         {
          JOptionPane.showMessageDialog(null,e);
         }
      
        
        
    }
    
}
