package com.module.service;

import com.module.model.PharmacyRecord;
import com.module.model.RecordEntry;

import java.util.List;

public interface IPharmacyService {

    public List<RecordEntry> mapObjectsToRecordEntryList(List<Object> objectList);

    public List<PharmacyRecord> mapToPharmacyRecordList(List<RecordEntry> recordEntryList);

    public boolean mapToH2Database(List<PharmacyRecord> pharmacyRecordList);
}
