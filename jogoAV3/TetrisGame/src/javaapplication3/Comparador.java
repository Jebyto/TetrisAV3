package javaapplication3;

import java.util.Comparator;
public class Comparador implements Comparator<Score> {

@Override
	public int compare(Score p1, Score p2){
	return p2.getPontos()-p1.getPontos();

	}}