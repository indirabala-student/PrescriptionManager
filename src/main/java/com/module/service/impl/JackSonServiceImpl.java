package com.module.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.model.RecordEntry;
import com.module.service.IJacksonService;
import com.module.util.logging.AppLogger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JackSonServiceImpl implements IJacksonService {

    private static final Logger logger = AppLogger.getLogger(H2ServiceImpl.class);

    JSONParser jsonParser=new JSONParser();
    ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public String parseJsonFileToString(String fileName) {
        try {
            return String.valueOf(jsonParser.parse(new FileReader(fileName)));
        }catch (IOException | ParseException e){
            return null;
        }
    }

    @Override
    public JsonNode parseStringToArrayNode(String jsonString) {

        JsonNode arrayNode;

        if (jsonString!=null) {
            try {
                arrayNode=objectMapper.readTree(jsonString);
                if (arrayNode.isArray()){

                    logger.info("JSON String is converted into Array Node");
                    return arrayNode;
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public List<RecordEntry> parseArrayNodeToRecordEntryList(JsonNode arrayNode) {
        if (arrayNode!=null) {
            List<RecordEntry> recordEntryList=new ArrayList<>();
            for (JsonNode node: arrayNode) {
                RecordEntry recordEntry = new RecordEntry();
                recordEntry.setMemberId(node.get("memberId").asInt());
                recordEntry.setField1(node.get("field1").asText());
                recordEntry.setField2(node.get("field2").asText());
                recordEntry.setField3(node.get("field3").asText());
                recordEntry.setRx(node.get("rx").asText());
                recordEntry.setDescription(node.get("description").asText());
                recordEntryList.add(recordEntry);
            }
            logger.info("JSON Array Node is converted into Record entry List");
            return recordEntryList;
        }
        return null;
    }
}
