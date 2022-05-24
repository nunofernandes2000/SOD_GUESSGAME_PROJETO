package folha9.ex2;

public class ThreadPessoa extends Thread {
    private Pessoa pessoa;

    public ThreadPessoa(String nome, int idade) {
        this.pessoa = new Pessoa(nome, idade);
    }

    public void add1ToIdade() {
        this.pessoa.setIdade(this.pessoa.getIdade() + 1);
    }

    @Override
    public void run() {
        System.out.println("A pessoa " + this.pessoa.getNome() + " tem " + this.pessoa.getIdade());
    }
}
