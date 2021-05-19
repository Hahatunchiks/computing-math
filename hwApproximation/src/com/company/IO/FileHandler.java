package com.company.IO;
import com.company.Solvers.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler implements Reader {

    @Override
    public boolean read(ArrayList<Point> table) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write filepath");
        File source = new File(sc.nextLine());
        FileReader fr = new FileReader(source);
        BufferedReader bf = new BufferedReader(fr);

        int j = 1;
        try {
            int size = Integer.parseInt(bf.readLine());
            for(int i = 0; i < size; i++){
                String[] row = bf.readLine().split(" ");
                Point point = new Point(Double.parseDouble(row[0]), Double.parseDouble(row[1]));
                table.add(point);
                j = i + 2;
            }
        } catch (NumberFormatException e){
            System.err.println("Wrong row in file:" + j);
            return false;
        }
        return true;
    }
}