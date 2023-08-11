package com.horsetrackapp.horsetrack.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

public class WagerServiceTest {

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();
    @InjectMocks
    WagerService wagerService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCalculateAmountWon() {
        int wagerAmount = 55;
        int odds = 5;
        int expected = 275;
        int actual = wagerService.calculateAmountWon(wagerAmount, odds);

        Assert.assertEquals("Winnings does not equal", expected, actual);
    }

}
