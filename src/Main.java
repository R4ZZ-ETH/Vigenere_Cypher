import java.util.Scanner;
//?Jjq, ricordare questo per poi provare a decriptarlo
public class Main {
    Scanner input = new Scanner(System.in);
    public Main(){
        System.out.println("Input the message:");
        String message = input.nextLine();
        System.out.println("Encrypt or decrypt?");
        Cypher cypher = new Cypher("3591255939063529", "NCajwAdDdcDCakcC");
        int scelta = input.nextInt();
        switch(scelta){
            case 1:               
                System.out.println("The encrypted message is: " + cypher.encrypt(message));
                break;
            case 2:
                System.out.println("The decrypted message is: " + cypher.decrypt(message));
                break;
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}
