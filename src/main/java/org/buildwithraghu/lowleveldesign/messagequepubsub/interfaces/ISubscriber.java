package org.buildwithraghu.lowleveldesign.messagequepubsub.interfaces;

import org.buildwithraghu.lowleveldesign.messagequepubsub.model.Message;

public interface ISubscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}
