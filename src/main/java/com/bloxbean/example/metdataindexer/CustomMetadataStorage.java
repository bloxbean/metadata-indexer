package com.bloxbean.example.metdataindexer;

import com.bloxbean.cardano.yaci.store.metadata.domain.TxMetadataLabel;
import com.bloxbean.cardano.yaci.store.metadata.storage.impl.jpa.MetadataMapper;
import com.bloxbean.cardano.yaci.store.metadata.storage.impl.jpa.TxMetadataStorageImpl;
import com.bloxbean.cardano.yaci.store.metadata.storage.impl.jpa.repository.TxMetadataLabelRepository;

import java.util.List;

//### This is an optional class

//Uncomment the following @Component annotation to override the default Metadata storage with
//this custom storage. This custom storage will only store metadata with label 721.
//@Component
public class CustomMetadataStorage extends TxMetadataStorageImpl {
    public CustomMetadataStorage(TxMetadataLabelRepository metadataLabelRepository,
                                 MetadataMapper metadataMapper) {
        super(metadataLabelRepository, metadataMapper);
    }

    @Override
    public List<TxMetadataLabel> saveAll(List<TxMetadataLabel> txMetadataLabelList) {
        List<TxMetadataLabel> filteredMetadataLabels = txMetadataLabelList.stream()
                .filter(txMetadataLabel -> "721".equals(txMetadataLabel.getLabel()))
                .toList();
        return super.saveAll(filteredMetadataLabels);
    }
}
