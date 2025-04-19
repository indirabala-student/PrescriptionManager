package com.module.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.module.model.PharmacyRecord;
import com.module.model.RecordEntry;

import java.util.LinkedList;
import java.util.List;

public interface IJacksonService {

    public String parseJsonFileToString(String fileName);

    public JsonNode parseStringToArrayNode(String string);

    public List<RecordEntry> parseArrayNodeToRecordEntryList(JsonNode arrayNode);

}
