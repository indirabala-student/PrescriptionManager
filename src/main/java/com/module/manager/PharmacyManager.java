package com.module.manager;

import com.fasterxml.jackson.databind.JsonNode;

import com.module.model.PharmacyRecord;
import com.module.model.RecordEntry;
import com.module.service.IJacksonService;
import com.module.service.IPharmacyService;
import com.module.service.impl.JackSonServiceImpl;
import com.module.service.impl.PharmacyServiceImpl;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.util.List;

public class PharmacyManager {

    IJacksonService jacksonService=new JackSonServiceImpl();
    IPharmacyService pharmacyService=new PharmacyServiceImpl();

    public List<PharmacyRecord> processJSONFileToPharmacyRecord(String fileName){
        try {
            String jsonString=jacksonService.parseJsonFileToString(fileName);
            JsonNode arrayNode=jacksonService.parseStringToArrayNode(jsonString);
            List<RecordEntry> recordEntryList=jacksonService.parseArrayNodeToRecordEntryList(arrayNode);
            return pharmacyService.mapToPharmacyRecordList(recordEntryList);
        } catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

}