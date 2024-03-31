package com.lalabib.latihan.simpleproduct.utils

import com.lalabib.latihan.simpleproduct.BuildConfig.Password
import com.lalabib.latihan.simpleproduct.utils.SharedObject.Email
import com.lalabib.latihan.simpleproduct.utils.SharedObject.MailHost
import java.util.*
import javax.activation.DataHandler
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.mail.util.ByteArrayDataSource


class EmailSender : Authenticator() {

    private val session: Session

    override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(Email, Password)
    }

    fun sendMail(
        subject: String?,
        body: String,
        recipients: String
    ) {
        val message = MimeMessage(session)
        val handler = DataHandler(ByteArrayDataSource(body.toByteArray(), "text/plain"))
        message.sender = InternetAddress(Email)
        message.subject = subject
        message.dataHandler = handler
        if (recipients.indexOf(',') > 0) message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(recipients)
        ) else message.setRecipient(
            Message.RecipientType.TO, InternetAddress(recipients)
        )
        Transport.send(message)
    }

    init {
        val props = Properties()
        props.setProperty("mail.transport.protocol", "smtp")
        props.setProperty("mail.host", MailHost)
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.port"] = "465"
        props["mail.smtp.socketFactory.port"] = "465"
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props["mail.smtp.socketFactory.fallback"] = "false"
        props.setProperty("mail.smtp.quitwait", "false")
        session = Session.getDefaultInstance(props, this)
    }
}