package org.example;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MediatorTest {

    @Test
    public void createSupplierMatrix() {
        int[][] supplierMatrix = {
                {0, 6, 8, 2},
                {4, 0, 2, 3},
                {3, 7, 0, 6},
                {2, 8, 4, 0}};

        assertTrue(Arrays.deepEquals(supplierMatrix, Main.loadSupplierMatrix("src/main/resources/datenASupplier_4.txt")));
    }
    @Test
    public void createCustomerMatrix() {
        int[][] customerMatrix = {
                {2, 1, 2},
                {1, 1, 1},
                {3, 2, 3},
                {1, 2, 2}};
        Main.loadSupplierMatrix("src/main/resources/datenASupplier_4.txt");
        assertTrue(Arrays.deepEquals(customerMatrix, Main.loadCustomerMatrix("src/main/resources/datenBCustomer4_3.txt")));
    }

}
