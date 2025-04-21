import com.module.manager.PharmacyManager;
import com.module.model.PharmacyRecord;
import com.module.service.IPharmacyService;
import com.module.service.impl.PharmacyServiceImpl;

import java.util.List;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        /*--------- in-memory DB connection metadata ---------*/
        String jdbcUrl = "jdbc:h2:mem:testdb";
        String username = "sa";
        String password = "";


        PharmacyManager pharmacyManager=new PharmacyManager();

        List<PharmacyRecord> pharmacyRecordList;
        pharmacyRecordList=pharmacyManager.processJSONFileToPharmacyRecord("records.json");

        IPharmacyService pharmacyService=new PharmacyServiceImpl();
        pharmacyService.mapToH2Database(pharmacyRecordList);
    }
}
