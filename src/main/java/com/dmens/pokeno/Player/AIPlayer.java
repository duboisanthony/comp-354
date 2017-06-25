package com.dmens.pokeno.Player;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.Pokemon;
import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.Deck.Hand;
import com.dmens.pokeno.Driver.GameController;

public class AIPlayer extends Player {

    private static final Logger LOG = LogManager.getLogger(AIPlayer.class);

	public AIPlayer(Deck deckList) {
		super(deckList);
                humanPlayer = false;
	}
        
        public void startPhase()
        {
            Hand mHand = getHand();
            for(int i = mHand.size() - 1; i >= 0; --i)
            {
        	Card card = mHand.getCards().get(i);
                if (card instanceof Pokemon)
                {
                    useCard(card);
                    //System.out.println("Played it!");
                    break;
                }
                //System.out.println("Didn't play it");
            }
        }
        
        public void activeFainted()
        {
            ArrayList<Pokemon> mBench = getBenchedPokemon();
            if (mBench.size() <= 0)
            {
                GameController.board.AnnouncementBox.setText("Opponenthas no available Pokemon! The player wins!");
                return;
            }
            setActivePokemon(mBench.get(0));
            mBench.remove(mBench.get(0));
        }
        
	//TODO: implement AI specific functions
}
