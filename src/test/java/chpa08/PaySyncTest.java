package chpa08;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaySyncTest {
    private MemoryPayInfoDao memoryPayInfoDao = new MemoryPayInfoDao();

    @Test
    void allDataSaved() throws IOException {
        PaySync paySync = new PaySync(memoryPayInfoDao);
        paySync.setFilePath("src/test/resources/c0111.csv");
        paySync.sync();

        //대역을 이용한 결과 검증
        List<PayInfo> savedInfos = memoryPayInfoDao.getAll();
        assertEquals(2, savedInfos.size());
    }
}
