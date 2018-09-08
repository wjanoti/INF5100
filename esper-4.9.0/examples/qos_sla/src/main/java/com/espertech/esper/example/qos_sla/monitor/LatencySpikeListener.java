/**************************************************************************************
 * Copyright (C) 2008 EsperTech, Inc. All rights reserved.                            *
 * http://esper.codehaus.org                                                          *
 * http://www.espertech.com                                                           *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package com.espertech.esper.example.qos_sla.monitor;

import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.example.qos_sla.eventbean.OperationMeasurement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LatencySpikeListener implements UpdateListener
{
    public void update(EventBean[] newEvents, EventBean[] oldEvents)
    {
        OperationMeasurement theEvent = (OperationMeasurement) newEvents[0].get("alert");

        log.info("Alert, for operation '" + theEvent.getOperationName() +
                "' and customer '" + theEvent.getCustomerId() + "'" +
                " latency was " + theEvent.getLatency());
    }

    private static final Log log = LogFactory.getLog(LatencySpikeListener.class);
}
