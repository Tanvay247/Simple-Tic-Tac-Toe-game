import java.util.*;

public class TicTacToe {

    static List playerPosition = new ArrayList<>();
    static List cpuPosition = new ArrayList<>();

    public static void printGameBoard(char[][] gameboard) {
        for(char[] r: gameboard) {
            for(char c: r) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placeInput(char[][] gameboard, int pos, String user) {
        char symbol = 'X';

        if (user.equals("player")) {
            symbol = 'X';
            playerPosition.add(pos);
        }
        else if (user.equals("CPU")) {
            symbol = 'O';
            cpuPosition.add(pos);
        }

        switch (pos) {
            case 1:     gameboard[0][0] = symbol;           
                break; 
            case 2:     gameboard[0][2] = symbol;                
                break;        
            case 3:     gameboard[0][4] = symbol;                
                break;        
            case 4:     gameboard[2][0] = symbol;                
                break;        
            case 5:     gameboard[2][2] = symbol;                
                break;        
            case 6:     gameboard[2][4] = symbol;                
                break;        
            case 7:     gameboard[4][0] = symbol;                
                break;        
            case 8:     gameboard[4][2] = symbol;               
                break;        
            case 9:     gameboard[4][4] = symbol;                
                break;        
            default: System.out.println("Invalid Input Entered!!!");
                break;
        }
    }

    public static String checkWinner() {
        List toprow =  Arrays.asList(1,2,3);
        List midrow =  Arrays.asList(4,5,6);
        List bottomrow =  Arrays.asList(7,8,9);
        List leftcol =  Arrays.asList(1,4,7);
        List midcol =  Arrays.asList(2,5,8);
        List rightcol =  Arrays.asList(3,6,9);
        List leftdiag =  Arrays.asList(1,5,9);
        List rightdiag =  Arrays.asList(3,5,7);

        List<List> win = new ArrayList<List>();
        win.add(toprow);
        win.add(midrow);
        win.add(bottomrow);
        win.add(leftcol);
        win.add(midcol);
        win.add(leftdiag);
        win.add(rightdiag);

        for(List l: win) {
            if (playerPosition.containsAll(l)) {
                return "PLAYER WON";
            }
            else if (cpuPosition.containsAll(l)) {
                return "CPU WON";
            }
            else if (playerPosition.size() + cpuPosition.size() == 9) {
                return "TIE";
            }
        }
        return "";
    }

    public static void main(String[] args) {
        char[][] gameboard = {{' ', '|', ' ', '|', ' '},
                            {'-', '+', '-','+', '-'},
                            {' ', '|', ' ', '|', ' '},
                            {'-', '+', '-','+', '-'},
                            {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameboard);
        
        while(true) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter your placing (1-9)");
            int playerpos = sc.nextInt();
            while (playerPosition.contains(playerpos) || cpuPosition.contains(playerpos)) {
                System.out.println("Place is taken\nSelect a different position:- ");
                playerpos = sc.nextInt();
            }
            placeInput(gameboard, playerpos, "player");
            String result = checkWinner();
            if (result.length()>0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpupos = rand.nextInt(9) + 1;
            while (playerPosition.contains(cpupos) || cpuPosition.contains(cpupos)) {
                cpupos = rand.nextInt(9) + 1;
            }
            placeInput(gameboard, cpupos, "CPU");

            printGameBoard(gameboard);

            result = checkWinner();
            if (result.length()>0) {
                System.out.println(result);
                break;
            }
        }
        
    }
}
