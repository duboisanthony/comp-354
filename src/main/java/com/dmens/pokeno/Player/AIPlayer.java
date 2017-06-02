package com.dmens.pokeno.Player;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.Pokemon;

public class AIPlayer extends Player {

    private static final Logger LOG = LogManager.getLogger(AIPlayer.class);

	public AIPlayer(ArrayList<Card> deckList) {
		super(deckList);
                humanPlayer = false;
	}
        
        public void startPhase()
        {
            ArrayList<Card> mHand = getHand();
            for(int i = mHand.size() - 1; i >= 0; --i)
            {
        	Card card = mHand.get(i);
                if (card instanceof Pokemon)
                {
                    useCard(card);
                    System.out.println("Played it!");
                    break;
                }
                System.out.println("Didn't play it");
            }
        }
        
	//TODO: implement AI specific functions
}
