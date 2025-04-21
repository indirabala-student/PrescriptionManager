package com.module.util.impl;

import com.module.util.SQLUtil;
import com.module.util.StringUtil;

public class PharmacySQLUtilImpl implements SQLUtil {

    @Override
    public String createPharmacyRecordTable() {

        return """
            CREATE TABLE IF NOT EXISTS pharmacy_record (
                member_id INT PRIMARY KEY
            );
            """;
    }

    @Override
    public String createRecordTable() {

        return """
            CREATE TABLE IF NOT EXISTS record (
                record_id INT AUTO_INCREMENT PRIMARY KEY,
                member_id INT,
                field1 VARCHAR(255),
                field2 VARCHAR(255),
                field3 VARCHAR(255),
                FOREIGN KEY (member_id) REFERENCES pharmacy_record(member_id)
            );
            """;
    }

    @Override
    public String createRxInfoTable() {

        return """
            CREATE TABLE IF NOT EXISTS rx_info (
                rx_info_id INT AUTO_INCREMENT PRIMARY KEY,
                record_id INT,
                rx VARCHAR(255),
                description VARCHAR(255),
                FOREIGN KEY (record_id) REFERENCES record(record_id)
            );
            """;
    }

    @Override
    public String dropTable(String tableName) {
        StringUtil stringUtil=new StringUtil();
        tableName=stringUtil.toSnakeCase(tableName);
        return "DROP TABLE IF EXISTS "+tableName+";";
    }

    @Override
    public String insertPharmacyRecord(int memberId) {
        return String.format("INSERT INTO pharmacy_record (member_id) VALUES "+memberId+";");
    }

    @Override
    public String insertRecord(int memberId, String field1, String field2, String field3) {

        return String.format(
                "INSERT INTO record (member_id, field1, field2, field3) VALUES (%d, '%s', '%s', '%s');",
                memberId, field1, field2, field3
        );
    }

    @Override
    public String insertRxInfo(int recordId, String rx, String description) {
        return String.format(
                "INSERT INTO rx_info (record_id, rx, description) VALUES (%d, '%s', '%s');",
                recordId, rx, description
        );
    }

    @Override
    public String selectAllPharmacyRecord() {
        return "SELECT * FROM pharmacy_record;";
    }

    @Override
    public String selectAllRecordsByMemberId(int memberId) {
        return "SELECT * FROM record WHERE member_id = %d;"+memberId;
    }

    @Override
    public String selectAllRxInfosByRecordId(int id) {
        return "SELECT * FROM rx_info WHERE record_id = id;"+id;
    }
}
