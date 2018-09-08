/**************************************************************************************
 * Copyright (C) 2008 EsperTech, Inc. All rights reserved.                            *
 * http://esper.codehaus.org                                                          *
 * http://www.espertech.com                                                           *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package com.espertech.esper.example.terminal.sender;

import com.espertech.esper.example.terminal.common.BaseTerminalEvent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.*;
import java.util.Hashtable;

public class InboundQueueSender
{
    public static final String SEND_QUEUE = "jms/queue/queue_b";

    private final QueueConnection conn;
    private final QueueSession session;
    private final QueueSender sender;

    public InboundQueueSender(String providerURL) throws JMSException, NamingException
    {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.PROVIDER_URL, providerURL);
        env.put(Context.SECURITY_PRINCIPAL, "guest");
        env.put(Context.SECURITY_CREDENTIALS, "pass");

        InitialContext iniCtx = new InitialContext(env);
        QueueConnectionFactory qcf = (QueueConnectionFactory) iniCtx.lookup("jms/RemoteConnectionFactory");
        conn = qcf.createQueueConnection("guest", "pass");

        Queue queB = (Queue) iniCtx.lookup(SEND_QUEUE);
        session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        sender = session.createSender(queB);
        conn.start();
    }

    public void sendEvent(BaseTerminalEvent baseDeskEvent)
    {
        try
        {
            ObjectMessage textMessage = session.createObjectMessage(baseDeskEvent);
            sender.send(textMessage);
        }
        catch (JMSException ex)
        {
            System.out.println("Error sending event:" + ex.toString());
        }
    }

    public void destroy() throws JMSException
    {
        sender.close();
        conn.stop();
        session.close();
        conn.close();
    }
}
