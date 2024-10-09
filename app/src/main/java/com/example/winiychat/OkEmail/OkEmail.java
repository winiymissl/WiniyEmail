package com.example.winiychat.OkEmail;


import javax.mail.internet.MimeMessage;

/**
 * fun sendEmail(username: String, password: String, to: String, subject: String, body: String) {
 * try {
 * val props = Properties()
 * props["mail.smtp.host"] = "smtp.163.com" // 替换为您使用的邮件服务器
 * props["mail.smtp.port"] = "25"
 * props["mail.smtp.auth"] = "true"
 * props["mail.smtp.ssl.enable"] = "false"
 * val session = Session.getInstance(props, object : Authenticator() {
 * override fun getPasswordAuthentication(): PasswordAuthentication {
 * return PasswordAuthentication(username, password)
 * }
 * })
 * val message = MimeMessage(session)
 * message.setFrom(InternetAddress(username))
 * message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
 * message.setSubject(subject)
 * message.setText(body)
 * Transport.send(message)
 * } catch (e: Exception) {
 * }
 */
public class OkEmail {
    public static MimeMessage createEmail() {
        return null;
    }
}
