package org.example;

import org.junit.jupiter.api.Test;
import org.testng.Assert;


public class SupplierTest {
    int [][] matrix = {{0, 6, 8, 2}, {4, 0, 2, 3},{3, 7, 0, 6},{2, 8, 4, 0}};
    Supplier s = new Supplier(matrix);
    @Test
    public void testCalculation(){
        Integer [] proposal = {0,1,3,2};
        Assert.assertEquals(s.getTime(proposal),13);
    }

    @Test
    public void testVote(){
        Integer[] proposal1 = {1,0,3,2};
        Integer [] proposal2 = {3,1,2,0};
        Integer [] contract = {0,1,3,2};
        Assert.assertEquals(s.vote(contract,contract),false);
        Assert.assertEquals(s.vote(contract,proposal1), true);
        Assert.assertEquals(s.vote(contract,proposal2), false);
    }

}
