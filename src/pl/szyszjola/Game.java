package pl.szyszjola;

import java.util.Scanner;

public class Game {


    public static void main(String[] args) {

        Logic gameLogic = new Logic();
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i < 10; i++) {
            System.out.println(i + "/10 Prób. Zgadujesz tytuł : " + gameLogic.getHiddenTitle());
            String answer = scanner.nextLine();
            if(answer.equals(""))
            {
                i--;
                continue;
            }
            gameLogic.play(answer.charAt(0) +"");
            if(gameLogic.getRepeat()) {
                i--;
                continue;
            }
            if (gameLogic.getCorrect()) {
                i--;
                if(gameLogic.getWin())
                    break;
            }
                System.out.println("Podałeś " + i + " błędne litery: " + gameLogic.getWrongLetter());
        }
        System.out.println(gameLogic.gameResult());
    }

}
