package com.dmens.pokeno.UtilsTest;

/**
 * Created by Devin Mens on 2017-05-31.
 */


import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.EnergyCard;
import com.dmens.pokeno.Utils.Tuple;
import org.junit.Test;
import com.dmens.pokeno.Ability.Ability;
import java.util.ArrayList;
import org.junit.Assert;

public class TupleTest {

    EnergyCard testAbility = new EnergyCard("Colourless", "Colourless");
    ArrayList<Integer> energyCost = new ArrayList<Integer>();

    @Test
    public void energyCostTest(){
        energyCost.add(0,2);
        Tuple<EnergyCard, ArrayList<Integer>> testTuple = new Tuple<>(testAbility, energyCost);


        Assert.assertEquals("Colourless", testTuple.x.getCategory());
        Assert.assertEquals(2, testTuple.y.get(0).intValue());
    }


}
