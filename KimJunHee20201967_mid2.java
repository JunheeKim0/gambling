import java.util.Scanner;                                 // 화면으로부터 데이터를 입력받는 기능을 제공하는 클래스

public class KimJunHee20201967_mid2{
    private static final int LEVEL = 10;                  // 값 불변

    private static int GOAL;                              // 목표 금액
    private static int MONEY;                             // 입력 금액
    private static int BET;                               // 베팅 금액
    private static int player;                            // 사용자의 가위 바위 보 기록 변수
    private static int computer;                          // 컴퓨터의 가위 바위 보 기록 변수
 
    private static int WIN = 0;                           // 이긴 횟수 저장 변수
    private static int LOSE = 0;                          // 진 횟수 저장 변수

    public static void main(String[] args) {              // main 문
        Put();                                            // 사용자로부터 총 금액을 입력받고, 목표 금액을 계산하여 출력 

        while (true) {
            Player_bet();                                 // 베팅 금액 입력
            if (BET == 0) break;                          // 0원을 입력하면 종료
            MukJJi();                                     // 묵찌빠 경기

            Player_status();                              // 묵찌빠 경기 횟수, 승 패 횟수 출력
             
            if (MONEY <= 0) break;                        // 도박으로 돈을 모두 잃으면 파산하며 게임 종료
            if (MONEY >= GOAL) GameClear();               // 목표 금액을 넘으면 목표를 달성하며 게임 종료
        }
     GameOver();

    }
                                          
    private static void Put() {
        Scanner scanner = new Scanner(System.in);          // 객체 생성

        System.out.println("====================================");
        System.out.println(" 묵 찌 빠 게임!!");
        System.out.println("====================================");

        System.out.println(" 돈을 넣어주세요.");
        System.out.println();
        System.out.print(">>> $");
        MONEY = scanner.nextInt();                           // 총 금액 입력

        System.out.println("------------------------------------");

        GOAL = MONEY * LEVEL;                                // 목표 금액 계산
        System.out.printf(" $%d 을 모으면 승리!\n", GOAL);
    }

    private static void Player_bet() {                          // 베팅값 정하는 메소드
        Scanner scanner = new Scanner(System.in);

        System.out.println("         ");
        System.out.println(" 얼마를 베팅하시겠습니까?");
        System.out.println(" ($0을 베팅하면 종료)");
        System.out.printf(" 현재 보유 자산 : $%d/$%d\n\n", MONEY, GOAL);

        System.out.print(">>> $");
        BET = scanner.nextInt();                                          // 베팅값을 사용자로부터 입력받음
 
        if (MONEY - BET < 0) {                                            // 총 금액보다 베팅값이 더 클 때
            System.out.println("------------------------------------");
            System.out.println(" 감당 못하실 금액입니다.");              
            Player_bet();                                                 // 다시 입력받음
        } 
    }

    static int getPlayerInput(Scanner input) {                // 사용자의 가위바위보 선택
        System.out.println("(1:주먹, 2:가위, 3:보)");
        System.out.print("어떤걸 낼까... ");
        int choice = input.nextInt();                         // 사용자로부터 숫자(1~3)를 입력받고 choice에 저장.
        if (choice < 1 || choice > 3) {                       // 입력값 범위(1~3) 벗어나면 다시 입력받음
            System.out.println("잘못된 입력");
            return getPlayerInput(input);
        }
    
        return choice;
    }


    static void display(int player, int computer) {            // getPlayerChoice가 반환한 string값을 표현해 플레이어와 컴퓨터의 선택 출력
        System.out.println("플레이어: " + getPlayerChoice(player));
        System.out.println("컴퓨터: " + getPlayerChoice(computer));
    }

    static String getPlayerChoice(int choice) {                 // 숫자로 저장된 choice값에 따라 주먹 가위 보를 출력
        switch (choice) { 
            case 1:
                return "주먹";
            case 2:
                return "가위";
            default:
                return "보";
        }
    }

    static int judgement(int player, int computer) {             // 공격권 판단
        if (player == 1) {                                       // 사용자가 주먹을 냈을 때
            if (computer == 2)                                   // 컴퓨터가 가위 내면
                return 1;                                        // 사용자가 공격권을 가짐
            else                                                 // 그 이외에는 컴퓨터가 공격권을 가짐
                return 0;
        } else if (player == 2) {                                // 사용자가 가위를 냈을 때
            if (computer == 3)                                   // 컴퓨터가 보를 내면
                return 1;                                        // 사용자가 공격권을 가짐
            else                                                 // 그 이외에는 컴퓨터가 공격권을 가짐                                        
                return 0;
        } else if (player == 3) {                                // 사용자가 보를 냈을 때
            if (computer == 1)                                   // 컴퓨터가 주먹을 내면
                return 1;                                        // 사용자가 공격권을 가짐
            else                                                 // 그 이외에는 컴퓨터가 공격권을 가짐
                return 0;
        } else {                                                 // if else if else 형태를 만들어주기 위해 
            return 0;
        }
    }

    private static void Player_status() {                                  // 묵찌빠 경기 횟수, 승 패 횟수 출력
        System.out.println("------------------------------------");
        System.out.printf("현재 전적 : %d 회", (WIN + LOSE));

        System.out.println();
        System.out.printf(" %d 승, %d 패", WIN, LOSE);
    }

    static void MukJJi() {
        int attacker;                                                      // 공격권 상태 변수
        int result = 0;                                                    // 최종 묵찌빠에서 승리했을 시 반복문 빠져나오기 위한 변수

        Scanner input = new Scanner(System.in);                            // 객체 생성     

        do {
            System.out.println("\n가위~ 바위~ 보~!");
            player = getPlayerInput(input);                                // 주먹 : 1, 가위 : 2, 보 : 3
            computer = (int) (Math.random() * 3) + 1;                      // 0 이상 1 미만의 난수를 생성 후 3을 곱함. 소수점 이하를 버려 정수로 변환하고 1을 더함 
                                                                           // -> 1,2,3 값 중 하나를 가지게 됨
            display(player, computer);                                     // 가위 바위 보 선택 출력

            if (player != computer)                                        // 공격권을 가질 때까지 실행
                break;
        } while (true); 

        attacker = judgement(player, computer);                            // 사용자가 공격권을 가지면 1, 컴퓨터가 가지면 0을 반환 

        do {
            switch (attacker) {
                case 1:                                                     // 사용자가 공격권을 가진다면
                    System.out.println("이기고 있습니다!");

                    break;
                case 0:
                    System.out.println("지고 있지만 뒤집을 수 있습니다.");   // 컴퓨터가 공격권을 가진다면
                    break;
            }

            player = getPlayerInput(input);                                  // 주먹 : 1, 가위 : 2, 보 : 3
            computer = (int) (Math.random() * 3) + 1;                        // 컴퓨터 난수 생성

            display(player, computer);                                       // 가위 바위 보 선택 출력

            if (player != computer)                                          // 비길 때까지 계속 공격권 반환
                attacker = judgement(player, computer);
            else                                                             // 비기면 result가 1을 가지며 while을 빠져나옴.
                result = 1;
        } while (result == 0);

        if (attacker == 1) {                                                 // 사용자가 공격권을 가진 상태에서 while을 빠져나왔다면
            System.out.println("플레이어 승리");
            WIN++;                                                           // WIN(승리 횟수)변수 1더함.
            MONEY += BET;                                                    // 베팅한 금액을 얻음
    
            System.out.printf(" 현재 보유 자산 : $%d/$%d\n\n", MONEY, GOAL);    // 총 금액과 목표 금액을 출력
         } else {                                                            // 컴퓨터가 공격권을 가진 상태에서 while을 빠져나왔다면 ( = attacker가 0이라면 )
            System.out.println("플레이어 패배");
            LOSE++;                                                          // LOSE(패배 횟수)변수 1더함.
            MONEY -= BET;                                                    // 베팅한 금액만큼 잃음
      
            System.out.printf(" 현재 보유 자산 : $%d/$%d\n\n", MONEY, GOAL);
        }

    }

    private static void GameClear() {                                        // 목표를 이뤘을 시 출력할 문장
        System.out.println("====================================");
        System.out.println("목표를 이루었습니다!");
        System.out.println("이제 부자입니다!");
    }

    private static void GameOver() {
        System.out.println("====================================");         // 총 금액을 다 잃었을 시 출력할 문장
        System.out.println("파산하셨습니다.");
    }

}

   



