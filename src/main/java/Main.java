import com.module.manager.PharmacyManager;
import com.module.model.PharmacyRecord;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PharmacyManager pharmacyManager=new PharmacyManager();

        List<PharmacyRecord> pharmacyRecordList=new ArrayList<>();
        pharmacyRecordList=pharmacyManager.processJSONFileToPharmacyRecord("records.json");
        for (PharmacyRecord pharmacyRecord:pharmacyRecordList) {
            System.out.println(pharmacyRecord);
        }
    }
}
