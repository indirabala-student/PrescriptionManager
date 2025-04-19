package com.module.model;

import java.util.List;

public class PharmacyRecord {
    private int memberId;
    private List<Record> recordList;

    public static class Record{
        private String field1;
        private String field2;
        private String field3;
        private List<RxInfo> rxInfoList;

        public static class RxInfo{
            private String rx;
            private String description;

            public RxInfo() {
            }

            public RxInfo(String rx, String description) {
                this.rx = rx;
                this.description = description;
            }

            public String getRx() {
                return rx;
            }

            public void setRx(String rx) {
                this.rx = rx;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            @Override
            public String toString() {
                return "RxInfo{" +
                        "rx='" + rx + '\'' +
                        ", description='" + description + '\'' +
                        '}';
            }
        }

        public Record() {
        }

        public Record(String field1, String field2, String field3, List<RxInfo> rxInfoList) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.rxInfoList = rxInfoList;
        }

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }

        public String getField3() {
            return field3;
        }

        public void setField3(String field3) {
            this.field3 = field3;
        }

        public List<RxInfo> getRxInfoList() {
            return rxInfoList;
        }

        public void setRxInfoList(List<RxInfo> rxInfoList) {
            this.rxInfoList = rxInfoList;
        }

        @Override
        public String toString() {
            return "Record{" +
                    "field1='" + field1 + '\'' +
                    ", field2='" + field2 + '\'' +
                    ", field3='" + field3 + '\'' +
                    ", rxInfoList=" + rxInfoList +
                    '}';
        }
    }

    public PharmacyRecord() {
    }

    public PharmacyRecord(int memberId, List<Record> recordList) {
        this.memberId = memberId;
        this.recordList = recordList;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override
    public String toString() {
        return "PharmacyRecord{" +
                "memberId=" + memberId +
                ", recordList=" + recordList +
                '}';
    }
}
