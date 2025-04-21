package com.module.service.impl;

import com.module.dao.IPharmacyRecordDAO;
import com.module.dao.impl.PharmacyRecordDAOImpl;
import com.module.model.PharmacyRecord;
import com.module.model.RecordEntry;
import com.module.service.IH2Service;
import com.module.service.IPharmacyService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PharmacyServiceImpl implements IPharmacyService {

    IH2Service h2Service=new H2ServiceImpl();
    IPharmacyRecordDAO pharmacyRecordDAO=new PharmacyRecordDAOImpl();

    @Override
    public List<RecordEntry> mapObjectsToRecordEntryList(List<Object> objectList) {
        return List.of();
    }

    @Override
    public List<PharmacyRecord> mapToPharmacyRecordList(List<RecordEntry> recordEntryList) {

        /*------------ group by memberId ----------*/
        Map<Integer, List<RecordEntry>> groupedByMember = recordEntryList.stream()
                .collect(Collectors.groupingBy(RecordEntry::getMemberId));

        List<PharmacyRecord> pharmacyRecords = new ArrayList<>();

        for (Map.Entry<Integer, List<RecordEntry>> entry : groupedByMember.entrySet()) {
            int memberId = entry.getKey();
            List<RecordEntry> records = entry.getValue();

            /*------------ Group records by unique Record fields (field1, field2, field3) ----------*/
            Map<String, List<RecordEntry>> groupedByFields = records.stream()
                    .collect(Collectors.groupingBy(rd -> rd.getField1() + "|" + rd.getField2() + "|" + rd.getField3()));

            List<PharmacyRecord.Record> recordList = new ArrayList<>();

            for (Map.Entry<String, List<RecordEntry>> fieldGroup : groupedByFields.entrySet()) {
                List<RecordEntry> relatedDetails = fieldGroup.getValue();

                String[] fields = fieldGroup.getKey().split("\\|");

                List<PharmacyRecord.Record.RxInfo> rxInfos = relatedDetails.stream()
                        .map(rd -> new PharmacyRecord.Record.RxInfo(rd.getRx(), rd.getDescription()))
                        .collect(Collectors.toList());

                PharmacyRecord.Record record = new PharmacyRecord.Record(
                        fields[0],
                        fields[1],
                        fields[2],
                        rxInfos
                );
                recordList.add(record);
            }
            pharmacyRecords.add(new PharmacyRecord(memberId, recordList));
        }
        return pharmacyRecords;
    }

    @Override
    public boolean mapToH2Database(List<PharmacyRecord> pharmacyRecordList) {
        try (Connection connection = h2Service.runH2DB()) {
            if (connection != null && pharmacyRecordDAO.createPharmacyRecord(connection)) {
                for (PharmacyRecord record : pharmacyRecordList) {
                    pharmacyRecordDAO.addPharmacyRecordEntry(connection, record);
                }
                pharmacyRecordDAO.fetchAllPharmacyRecords(connection);
                return true;
            }
            return false;
        }catch (SQLException exception){
            throw new RuntimeException();
        }
    }
}
