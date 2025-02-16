package application;

import entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main( String[] args ) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.println("Caminho para o arquivo: ");
        String sourceFileStr = sc.nextLine();

        File sourceFile = new File(sourceFileStr);
        String sourceFolderStr = sourceFile.getParent();

        boolean success = new File(sourceFolderStr + "\\out").mkdir();

        String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {

            String itemCsv = br.readLine();
            while (itemCsv != null) {

                String[] fileds = itemCsv.split(",");
                String name = fileds[0];
                double price = Double.parseDouble(fileds[1]);
                int quantity = Integer.parseInt(fileds[2]);

                products.add(new Product(name, price, quantity));

                itemCsv = br.readLine();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {

                for (Product item : products) {
                    bw.write(item.getName() + "," + String.format("%.2f", item.total()));
                }

            System.out.println(targetFileStr + "Arquivo Criado!");

            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage() );
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        sc.close();
    }
}
