package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        //AnnotationConfigApplicationContext 쓰면 @Component 안붙여줘도 됨
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeBean1 : " + prototypeBean1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeBean2 : " + prototypeBean2);
        //isSameAs == 비교
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);


        //destroy안되니까 수동으로..
        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }

    //AnnotationConfigApplicationContext 쓰면 @Component 안붙여줘도 됨
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
