package com.company.IO;
import com.company.Solvers.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleReader implements Reader{

    @Override
    public boolean read(ArrayList<Point> table) {
        Scanner sc = new Scanner(System.in);
        boolean q = false;
        int size = 0;
        while (!q) {
            System.out.println("Enter table size");
            try {
                size = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                System.err.println("try again");
            }
            q = true;
        }
        int j = 1;
        try {
            for (int i = 0; i < size; i++) {
                String[] row = sc.nextLine().split(" ");
                Point point = new Point(Double.parseDouble(row[0]), Double.parseDouble(row[1]));
                table.add(point);
                j = i + 2;
            }
        } catch (NumberFormatException e){
            System.err.println("Wrong in row : " + j);
            return false;
        }
        return true;
    }
}