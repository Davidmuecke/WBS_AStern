package com.dhbw.AStern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AStern {
	private Karte karte;

	public AStern(Karte aKarte) {
		setKarte(aKarte);
	}

	public Weg suche() {
		Feld start = getKarte().getStart();
		Feld ziel = getKarte().getZiel();

		// Schätzt die H-Funktion der Karte.
		getKarte().calculateHFunction(ziel);

		// Der Abstand des Starts zu sich selbst ist 0.
		start.setGvonx(0);
		start.setFvonx(start.getGvonx() + start.getHvonx());

		ArrayList<ListElement> openList = new ArrayList<ListElement>();
		ArrayList<ListElement> closeList = new ArrayList<ListElement>();

		// Startknoten zur open-Liste hinzufügen
		openList.add(new ListElement(start, new Weg()));

		while (!openList.isEmpty()) {
			printOpenList(openList);
			// Liste sortieren.
			openList = sortList(openList);

			// Das erste Feld der sortierten Open-Liste wird als Ausgangsfeld der nächsten Operation gewählt.
			Feld ausgangsFeld = openList.get(0).getFeld();
			System.out.println("Select -> " + ausgangsFeld);

			if (!ausgangsFeld.equals(getKarte().getZiel())) {

				// Die Nachbarn des ausgangsfeldes werden ermittelt
				ArrayList<Feld> neighbourList = new ArrayList<Feld>();

				if (ausgangsFeld.getX() > 0) {
					neighbourList.add(getKarte().getFeld(ausgangsFeld.getX() - 1, ausgangsFeld.getY()));
				}
				if (ausgangsFeld.getY() > 0) {
					neighbourList.add(getKarte().getFeld(ausgangsFeld.getX(), ausgangsFeld.getY() - 1));
				}
				if (ausgangsFeld.getX() < karte.getWidth() - 1) {
					neighbourList.add(getKarte().getFeld(ausgangsFeld.getX() + 1, ausgangsFeld.getY()));
				}
				if (ausgangsFeld.getY() < karte.getHeight() - 1) {
					neighbourList.add(getKarte().getFeld(ausgangsFeld.getX(), ausgangsFeld.getY() + 1));
				}

				for (Feld neighbour : neighbourList) {
					// G-Funktion des Nachbar-Feldes aufstellen.
					neighbour.setGvonx(ausgangsFeld.getGvonx() + ausgangsFeld.getKosten());
					
					// G-Funktion des Nachbar-Feldes aufstellen.
					neighbour.setFvonx(neighbour.getGvonx() + neighbour.getHvonx());

					// Weg zum Nachbarfeld definieren.
					Weg wegToNeighbour = new Weg(openList.get(0).getWeg());
					wegToNeighbour.addFeld(openList.get(0).getFeld());

					// Neues Listenelement für Nachbarfeld.
					ListElement neighbourElement = new ListElement(neighbour, wegToNeighbour);
					
					// Flag zeigt an, ob sich das Feld bereits auf der Close-Liste befindet.
					boolean isOnCloseListFlag = false;;
					
					// Es wird ermittelt, ob sich das Feld bereits auf der Close-Liste befindet.
					for (ListElement element : closeList) {
						if (element.getFeld().equals(neighbour)) {
							isOnCloseListFlag = true;
							break;
						}
					}
					

					if (!isOnCloseListFlag) {
						// Befindet sich das Feld schon auf der Open-Liste?
						// Wenn ja, wird geprüft, welcher Weg länger war und der kürzere behalten.
						// Wenn nein, wird das Feld zur Open-Liste hinzugefügt.
						for (ListElement element : openList) {
							if (element.getFeld().equals(neighbour)) {
								if (element.getGesamtkosten() >= neighbourElement.getGesamtkosten()) {
									openList.add(new ListElement(neighbour, wegToNeighbour));
									openList.remove(openList.indexOf(element));
									closeList.add(element);
								} else {
									closeList.add(neighbourElement);
								}
								break;
							} else {
								if (openList.get(openList.size() - 1).equals(element)) {
									openList.add(new ListElement(neighbour, wegToNeighbour));
									break;
								}
							}
						}
					}

				}

			} else {
				// Das Ziel wurde erreicht, wenn das erste Feld auf der Open-Liste das Zielfeld ist.
				System.out.println("ZIEL ERREICHT");
				
				// Zielfeld wird an den Weg angehängt
				openList.get(0).getWeg().addFeld(getKarte().getZiel());
				
				// Weg wird als "WegZumZiel" markiert, damit das Zielfeld bei der Berechnung nicht einbezogen wird.
				openList.get(0).getWeg().setIstWegZumZiel(true);
				// Der Weg wird zurückgegeben
				return openList.get(0).getWeg();
			}
			// Das bearbeitete Feld wird von der Open-Liste gestrichen und zur Close-Liste hinzugefügt.
			closeList.add(openList.get(0));
			openList.remove(0);
		}
		
		// Für den Fall, 
		closeList.get(closeList.size() - 1).getWeg().addFeld(getKarte().getZiel());

		// Das Element, das als letztes in die Close-Liste kam, wird zurückgegeben.
		return closeList.get(closeList.size() - 1).getWeg();

	}

	/**
	 * @return the karte
	 */
	public Karte getKarte() {
		return karte;
	}

	/**
	 * @param karte the karte to set
	 */
	public void setKarte(Karte karte) {
		this.karte = karte;
	}

	private ArrayList<ListElement> sortList(ArrayList<ListElement> aList) {
		Collections.sort(aList, new Comparator<ListElement>() {
			@Override
			public int compare(ListElement element1, ListElement element2) {
				return new Double(element1.getFeld().getFvonx()).compareTo(element2.getFeld().getFvonx());
			}
		});
		return aList;
	}

	/**
	 * Druckt die Open-Liste in die Console.
	 * @param aList Liste, die ausgegeben werden soll
	 */
	private void printOpenList(ArrayList<ListElement> aList) {
		StringBuilder sb = new StringBuilder();

		sb.append("OpenList: ");
		for (ListElement feld : aList) {
			sb.append(feld.getFeld());
		}
		System.out.println(sb.toString());
	}

}
