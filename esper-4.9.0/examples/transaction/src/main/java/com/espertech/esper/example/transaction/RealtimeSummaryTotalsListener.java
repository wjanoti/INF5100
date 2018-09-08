/**************************************************************************************
 * Copyright (C) 2008 EsperTech, Inc. All rights reserved.                            *
 * http://esper.codehaus.org                                                          *
 * http://www.espertech.com                                                           *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package com.espertech.esper.example.transaction;

import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.client.EventBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RealtimeSummaryTotalsListener implements UpdateListener
{
    public void update(EventBean[] newEvents, EventBean[] oldEvents)
    {
        if (newEvents == null)
        {
            // we don't care about events leaving the window (old events)
            return;
        }

        EventBean theEvent = newEvents[0];
        log.debug(
                " Totals minAC=" + theEvent.get("minLatencyAC") +
                " maxAC=" + theEvent.get("maxLatencyAC") +
                " avgAC=" + theEvent.get("avgLatencyAC") +
                " minAB=" + theEvent.get("minLatencyAB") +
                " maxAB=" + theEvent.get("maxLatencyAB") +
                " avgAB=" + theEvent.get("avgLatencyAB") +
                " minBC=" + theEvent.get("minLatencyBC") +
                " maxBC=" + theEvent.get("maxLatencyBC") +
                " avgBC=" + theEvent.get("avgLatencyBC")
                );
    }

    private static final Log log = LogFactory.getLog(RealtimeSummaryTotalsListener.class);
}
