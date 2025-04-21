package com.module.dao;

import com.module.model.PharmacyRecord;

import java.sql.Connection;
import java.util.List;

public interface IPharmacyRecordDAO {

    public boolean createPharmacyRecord(Connection connection);

    public boolean dropTable(Connection connection, String tableName);

    public boolean addPharmacyRecordEntry(Connection connection, PharmacyRecord pharmacyRecord);

    public boolean fetchPharmacyRecords(Connection connection);

    public List<PharmacyRecord> fetchAllPharmacyRecords(Connection connection);
}
