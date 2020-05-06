package ch.jmildner.tools;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Die finale Klasse <code>MyMainTool</code> dient dem Versenden von E-Mails.
 *
 * <pre>
 *
 *  ----------------------------------------------------------------
 *  Anwendungsbeispiel:
 *  ----------------------------------------------------------------
 *
 *  final String 	host 	= "smtp.hispeed.ch";
 *  final String 	from 	= "noreply@balcab.ch";
 *  final String 	user 	= "johann.mildner@balcab.ch";
 *  final String 	passwordd = "**********";
 *  final boolean 	debug 	= true;
 *
 *  oder
 *
 *  final String 	host 	= "smtp.genotec.ch";
 *  final String 	from 	= "johann@jmildner.ch";
 *  final String 	user 	= "johann@jmildner.ch";
 *  final String 	password = "**********";
 *
 *  MyMailTool 		mail 	= new MyMailTool(host, from, user, password, debug);
 *
 *
 *  final String[] 	TO 	= { "jmildner@gmx.ch" };
 *  final String[] 	CC 	= {};
 *  final String[] 	BCC 	= {};
 *
 *  final String 	SUBJECT = "Test";
 *
 *  final String TEXT 	= "Lieber Franzi \n"
 *  			+ "Ich schreibe dir dieses Mail langsam, \n"
 *  			+ "weil ich weiss, dass du nicht schnell lesen kannst. \n"
 *  			+ "Gruss und Kuss \n"
 *  			+ "Julius"
 *
 *   ----------------------------------------------------------------
 * </pre>
 *
 *
 * @author Johann Mildner
 *
 */
final public class MyMailTool
{

    private final boolean debug;

    private final String host;
    private final String from;
    private final String user;
    private final String password;

    public MyMailTool(String host, String from, String user,
            String password, boolean... debug)
    {
        this.host = host;
        this.from = from;
        this.user = user;
        this.password = password;

        if (debug.length == 1)
        {
            this.debug = debug[0];
        }
        else
        {
            this.debug = false;
        }
    }

    public void sendMail(String[] TO, String[] CC, String[] BCC,
            String SUBJECT, String TEXT) throws Exception
    {
        sendMail(TO, CC, BCC, SUBJECT, TEXT, host, user, from,
                password);
    }

    private void sendMail(final String[] TO, final String[] CC,
            final String[] BCC, final String SUBJECT, final String TEXT,
            final String HOST, final String USER, final String FROM,
            final String PASSWORD) throws Exception
    {
        if (debug)
        {
            System.out.println("----------");
            System.out.println(HOST);
            System.out.println("----------");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("FROM:     " + FROM);
            System.out.println("TO:       " + Arrays.toString(TO));
            System.out.println("CC:       " + Arrays.toString(CC));
            System.out.println("BCC:      " + Arrays.toString(BCC));
            System.out.println("USER:     " + USER);
            System.out.println("PASSWORD: " + "*******");
            System.out.println("SUBJECT:  " + SUBJECT);
            System.out.println("TEXT:     " + TEXT);
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST);
        // props.put("mail.smtp.socketFactory.port", "25");
        // props.put("mail.smtp.socketFactory.class",
        // "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.port", "25");

        Authenticator auth;
        auth = new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(USER, PASSWORD);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(FROM));

        for (String recipient : TO)
        {
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
        }

        for (String recipient : CC)
        {
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(recipient));
        }

        for (String recipient : BCC)
        {
            message.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(recipient));
        }

        message.setSubject(SUBJECT);

        message.setText(TEXT);

        message.setSentDate(new Date());

        Transport.send(message);

        System.out.println("MAIL GESENDET ...");

    }
}
