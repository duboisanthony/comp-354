package com.dmens.pokeno.database;

import java.util.ArrayList;
import java.util.List;

public abstract class Database<T> {
	protected List<T> db;
	
	protected Database(){
		db = new ArrayList<T>();
	}
	
	public abstract void initialize(String filename);
	
	public T query(int index){
		return db.get(index-1);
	}
}
