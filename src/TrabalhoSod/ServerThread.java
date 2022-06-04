
package TrabalhoSod;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;


import static folha16.Buffer.min;
import static java.awt.SystemColor.text;

public class ServerThread extends Thread {

    private Socket clientSocket;
    private String PATH;
    private String loginFile;

    private Lock Lock1;

    private Lock LockLogin;

    private int GuessNumber;

    private int min;

    private int max;



    public ServerThread(Socket clientSocket, int GuessNumber, String PATH, String loginFile, int min, int max, Lock Lock1, Lock LockLogin) {

        this.clientSocket = clientSocket;
        this.GuessNumber = GuessNumber;
        this.PATH = PATH;
        this.loginFile = loginFile;
        this.min = min;
        this.max = max;
        this.Lock1 = Lock1;
        this.LockLogin = LockLogin;
    }

    @Override
    public void run() {

        try (
                PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

        ) {


            //###LOGIN###



            String line = "";
            int tentativas = 0;
            boolean autenticacao = false;
            String username = null;
            String password;

            //ciclo para verificar login + tentativas de login
            //TODO: Resolver o código, para que este, possa verificar se o utilizador já se encotra logado

            do {
                BufferedReader fileLoginBReader = new BufferedReader(new FileReader(this.PATH + this.loginFile ));
                username = in.readLine();
                password = in.readLine();

                line = "";
                while (line != null) {


                    line = fileLoginBReader.readLine();

                    String Login = username + ";" +password;


                    if (line == null) {
                        break;
                    } else

                    if (line.equals(Login)) {
                        autenticacao = true;
                        out.println("Login efetuado com sucesso");
                        System.out.println("Login efetuado com sucesso");
                        System.out.println("O jogador com username: " +username+ " efectou o login");
                        break;
                    }
                    else {
                        Login = username + ";" + password + ";1";
                        if(line.equals(Login)){
                            autenticacao = true;
                            out.println("Utilizador já existe");
                            System.out.println("Utilizador já existe");
                            break;
                        }
                    }
                }

                if(!autenticacao) {
                    System.out.println("Login incorreto, tente novamente" + tentativas);
                    tentativas ++;

                    if (tentativas < 3) {
                        out.println("Login incorreto, tente novamente");
                    }
                }


            } while( autenticacao == false && tentativas < 3);


            if(autenticacao == false && tentativas == 3){
                out.println("Tentativas excedidas");
                System.out.println("Tentativas excedidas");
            }

            if (autenticacao == true){
                synchronized(this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }


            //JOGO LADO DO SERVERTHREAD

            if (autenticacao == true) {


                //Mensagens enviadas para o cliente

                System.out.println("Vamos começar a jogar");
                out.println("Vamos começar a jogar!");
                System.out.println("O número que vai que adivinhar vai estar entre: " + min + " e " + max + ". Boa sorte!");
                out.println("O número que vai que adivinhar vai estar entre: " + min + " e " + max + ". Boa sorte!");
                System.out.println("Em qualquer momento do jogo, pode desistir se quiser, escrevendo 'desistir'");
                out.println("Em qualquer momento do jogo, pode desistir se quiser, escrevendo 'desistir'");

                do {
                    String RespostaCliente2; //variavel que guarda resposta do cliente
                    int guess2;
                    RespostaCliente2 = in.readLine(); //recebe a resposta do cliente
                    if (RespostaCliente2.equals("DESISTO")) {
                        System.out.println("O jogador: " +username + " desistiu");
                        break;
                    }
                    guess2 = Integer.parseInt(RespostaCliente2);
                    System.out.println("O jogador com username: " + username + " enviou o número: " + RespostaCliente2);


                    if (guess2 > GuessNumber){
                        System.out.println("O jogador  "+username+" escolheu o "+guess2+" sendo que o número a adivinhar é menor");
                        out.println("O número que vai que adivinhar é menor que o que escolheu");
                    } else if (guess2 < GuessNumber){
                        System.out.println("O jogador "+username+" escolheu o "+guess2+" sendo que o número a adivinhar é maior");
                        out.println("O número que vai que adivinhar é maior que o que escolheu");
                    } else if (guess2 == GuessNumber){
                        System.out.println("O jogador " +username+ " acertou no numero a adivinhar, parabéns!");
                        out.println("Parabéns, acertou!");
                        break;
                    }

                }while(autenticacao == true);
            }


        } catch (FileNotFoundException e) {
            System.err.println("Main: Erro de criação do buffer de leitura para o ficheiro ");


        } catch (IOException e) {
            System.err.println("Thread " + this.getName() + ": Erro de criação dos buffers do socket");
            System.exit(1);
        }


    }

}


