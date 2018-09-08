/*
 * *************************************************************************************
 *  Copyright (C) 2008 EsperTech, Inc. All rights reserved.                            *
 *  http://esper.codehaus.org                                                          *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 * *************************************************************************************
 */

package com.espertech.esper.example.cycledetect;

import com.espertech.esper.client.util.ExpressionReturnType;
import com.espertech.esper.plugin.PlugInAggregationMultiFunctionHandler;
import com.espertech.esper.plugin.PlugInAggregationMultiFunctionStateFactory;
import com.espertech.esper.plugin.PlugInAggregationMultiFunctionValidationContext;
import com.espertech.esper.epl.agg.access.AggregationAccessor;
import com.espertech.esper.epl.agg.access.AggregationStateKey;

public class CycleDetectorAggregationHandler implements PlugInAggregationMultiFunctionHandler {

    private static final AggregationStateKey CYCLE_KEY = new AggregationStateKey() {};

    private final CycleDetectorAggregationFactory factory;
    private final PlugInAggregationMultiFunctionValidationContext validationContext;

    public CycleDetectorAggregationHandler(CycleDetectorAggregationFactory factory, PlugInAggregationMultiFunctionValidationContext validationContext) {
        this.factory = factory;
        this.validationContext = validationContext;
    }

    public AggregationStateKey getAggregationStateUniqueKey() {
        return CYCLE_KEY;   // Share the same provider
    }

    public PlugInAggregationMultiFunctionStateFactory getStateFactory() {
        return new CycleDetectorAggregationStateFactory(factory.getFromExpression(), factory.getToExpression());
    }

    public AggregationAccessor getAccessor() {
        if (validationContext.getFunctionName().toLowerCase().equals(CycleDetectorConstant.CYCLEOUTPUT_NAME)) {
            return new CycleDetectorAggregationAccessorOutput();
        }
        return new CycleDetectorAggregationAccessorDetect();
    }

    public ExpressionReturnType getReturnType() {
        if (validationContext.getFunctionName().toLowerCase().equals(CycleDetectorConstant.CYCLEOUTPUT_NAME)) {
            return ExpressionReturnType.collectionOfSingleValue(factory.getFromExpression().getType());
        }
        return ExpressionReturnType.singleValue(Boolean.class) ;
    }
}
