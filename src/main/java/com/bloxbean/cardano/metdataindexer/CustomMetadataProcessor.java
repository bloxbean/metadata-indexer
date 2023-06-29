package com.bloxbean.cardano.metdataindexer;

import com.bloxbean.cardano.yaci.store.events.AuxDataEvent;
import com.bloxbean.cardano.yaci.store.metadata.domain.TxMetadataEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//#### This is an optional class

//You can also write your own processor to process derived event like TxMetadataEvent or core event like AuxDataEvent.
//But if you want to just filter out some metadata based on label, better to use CustomMetadataStorage as rollback
//is automatically handled by the framework.
//@Component
public class CustomMetadataProcessor {

    @EventListener
    @Transactional
    public void handlMetdataEvent(TxMetadataEvent txMetadataEvent) {
        System.out.println("CustomMetadataProcessor >>>>>" + txMetadataEvent.getTxMetadataList());
    }

//    @EventListener
//    public void handleAuxDataEvent(AuxDataEvent auxDataEvent) {
//
//    }
}
