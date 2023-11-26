import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        BigInteger b1 = new BigInteger("99999999999999999999999999999999");
        BigInteger b2 = new BigInteger("9999999999999999999999999999999999999999");

        BigInteger prod = b1.multiply(b2);
        System.out.println(prod);

        MyBigInteger mb1 = new MyBigInteger("99999999999999999999999999999999");
        MyBigInteger mb2 = new MyBigInteger("9999999999999999999999999999999999999999");

        MyBigInteger mprod = mb1.multiply(mb2);

        System.out.println(mprod);

        System.out.println(prod.toString().equals(mprod.toString()));

        MyBigInteger mbadd1 = new MyBigInteger("54");
        MyBigInteger mbadd2 = new MyBigInteger("43");

        System.out.println(mbadd1.add(mbadd2));
    }
}