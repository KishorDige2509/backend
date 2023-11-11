package com.wissen;

import com.wissen.CardSolution.Rank;
import com.wissen.CardSolution.Suite;

public class Card {

	private Suite suite;
	private Rank rank;

	// constructor
	public Card(Suite suite, Rank rank) {
		this.suite = suite;
		this.rank = rank;
	}

	// setters
	public void setSuite(Suite suite) {
		this.suite = suite;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	// getters
	public Suite getSuite() {
		return suite;
	}

	public Rank getRank() {
		return rank;
	}

}
