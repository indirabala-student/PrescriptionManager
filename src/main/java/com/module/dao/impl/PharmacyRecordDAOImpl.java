package com.module.dao.impl;

import com.module.model.PharmacyRecord;
import com.module.service.IH2Service;
import com.module.dao.IPharmacyRecordDAO;
import com.module.service.impl.H2ServiceImpl;
import com.module.util.SQLUtil;
import com.module.util.impl.PharmacySQLUtilImpl;
import com.module.util.logging.AppLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PharmacyRecordDAOImpl implements IPharmacyRecordDAO {
    IH2Service h2Service=new H2ServiceImpl();
    SQLUtil sqlUtil=new PharmacySQLUtilImpl();

    private static final Logger logger = AppLogger.getLogger(H2ServiceImpl.class);

    @Override
    public boolean createPharmacyRecord(Connection connection) {
        if (connection!=null) {
            String sqlString = sqlUtil.createPharmacyRecordTable();
            h2Service.makeStatement(connection, sqlString);
            sqlString=sqlUtil.createRecordTable();
            h2Service.makeStatement(connection, sqlString);
            sqlString=sqlUtil.createRxInfoTable();
            h2Service.makeStatement(connection, sqlString);
            logger.info("Pharmacy Record Table has been created");
            return true;
        }else {
            logger.severe("Pharmacy Record Table cannot be created");
            return false;
        }
    }

    @Override
    public boolean dropTable(Connection connection, String tableName) {
        if (connection!=null){
            String sqlString=sqlUtil.dropTable(tableName);
            h2Service.makeStatement(connection, sqlString);
            return true;
        }
        return false;
    }

    @Override
    public boolean addPharmacyRecordEntry(Connection connection, PharmacyRecord pharmacyRecord) {
        if (connection!=null){
            String sqlString=sqlUtil.insertPharmacyRecord(pharmacyRecord.getMemberId());
            h2Service.makePreparedStatement(connection, sqlString);
            logger.info("Pharmacy Record entry has been added");

            List<PharmacyRecord.Record> recordList=pharmacyRecord.getRecordList();
            for (PharmacyRecord.Record record:recordList){
                sqlString=sqlUtil.insertRecord(pharmacyRecord.getMemberId(), record.getField1(),record.getField2(), record.getField3());
                h2Service.makeStatement(connection, sqlString);

                List<PharmacyRecord.Record.RxInfo> rxInfoList=record.getRxInfoList();
                int i=0;
                for (PharmacyRecord.Record.RxInfo rxInfo: rxInfoList){
                    i++;
                    sqlString=sqlUtil.insertRxInfo(i, rxInfo.getRx(), rxInfo.getDescription());
                    h2Service.makeStatement(connection, sqlString);
                }
            }
            return true;
        }else {
            logger.severe("Pharmacy Record cannot be added");
            return false;
        }
    }

    @Override
    public boolean fetchPharmacyRecords(Connection connection) {
        if (connection!=null){
            String sqlString=sqlUtil.selectAllPharmacyRecord();
            h2Service.makeStatement(connection,sqlString);
            logger.info("fetched all Pharmacy Records");
            return true;
        }else{
            logger.severe("Pharmacy Records cannot be fetched");
            return false;
        }
    }

    @Override
    public List<PharmacyRecord> fetchAllPharmacyRecords(Connection connection) {
        return null;
    };
}
