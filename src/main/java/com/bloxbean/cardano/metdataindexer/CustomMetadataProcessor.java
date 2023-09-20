package com.bloxbean.cardano.metdataindexer;

import com.bloxbean.cardano.metdataindexer.db.CustomerRepository;
import com.bloxbean.cardano.yaci.store.events.AuxDataEvent;
import com.bloxbean.cardano.yaci.store.metadata.domain.TxMetadataEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;
import static org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT;

//#### This is an optional class

//You can also write your own processor to process derived event like TxMetadataEvent or core event like AuxDataEvent.
//But if you want to just filter out some metadata based on label, better to use CustomMetadataStorage as rollback
//is automatically handled by the framework.
@Component
@RequiredArgsConstructor
public class CustomMetadataProcessor {
    private final CustomerProcessor customerProcessor;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handlMetdataEvent(TxMetadataEvent txMetadataEvent) {
        try {
            customerProcessor.saveCustomer();
        } catch (Exception e) {
            System.out.println("Error saving customer: " + e.getMessage());
        }
    }

}
