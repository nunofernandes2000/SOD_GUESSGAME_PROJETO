
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
                            out.println("Utilizador j?? existe");
                            System.out.println("Utilizador j?? existe");
                            break;
                        }
                    }
                }

                if(!autenticacao) {
                    System.out.println("Login incorreto, tente novamente" + " n??mero de tentativas -> " + tentativas);
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


                out.println("Vamos come??ar a jogar!");
                out.println("O n??mero que vai que adivinhar vai estar entre: " + min + " e " + max + ". Boa sorte!");
                out.println("Em qualquer momento do jogo, pode desistir se quiser, escrevendo 'desisto'");

                do {
                    String RespostaCliente2; //variavel que guarda resposta do cliente
                    int guess2;

                    RespostaCliente2 = in.readLine(); //recebe a resposta do cliente

                    if (RespostaCliente2.equals("DESISTO")) {
                        System.out.println("O jogador: " +username + " desistiu");
                        break;
                    }


                    guess2 = Integer.parseInt(RespostaCliente2);
                    System.out.println("O jogador com username: " + username + " enviou o n??mero: " + RespostaCliente2);



                    if (Shared.GuessWinner.length() > 2){
                        out.println(Shared.GuessWinner);
                        break;
                    } else if (Shared.TimeEnd == true) {
                        out.println("Acabou o jogo");

                    }
                    else if (guess2 > GuessNumber){
                        System.out.println("O jogador  "+username+" escolheu o "+guess2+" sendo que o n??mero a adivinhar ?? menor");
                        out.println("O n??mero que vai que adivinhar ?? menor que o que escolheu");
                    } else if (guess2 < GuessNumber){
                        System.out.println("O jogador "+username+" escolheu o "+guess2+" sendo que o n??mero a adivinhar ?? maior");
                        out.println("O n??mero que vai que adivinhar ?? maior que o que escolheu");
                    } else if (guess2 == GuessNumber){
                        System.out.println("O jogador " +username+ " acertou no numero a adivinhar, parab??ns!");
                        Shared.GuessWinner = "O jogador com username: " + username + " Acertou no numero" + " e o n??mero correto era: " + GuessNumber;
                        out.println("Parab??ns, acertou!");
                        break;
                    }


                }while(autenticacao == true);
            }


        } catch (FileNotFoundException e) {
            System.err.println("Main: Erro de cria????o do buffer de leitura para o ficheiro ");


        } catch (IOException e) {
            System.err.println("Thread " + this.getName() + ": Erro de cria????o dos buffers do socket");
            System.exit(1);
        }


    }

}


