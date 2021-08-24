package javaapplication3;

import java.util.ArrayList;
import java.util.Collections;

public class Ranking {
	private ArrayList<Score> ranking = new ArrayList<Score>();
	
	public void cadastrar(String nome, int Pontos) {
		Score score = new Score(nome, Pontos);
		ranking.add(score);
	}
	
	public String getNome(int i) {
		
		return ranking.get(i).getNome();
	}
public int getPontos(int i) {
		
		return ranking.get(i).getPontos();
	}
public int size() {
	
	return ranking.size();
}

public void ordRanking(){
	Collections.sort(ranking, new Comparador());
}
	
}
