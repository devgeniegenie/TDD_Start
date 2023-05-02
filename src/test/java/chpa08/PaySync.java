package chpa08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

//before
/*public class PaySync {
    private PayInfoDao payInfoDao = new PayInfoDao();

    public void sync() throws IOException {
        Path path = Paths.get("D:\\testdata\\pay\\cp0001.csv");
        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    PayInfo payInfo = new PayInfo(data[0], data[1], Integer.parseInt(data[2])
                    );
                    return payInfo;
                })
                .collect(Collectors.toList());
        payInfos.forEach(pi -> payInfoDao.insert(pi));
    }
}*/

//after
public class PaySync {
    private String filePath = "D:\\testdata\\pay\\cp0001.csv";
    private PayInfoDao payInfoDao = new PayInfoDao();

    //private PayInfoDao payInfoDao = new PayInfoDao();
    //의존 대상은 주입 받을 수 있는 수단을 제공해서 교체할 수 있도록 한다(생성자 or setter(많은 레거시 코드에서 생성자 없는 버전 사용하고 있을때))
    public PaySync(PayInfoDao payInfoDao) {
        this.payInfoDao = payInfoDao;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //메서드를 실행할 때 인자로 전달받게 수정해도 좋음
    //public void sync(String filePath) throws IOException {
    public void sync() throws IOException {
        Path path = Paths.get(filePath);
        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    PayInfo payInfo = new PayInfo(data[0], data[1], Integer.parseInt(data[2])
                    );
                    return payInfo;
                })
                .collect(Collectors.toList());
        payInfos.forEach(pi -> payInfoDao.insert(pi));
    }
}
