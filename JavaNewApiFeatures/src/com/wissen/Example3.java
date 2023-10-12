package com.wissen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Example3 {

	public static void main(String[] args) {

		Card card1 = new Card(Suite.DIAMOND, Rank.ACE);
		Card card2 = new Card(Suite.DIAMOND, Rank.QUEEN);
		printHigherCard(card1, card2);

		List<Card> cards = new ArrayList<>();

		cards.add(card1);
		cards.add(card2);

//		shuffleDeck(cards); // TODO
		sortDeck(cards);
	}

	static List<Card> sortDeck(List<Card> cards) {

		return cards.stream().sorted((card1, card2) -> {
			int suitComparision = card1.getSuite().getId().compareTo(card2.getSuite().getId());
			if (suitComparision != 0) {
				return suitComparision;
			} else {
				return card1.getRank().getId().compareTo(card2.getRank().getId());
			}
		}).collect(Collectors.toList());
	}

	static void printHigherCard(Card card1, Card card2) {
		if (card1.getRank().getId() > card2.getRank().getId()) {
			System.out.println("Card1 is higher in rank");
		} else if (card1.getRank().getId() < card2.getRank().getId()) {
			System.out.println("Card2 is higher in rank");
		} else {
			System.out.println("Cards are equal in rank");
		}
	}

	enum Suite {
		DIAMOND(1, "Diamond"), HEART(2, "Hear"), CLUB(3, "Club"), SPADE(4, "Spade");

		private Integer id;
		private String name;

		Suite(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public Integer getId() {
			return id;
		}
	}

	enum Rank {
		ONE(1, "one"), TWO(2, "two"), THREE(3, "three"), FOUR(4, "four"), FIVE(5, "five"), SIX(6, "six"),
		SEVEN(7, "seven"), EIGHT(8, "eight"), NINE(9, "nine"), JACK(10, "jack"), QUEEN(11, "queen"), KING(12, "king"),
		ACE(13, "ace");

		private Integer id;
		private String name;

		Rank(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public Integer getId() {
			return id;
		}
	}

}
