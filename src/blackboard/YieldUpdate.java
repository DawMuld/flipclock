/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackboard;

import java.time.LocalDate;




/**
 *
 * @author daan-
 */
public class YieldUpdate {

    private final int totalYieldValue;
    private final int[] monthYieldValues;






    public YieldUpdate(int totalYieldValue, int[] monthYieldValues) {
        this.totalYieldValue = totalYieldValue;
        this.monthYieldValues = monthYieldValues;
    }






    public int getTotalYieldValue() {
        return this.totalYieldValue;
    }






    public int[] getMonthYieldValues() {
        return this.monthYieldValues;
    }






    public int[] getMonthNumbers() {
        int[] monthNumbers = new int[12];
        LocalDate ld = LocalDate.now().minusMonths(11L);
        for (int i = 0; i < 12; i++) {
            monthNumbers[i] = ld.getMonthValue();
            ld = ld.plusMonths(1L);
        }
        return monthNumbers;
    }
}

