package hello.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        //this.price = price; //여기가 문제임!
        //가격을 바로 리턴시킴
        return price;
    }

    /*public int getPrice() {
        return price;
    }*/
}
