package hello.core.lifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    //접속할 url
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        /*connect();
        call("초기화 연결 메시지"); 애프터~로*/
    }

    //외부에서 넣도록
    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: "+url);
    }

    //연결이 된 상태에서 call을 부를 수 있다고 가정.. 연결한 서버에 메시지를 보냄
    public void call(String message) {
        System.out.println("call: "+ url + "message =" + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

/*
    //의존관계(프로퍼티즈)설정이 끝나면
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }
*/

/*    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
*/
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }

}
