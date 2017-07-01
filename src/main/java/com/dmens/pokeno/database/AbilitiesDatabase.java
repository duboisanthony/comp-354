package com.dmens.pokeno.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Utils.AbilityUtil;

public class AbilitiesDatabase extends Database<Ability>{
	private static Database<Ability> database;
	
	public static Database<Ability> getInstance(){
		if(database == null)
			database = new AbilitiesDatabase();
		return database;
	}
	
	private AbilitiesDatabase(){
		db = new ArrayList<Ability>();
	}

	public void initialize(String ablitiesFilePath){
		List<String> list = null;
		System.out.println(getClass().getClassLoader().getResource(ablitiesFilePath));
		try (BufferedReader br = Files.newBufferedReader(Paths.get(getClass().getClassLoader().getResource(ablitiesFilePath).toURI()))) {
			list = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		list.forEach(line -> {
			System.out.println(line);
			db.add(AbilityUtil.getAbilityFromString(line));
		});
	}
}
