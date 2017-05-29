package com.dmens.pokeno.Player;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Card.Card;

public class AIPlayer extends Player {

    private static final Logger LOG = LogManager.getLogger(AIPlayer.class);

	public AIPlayer(ArrayList<Card> deckList) {
		super(deckList);
	}

	//TODO: implement AI specific functions
}
