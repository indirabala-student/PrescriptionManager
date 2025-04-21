package com.module.util;

public interface SQLUtil {

    public String createPharmacyRecordTable();

    public String createRecordTable();

    public String createRxInfoTable();

    public String dropTable(String tableName);

    public String insertPharmacyRecord(int memberId);

    public String insertRecord(int memberId, String field1, String field2, String field3);

    public String insertRxInfo(int recordId, String rx, String description);

    public String selectAllPharmacyRecord();

    public String selectAllRecordsByMemberId(int memberId);

    public String selectAllRxInfosByRecordId(int id);
}
