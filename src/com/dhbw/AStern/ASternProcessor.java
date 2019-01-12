package com.dhbw.AStern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ASternProcessor {
	private Karte karte;

	public ASternProcessor(Karte aKarte) {
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
			// Liste sortieren.
			openList = sortList(openList);

			printList(openList, "OpenList");
			printList(closeList, "CloseList");

			// Das erste Feld der sortierten Open-Liste wird als Ausgangsfeld der nächsten Operation gewählt.
			ListElement ausgangsElement = openList.get(0);
			Feld ausgangsFeld = ausgangsElement.getFeld();
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
					double gVonX = ausgangsFeld.getGvonx() + ausgangsFeld.getKosten();
					
					// F-Funktion des Nachbar-Feldes aufstellen.
					// Besonderheit: der Erschöpfungswert spielt ebenfalls eine Rolle.
					// Wenn das überqueren des Nachbarfeldes dazu führen würde, dass ein Pause gemacht werden muss,
					// werden +5 zu f(x) dazu addiert.
					double fVonX = gVonX + neighbour.getHvonx() + calculateEFunktion(ausgangsElement, neighbour.getGelaende());

					// Weg zum Nachbarfeld definieren.
					Weg wegToNeighbour = new Weg(ausgangsElement.getWeg());
					wegToNeighbour.addFeld(ausgangsFeld);

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
								if (element.getFeld().getFvonx() >= fVonX) {
									element.setWeg(wegToNeighbour);
									element.getFeld().setFvonx(fVonX);
									element.getFeld().setGvonx(gVonX);
								} else {
									closeList.add(neighbourElement);
								}
								break;
							} else {
								if (openList.get(openList.size() - 1).equals(element)) {
									neighbour.setFvonx(fVonX);
									neighbour.setGvonx(gVonX);
									openList.add(new ListElement(neighbour, wegToNeighbour));
									break;
								}
							}
						}
					}

				}

			} else {
				// Das Ziel wurde erreicht, da das erste Feld auf der Open-Liste das Zielfeld ist.
				
				// Zielfeld wird an den Weg angehängt
				ausgangsElement.getWeg().addFeld(getKarte().getZiel());
				
				// Weg wird als "WegZumZiel" markiert, damit das Zielfeld bei der Berechnung nicht einbezogen wird.
				ausgangsElement.getWeg().setIstWegZumZiel(true);
				// Der Weg wird zurückgegeben
				return ausgangsElement.getWeg();
			}
			// Das bearbeitete Feld wird von der Open-Liste gestrichen und zur Close-Liste hinzugefügt.
			closeList.add(ausgangsElement);
			openList.remove(0);
		}
		
		// Für den Fall, dass die open-List leer ist, wird der Weg zum letzten Feld der close-List
		// als Weg zum Ziel ausgegeben.
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
	 * @param aBezeichner wie die Liste heißen soll
	 */
	private void printList(ArrayList<ListElement> aList, String aBezeichner) {
		StringBuilder sb = new StringBuilder();

		sb.append(aBezeichner + ": ");
		for (ListElement feld : aList) {
			sb.append("\n\t" + feld.getFeld());
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * Berechnet die den Wert der e-Funktion (Einfluss der Erschöpfung).
	 * @param anElement aktuelles Listen Element.
	 * @param aGelaende Gelände des möglichen nächsten Feldes des Weges.
	 * @return Wert der E-Funktion für dsa nächste mögliche Feld des Weges.
	 */
	private double calculateEFunktion(ListElement anElement, String aGelaende) {
		double erschoepfungswert = anElement.getWeg().getErschoepfung();
		double erschoepfungseinfluss = 0;
		
		// Beim Erschöpfungswert muss auch das aktuelle Feld beachtet werden.
		switch(anElement.getFeld().getGelaende()) {
			case "0": erschoepfungswert += 0;
			break;
			case "1": erschoepfungswert += 4;
			break;
			case "2": erschoepfungswert += 0;
			break;
			case "3": erschoepfungswert =  erschoepfungswert/ 2;
			break;
			case "4": erschoepfungswert += 0;
			break;
			case "5": erschoepfungswert += 3;
			break;
			default:  erschoepfungswert += 0;
		}
		// Was passiert mit dem Erschöpfungswert, wenn das potentiell nächste Feld verlassen wird?
		// Wenn der Wert größer als 10 wird muss eine Pause gemacht werden.
		// Eine Pause hat die Kosten 5. Deshalb werden in diesem Fall ein Erschöpfungseinfluss von 5
		// in die Berechnung des Wertes der F-Funktion mit einbezogen.
		// Wird der Erschöpfungswert durch das Laufen im Wald verringert, wirkt das möglichen spätern
		// Pausen entgegen. Das macht das Feld attraktiver. Das wird im Wert der F-Funktion ausgedrückt,
		// in dem dieser um den Betrag der Verringerung des Erschöpfungswertes verringert wird.
		switch(aGelaende) {
			case "1": 
				if(erschoepfungswert > 6) {
					erschoepfungseinfluss = 5;
				}
			break;
			case "3": erschoepfungseinfluss =  - (erschoepfungswert/ 2);
			break;
			case "5": 
				if(erschoepfungswert > 7) {
					erschoepfungseinfluss = 5;
				}
			break;
			default:  erschoepfungseinfluss = 0;
		}
		return erschoepfungseinfluss;
	}

}
