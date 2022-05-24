package folha15;

public class Transactions {

    public static void main(String[] args) {

        BankAccount balance = new BankAccount(1000);

        Thread t1 = new SpendMoney("t1", balance);
        Thread t2 = new SpendMoney("t2", balance);

        t1.start();
        t2.start();
    }
}
