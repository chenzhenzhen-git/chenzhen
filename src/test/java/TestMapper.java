import com.bonc.dxbrgrmp.DxbrgrmpApplication;
import com.bonc.dxbrgrmp.dao.mybatis.cloudmapper.TestdbMapper;
import com.bonc.dxbrgrmp.dao.mybatis.mapper.TWeatherCityCodeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: lgf
 * @Date: 2019/12/23
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DxbrgrmpApplication.class)
public class TestMapper {

    @Autowired
    TWeatherCityCodeMapper tw;
    @Autowired
    TestdbMapper tm;

    @Test
    public void test1(){
        System.out.println(tw.selectSiteRegionAndSityUrlCodeByRegionId(100000).getSityUrl());
    }

    @Test
    public void test2(){
        System.out.println(tm.selectDbAll().toString());
    }
}
