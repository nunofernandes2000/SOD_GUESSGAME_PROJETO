
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

    private Lock itemsLock;

    private Lock raffleLock;

    private int GuessNumber;

    private int min;

    private int max;


    private BufferedReader fileItemsBReader;

    private BufferedReader fileRaffleBReader;

    private BufferedWriter fileRaffleBWriter;

    private int itemNumberRaffle;

    public ServerThread(Socket clientSocket, int GuessNumber, String PATH, String loginFile, int min, int max) {

        this.clientSocket = clientSocket;
        this.GuessNumber = GuessNumber;
        this.PATH = PATH;
        this.loginFile = loginFile;
        this.min = min;
        this.max = max;
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

            //ciclo para verificar login + tentativas de login
            //TODO: Resolver o cóidigo, para que este, possa verificar se o utilizador já se encotra logado

            do {
                BufferedReader fileLoginBReader = new BufferedReader(new FileReader(this.PATH + this.loginFile ));
                String username = in.readLine();
                String password = in.readLine();

                line = "";
                while (line != null) {


                    line = fileLoginBReader.readLine();

                    String Login = username + ";" +password;


                    if (line == null) {
                        System.out.println("erro");
                        break;
                    } else
                        System.out.println("fafa");

                    if (line.equals(Login)) {
                        autenticacao = true;
                        out.println("Login efetuado com sucesso");
                        System.out.println("Login efetuado com sucesso");
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



            //TODO: corrigir bug do jogo do lado do serverThread
            //jogo lado do serverThread

            if (autenticacao == true) {
                out.println("Tempo de espera de login acabou!");
                out.println("Vamos começar jogar!");


            }

            while(autenticacao == true) {

                out.println("Bem vindo ao jogo, por favor tente adivinhar o numero secreto");
                out.println("O número secreto está entre o min: " +min + " e o max: " +max);
                out.println("Por favor insira um numero");
            }








        } catch (FileNotFoundException e) {
            System.err.println("Main: Erro de criação do buffer de leitura para o ficheiro ");


        } catch (IOException e) {
            System.err.println("Thread " + this.getName() + ": Erro de criação dos buffers do socket");
            System.exit(1);
        }


    }

}


