import java.io.*;
import java.util.Scanner;

public class Character {
    private String Name;
    private Integer XCoordinate;
    private Integer YCoordinate;

    public Character(String Name, Integer XCoordinate, Integer YCoordinate){
        this.Name = Name;
        this.XCoordinate = XCoordinate;
        this.YCoordinate =YCoordinate;
    }

    public String GetName() {
        return Name;
    }

    public Integer GetX() {
        return XCoordinate;
    }

    public Integer GetY() {
        return YCoordinate;
    }

    public void ChangePosition(Integer XChange, Integer YChange){
        XCoordinate += XChange;
        YCoordinate += YChange;
    }

    public static void main(String[] args) {
        Character[] charArray = new Character[10];
        File file = new File("Characters.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < 10; i++) {
                charArray[i] = new Character(" ", 0, 0);
                charArray[i].Name = reader.readLine();
                charArray[i].XCoordinate = Integer.valueOf(reader.readLine());
                charArray[i].YCoordinate = Integer.valueOf(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        Integer position;
        do {
            position = -1;
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the character's name: ");
            String characterName = keyboard.next();
            for (int i = 0; i < 10; i++) {
                if (charArray[i].Name.equalsIgnoreCase(characterName)) {
                    position = i;
                }
            }
        } while (position == -1);

        Boolean valid;
        do {
            valid = false;
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the direction for the character position: ");
            String Direction = keyboard.next().toUpperCase();
            if (Direction.equals("A") || Direction.equals("W") || Direction.equals("S") || Direction.equals("D")) {
                switch (Direction) {
                    case "A":
                        charArray[position].ChangePosition(-1, 0);
                        break;
                    case "W":
                        charArray[position].ChangePosition(0, 1);
                        break;
                    case "S":
                        charArray[position].ChangePosition(0, -1);
                        break;
                    case "D":
                        charArray[position].ChangePosition(1, 0);
                        break;
                }
                System.out.println(charArray[position].GetName() + " has changed coordinates to X = " + charArray[position].GetX() + " and Y = " + charArray[position].GetY());
                valid = true;
            }
        } while (!valid);
    }
}
