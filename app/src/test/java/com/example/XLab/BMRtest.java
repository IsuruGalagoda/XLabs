package com.example.XLab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BMRtest {

    private BMR_Calculator calculateBMR;

    @Before
    public void setup(){
        calculateBMR = new BMR_Calculator();

    }

    @Test
    public void testMenBMR(){
        float value = calculateBMR.calMenBMR(165f,78f,28f);
        assertEquals(1774.905f,value,0.0001);
    }
}

