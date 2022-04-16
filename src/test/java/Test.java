
import com.yy.service.HomeServiceImpl;
import com.yy.service.VipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author young
 * @date 2022/4/14
 */
public class Test {
    @Autowired
    private HomeServiceImpl homeService;
    @Autowired
    private VipServiceImpl vipService;
    @org.junit.Test
    public void test(){
        System.out.println(homeService.queryHomeById(1));

    }
}
