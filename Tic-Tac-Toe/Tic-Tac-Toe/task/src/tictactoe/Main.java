package tictactoe;


import javax.swing.plaf.nimbus.State;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Field field = new Field();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Integer[] coords;
        String[] moves = {"X", "O"};
        int moves_counter = 0;

        field.printField();
        while (running)
        {
            System.out.print("Enter coordinates: ");
            coords = readInputCoords(scanner);
            if (isValidCoords(coords, field))
            {
                field.setCell(coords[0], coords[1], moves[moves_counter % 2]);
                running = field.isRunningState();
                field.printField();
                moves_counter++;
            }
        }
        field.printState();
    }

    private static boolean isValidCoords(Integer[] coords, Field field)
    {
        if (coords[0] < 1 || coords[0] > 3 ||
            coords[1] < 1 || coords[1] > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }


        if (!field.getCell(coords[0], coords[1]).equals(" "))
        {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    private static String[] readInputField(String input)
    {
        return input.replace("_", " ").split("");
    }

    private static Integer[] readInputCoords(Scanner scanner)
    {
        Integer[] coords = new Integer[2];
        String input = scanner.nextLine();

        coords[0] = 0;
        coords[1] = 0;
        if (!input.contains(" "))
            return coords;

        String[] splitted = input.split(" ");
        if (splitted.length != 2)
            return coords;

        for (int i=0; i < 2; i++)
        {
            try {
                coords[i] = Integer.parseInt(splitted[i]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                break;
            }
        }
        int temp = coords[0];
        coords[0] = coords[1];
        coords[1] = temp;
        coords[0] = 4 - coords[0];
        return coords;
    }
}
