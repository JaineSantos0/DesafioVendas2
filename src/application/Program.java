package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com o caminho do arquivo: ");
        String path = sc.nextLine();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            List<Sale> saleList = new ArrayList<>();
            Set<Sale> saleSet = new HashSet<>();

            String line = br.readLine();

            while (line != null){
                String[] fields = line.split(",");
                saleList.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]),
                        fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));

                saleSet.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]),
                        fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));

                line = br.readLine();
            }

            saleSet.forEach(sale -> {
                        String seller = sale.getSeller();
                        Double totalSeller = saleList.stream().filter(obj -> Objects.equals(obj.getSeller(), seller))
                                .map(Sale::getTotal)
                                .reduce(0.00, Double::sum);
                        sale.setTotal(totalSeller);
                    });

            saleSet.forEach(System.out::println);
        }
        catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}