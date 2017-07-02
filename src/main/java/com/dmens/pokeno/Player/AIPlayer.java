package com.dmens.pokeno.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.EnergyTypes;
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
        	AtomicBoolean energyPlayed = new AtomicBoolean(false);
            // Select pokemon and bench available pokemon
            getHand().getPokemon().forEach(pokemon ->{
            	useCard(pokemon);
            });
            // Does the active pokemon need energy?
            // If so, play energy
           // If not, does the benched pokemon need energy
            if(!checkAndPlayEnergyOn(getActivePokemon())){
            	for(Pokemon pokemon : getBenchedPokemon()){
            		if(checkAndPlayEnergyOn(pokemon))
            			break;
            	};
            }
            
            // Use trainer cards in hand
            
            // Attack if possible
            GameController.useActivePokemonForPlayer(1, 0);

        }
        
        private boolean checkAndPlayEnergyOn(Pokemon pokemon){
        	AtomicBoolean energyPlayed = new AtomicBoolean(false);
        	Hand mHand = getHand();
        	Map<EnergyTypes, Integer> costs = pokemon.getTotalEnergyNeeds();
            if(!costs.isEmpty()){
            	for(EnergyTypes energy : costs.keySet()){
            		if(costs.get(energy) <= 0)
            			continue;
            		Card energyInHand = mHand.getEnergyOfType(energy);
            		if(energyInHand != null){
	            		setEnergy(mHand.getEnergyOfType(energy), pokemon);
	            		energyPlayed.set(true);
	            		break;
            		}
            	};
            }  
            return energyPlayed.get();
        }
        
        public void selectStarterPokemon(){
            Hand mHand = getHand();
            useCard(mHand.getPokemon().get(0));
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
