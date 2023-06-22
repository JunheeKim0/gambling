import java.util.Scanner;                           // 화면으로부터 데이터를 입력받는 기능을 제공하는 클래스

public class KimJunHee20201967_mid1{
    public static void main(String[] args) {
        int CPU;                                    // 컴퓨터의 가위 바위 보 기록 변수
        int USER;                                   // 사용자의 가위 바위 보 기록 변수

        Scanner scanner = new Scanner(System.in);   // scanner class를 사용하기 위한 객체 선언
 
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("무엇을 내시겠습니까?\n");
            System.out.println("1. 가위");
            System.out.println("2. 바위");
            System.out.println("3. 보");
            System.out.print(">>> ");
            USER = scanner.nextInt();                                     // 사용자의 가위바위보 선택 입력받음

            if (USER != 1 && USER != 2 && USER != 3) {                    // and
                System.out.println("------------------------------------");
                System.out.println("올바르지 않은 입력입니다!");
                continue;
            }

            CPU = (int) (Math.random() * 3) + 1;               // 0 이상 1 미만의 난수를 생성 후 3을 곱함. 소수점 이하를 버려 정수로 변환하고 1을 더함 

            System.out.println("------------------------------------");
            System.out.print("컴퓨터는 ");

            switch (CPU) {
                case 1:
                    System.out.print("가위");
                    break;
                case 2:
                    System.out.print("바위");
                    break;
                case 3:
                    System.out.print("보");
                    break;
            }

            System.out.print("를, 당신은 ");

            switch (USER) {
                case 1:
                    System.out.print("가위");
                    break;
                case 2:
                    System.out.print("바위");
                    break;
                case 3:
                    System.out.print("보");
                    break;
            }

            System.out.println("를 냈습니다.\n");

            if (USER == CPU) {
                System.out.println("비겼다.");
            } else if ((USER == 1 && CPU == 3) || (USER == 2 && CPU == 1) || (USER == 3 && CPU == 2)) {
                System.out.println("이겼다!!!");
            } else {
                System.out.println("졌다...");
            }

            System.out.println("------------------------------------");
            int continueGame;                              

            while (true) {
                System.out.println("한 세트 게임이 끝났습니다. 게임을 계속하시겠습니까? (0: 종료, 1: 계속)");
                System.out.print(">>> ");
                continueGame = scanner.nextInt();                    //사용자로부터 0,1값 입력받음
                if (continueGame == 0 || continueGame == 1) {
                    break;
                } else {
                    System.out.println("------------------------------------");
                    System.out.println("올바르지 않은 입력입니다!");
                }
            }

            if (continueGame == 0) {
                System.out.println("------------------------------------");
                System.out.println("게임을 종료합니다.");
                break;
            }
        }
    }
    }

