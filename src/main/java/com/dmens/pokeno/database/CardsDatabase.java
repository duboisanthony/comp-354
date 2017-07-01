package com.dmens.pokeno.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.CardTypes;
import com.dmens.pokeno.Card.EnergyCard;
import com.dmens.pokeno.Utils.CardUtil;

public class CardsDatabase extends Database<Card>{
	private static Database<Card> database;
	private static String[] supportedPokemon = {"Electrike", "Froakie", "Electabuzz", "Machop", "Zubat", "Shellder"};
	private static String[] supportedTrainer = {};
	
	public static Database<Card> getInstance(){
		if(database == null)
			database = new CardsDatabase();
		return database;
	}
	
	private CardsDatabase(){
		db = new ArrayList<Card>();
	}
	
	public void initialize(String cardsFilePath){
		List<String> list = null;
		try (BufferedReader br = Files.newBufferedReader(Paths.get(getClass().getClassLoader().getResource(cardsFilePath).toURI()))) {
			list = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		list.forEach(line -> {
			db.add(CardUtil.getCardFromString(line));
		});
	}
	
	@Override
	public Card query(int index) {
		Card card = super.query(index);
		if(card.isType(CardTypes.POKEMON) && !Arrays.asList(supportedPokemon).contains(card.getName())){
			return new EnergyCard("Colorless", "colorless");
		}else if(card.isType(CardTypes.TRAINER) && !Arrays.asList(supportedTrainer).contains(index)){
			return new EnergyCard("Colorless", "colorless");
		}
		return card.copy();
	}
}
