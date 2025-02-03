package core.spring.scan;

import core.spring.AutoAppConfig;
import core.spring.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberServiceImpl bean = ac.getBean(MemberServiceImpl.class);
        assertThat(bean).isInstanceOf(MemberServiceImpl.class);

    }
}
