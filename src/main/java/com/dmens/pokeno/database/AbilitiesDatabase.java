package com.dmens.pokeno.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dmens.pokeno.ability.Ability;
import com.dmens.pokeno.utils.AbilityUtil;
import com.dmens.pokeno.utils.FileUtils;

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
		List<String> list = FileUtils.getFileContentsAsList(ablitiesFilePath);
		list.forEach(line -> {
			System.out.println(line);
			db.add(AbilityUtil.getAbilityFromString(line));
		});
	}
}
