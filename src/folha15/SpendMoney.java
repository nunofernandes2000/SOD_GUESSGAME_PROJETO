package folha15;

public class SpendMoney extends Thread {

    public BankAccount bankAccount;

    public SpendMoney(String name, BankAccount balance) {

        this.bankAccount = balance;
        this.setName(name);
    }

    @Override
    public void run() {

        int oldBalance;
        int transaction;

        while (true) {

            transaction = (int) (Math.random() * 100 + 1);

            // tem de se colocar como argumento o objeto ao qual o acesso tem de ser controlado
            synchronized (bankAccount) {

                oldBalance = bankAccount.getBalance();

                if (oldBalance >= 0) {
                    bankAccount.withdrawMoney(transaction);

                    System.out.println("O saldo mudou de " + oldBalance + "€ para " + bankAccount.getBalance() + "€ por levantamento de " + transaction + "€ pela thread " + this.getName());

                    try {
                        Thread.sleep(50);

                    } catch (InterruptedException e) {
                        System.out.println("Erro de interrupção");
                    }

                } else {
                    return;
                }
            }
        }
    }
}
