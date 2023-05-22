import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class tictacktoevanisha
{
    static ArrayList<Integer> playerpositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpupositions = new ArrayList<Integer>();
    static char[][] gameboard={
        {' ','|',' ','|',' '},
        {'-','+','-','+','-'},
        {' ','|',' ','|',' '},
        {'-','+','-','+','-'},
        {' ','|',' ','|',' '}
    };
    //global vaiables
    public static char playersymbol1, playersymbol2;
    public static String player1,player2,conti="y";
    public static int choice;
    
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        char[][] gameboardpos={
            {'1','|','2','|','3'},
            {'-','+','-','+','-'},
            {'4','|','5','|','6'},
            {'-','+','-','+','-'},
            {'7','|','8','|','9'}
        };

        System.out.print("\n---------------Menu---------------\n1 - One Player Game\n2 - Two Player Game\n3 - Exit Application\n\nChoose any one option - ");
        choice=scan.nextInt();
        switch(choice)
        {
            case 1:
            {   
                System.out.print("\nEnter player name - ");
                player1=scan.next();
                System.out.print("Choose a symbol for "+player1+" - ");
                playersymbol1 = scan.next().charAt(0);
                playersymbol2 = (playersymbol1 == 'X') ? 'O' : 'X';
                /*if(playersymbol1 == 'X')
                    playersymbol2 = 'O';
                else
                    playersymbol2 ='X';*/
                System.out.println("\n---------------Details---------------\n"+player1+"-"+playersymbol1+"\nCPU-"+playersymbol2);
                
                break;
            }
            case 2:
            {
                System.out.print("\nEnter player 1 name - ");
                player1=scan.next();
                System.out.print("Choose a symbol for "+player1+" - ");
                playersymbol1 = scan.next().charAt(0);
                System.out.print("\nEnter player 2 name - ");
                player2=scan.next();
                System.out.print("Choose a symbol for "+player2+" - ");
                playersymbol2 = scan.next().charAt(0);
                System.out.println("\n---------------Details---------------\n"+player1+"-"+playersymbol1+"\n"+player2+"-"+playersymbol2);
                break;
            }
            case 3: 
            {
                System.out.println("Ok exiting...");
                return;
            }
        }

        System.out.println("\nThe game board is ");
                printgameboard(gameboardpos);//showing the game board with positions
                System.out.println();
                gameprocess();
                do 
                {
                    System.out.println("Do you want to continue ? (yes=y/no=n)");
                    conti=scan.next();
                    if(conti.equalsIgnoreCase("y"))
                        {
                            resetboard();
                            printgameboard(gameboardpos);
                            gameprocess();
                        }
                } while (conti.equalsIgnoreCase("y"));
                
}//closing main

    public static void gameprocess()
    {
        Scanner scan = new Scanner(System.in);
        while(true)
        {//game loop

            System.out.println("\nEnter your placement (1-9) "+player1+" - ");
            int playerpos = scan.nextInt();

                while(playerpositions.contains(playerpos) || cpupositions.contains(playerpos))
                    {   
                        System.out.println("Postion already taken :(\nEnter a correct postion");
                        playerpos = scan.nextInt();
                    }
            placepiece(gameboard,playerpos,"player");
            printgameboard(gameboard);

            String result = checkwinner();
            System.out.println();
            if(result.length()>0)
                {
                System.out.println(result);
                break;
                }
            int cpupos;
            Random rand=new Random();
            if(choice==1)
            {
                cpupos = rand.nextInt(9)+1;
            }
            else
            {
                System.out.println("\nEnter your placement (1-9)  "+player2+" - ");
                cpupos = scan.nextInt();
            }

            while(playerpositions.contains(cpupos) || cpupositions.contains(cpupos))   
                {
                cpupos = rand.nextInt(9) + 1;
                }

            placepiece(gameboard,cpupos,"CPU");

            printgameboard(gameboard);
            
            result = checkwinner();
            if(result.length()>0)
                {
                System.out.println(result);
                break;
                }
        }//closing while
    }
//resetting the board
public static void resetboard() 
{
    playerpositions.clear();
    cpupositions.clear();

    // Create a new game board array and assign it to the gameBoard variable
    gameboard = new char[][]{
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}
    };
}

//printing the gameboard
    public static void printgameboard(char[][] gameboard)
     {
        System.out.println();
       for(char[] row: gameboard)
        {
            for(char c: row)
            {
                System.out.print(c);
            }
        System.out.println();
        }  
     }//closing printgamefn

     
    public static void placepiece(char[][] gameboard,int pos, String user)
    {
       
        char symbol=' ';
        if(user.equals("player"))
                {
                    symbol=playersymbol1;
                    playerpositions.add(pos);
                }
        else if(user.equals("CPU"))// we can change it to player 2 and take input from user or ai or random
                {
                    symbol=playersymbol2;
                    cpupositions.add(pos);
                }
        switch(pos)
        {
            case 1:
            gameboard[0][0]=symbol;
            break;
            case 2:
            gameboard[0][2]=symbol;
            break;
            case 3:
            gameboard[0][4]=symbol;
            break;
            case 4:
            gameboard[2][0]=symbol;
            break;
            case 5:
            gameboard[2][2]=symbol;
            break;
            case 6:
            gameboard[2][4]=symbol;
            break;
            case 7:
            gameboard[4][0]=symbol;
            break;
            case 8:
            gameboard[4][2]=symbol;
            break;
            case 9:
            gameboard[4][4]=symbol;
            break;
            default : 
            {
            System.out.println("no such placement on the game board as entered");
            break;
            }
        }//switch closing
        
    }//closing piece placement fn

    public static String checkwinner() {
        List<Integer> toprow = Arrays.asList(1, 2, 3);
        List<Integer> midrow = Arrays.asList(4, 5, 6);
        List<Integer> botrow = Arrays.asList(7, 8, 9);
    
        List<Integer> leftcol = Arrays.asList(1, 4, 7);
        List<Integer> midcol = Arrays.asList(2, 5, 8);
        List<Integer> rightcol = Arrays.asList(3, 6, 9);
    
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);
    
        List<List<Integer>> winning = new ArrayList<List<Integer>>();
        winning.add(toprow);
        winning.add(midrow);
        winning.add(botrow);
        winning.add(leftcol);
        winning.add(midcol);
        winning.add(rightcol);
        winning.add(cross1);
        winning.add(cross2);
    
        for (List<Integer> l : winning) {
            if (playerpositions.containsAll(l)) {
                return "Congrats! " + player1 + " won this round!";
            } else if (cpupositions.containsAll(l)) {
                return "CPU won this round!";
            }
        }
    
        if (playerpositions.size() + cpupositions.size() == 9) {
            return "It's a tie! Good match :)";
        }
    
        return "";
    }
}