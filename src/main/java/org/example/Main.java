package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int amountJobs = 0;
    static Supplier supplier;
    static Customer customer;
    static Mediator mediator;
    static int rounds;

    public static void main(String[] args) {
        supplier = new Supplier(loadSupplierMatrix("src/main/resources/daten5ASupplier_200.txt"));
        customer = new Customer(loadCustomerMatrix("src/main/resources/daten4ACustomer_200_5.txt"));
        mediator = new Mediator(supplier, customer, amountJobs);

        rounds = 1000;
        for (int i = 0; i < rounds; i++) {
            int [] proposal = mediator.adjustContractRandomly();

            if (mediator.proposeContract(proposal)) {

                System.out.println("Round: " + i + " " + customer.getTime_contract() + "  -   " + supplier.getTime(mediator.getMContract()));
            }
        }
    }

    public static int[][] loadSupplierMatrix(String path) {
        ArrayList<int[]> list = new ArrayList<>();
        String line = "";
        int[][] timeMatrix;
        try (FileReader fis = new FileReader(path); BufferedReader bur = new BufferedReader(fis)) {
            while ((line = bur.readLine()) != null) {
                if (line.contains(" ")) {
                    int[] row = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                    list.add(row);
                } else {
                    amountJobs = Integer.parseInt(line);
                }
            }
            timeMatrix = new int[amountJobs][amountJobs];
            for (int i = 0; i < amountJobs; i++) {
                timeMatrix[i] = list.get(i);
            }
            printMatrix(timeMatrix);
            return timeMatrix;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[][] loadCustomerMatrix(String path) {
        ArrayList<int[]> list = new ArrayList<>();
        String line = "";
        int[][] jobsSequenceMatrix;
        int amountMachines = 0;
        try (FileReader fis = new FileReader(path); BufferedReader bur = new BufferedReader(fis)) {
            while ((line = bur.readLine()) != null) {
                if (line.contains(" ")) {
                    int[] jobRow = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                    list.add(jobRow);
                } else {
                    if (Integer.parseInt(line) != amountJobs) {
                        amountMachines = Integer.parseInt(line);
                    }
                }
            }
            jobsSequenceMatrix = new int[amountJobs][amountMachines];
            for (int i = 0; i < amountJobs; i++) {
                jobsSequenceMatrix[i] = list.get(i);
            }
            printMatrix(jobsSequenceMatrix);
            return jobsSequenceMatrix;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix).forEach(e -> {
            Arrays.stream(e).forEach(b -> System.out.print(b + " "));
            System.out.println();
        });

    }


}