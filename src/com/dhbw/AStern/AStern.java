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

			Feld startFeld = openList.get(0).getFeld();
			System.out.println("Select -> " + startFeld);

			if (!startFeld.equals(getKarte().getZiel())) {

				ArrayList<Feld> neighbourList = new ArrayList<Feld>();

				if (startFeld.getX() > 0) {
					neighbourList.add(getKarte().getFeld(startFeld.getX() - 1, startFeld.getY()));
				}
				if (startFeld.getY() > 0) {
					neighbourList.add(getKarte().getFeld(startFeld.getX(), startFeld.getY() - 1));
				}
				if (startFeld.getX() < karte.getWidth() - 1) {
					neighbourList.add(getKarte().getFeld(startFeld.getX() + 1, startFeld.getY()));
				}
				if (startFeld.getY() < karte.getHeight() - 1) {
					neighbourList.add(getKarte().getFeld(startFeld.getX(), startFeld.getY() + 1));
				}

				for (Feld neighbour : neighbourList) {
					neighbour.setGvonx(startFeld.getGvonx() + startFeld.getKosten());
					neighbour.setFvonx(neighbour.getGvonx() + neighbour.getHvonx());

					Weg wegToNeighbour = new Weg(openList.get(0).getWeg());
					wegToNeighbour.addFeld(openList.get(0).getFeld());

					ListElement neighbourElement = new ListElement(neighbour, wegToNeighbour);

					boolean isOnCloseListFlag = false;;
					
					for (ListElement element : closeList) {
						if (element.getFeld().equals(neighbour)) {
							isOnCloseListFlag = true;
							break;
						}
					}
					

					if (!isOnCloseListFlag) {
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
				System.out.println("ZIEL ERREICHT");
				
				// Zielfeld wird an den Weg angehängt
				openList.get(0).getWeg().addFeld(getKarte().getZiel());
				
				// Weg wird als "WegZumZiel" markiert, damit das Zielfeld bei der Berechnung nicht einbezogen wird.
				openList.get(0).getWeg().setIstWegZumZiel(true);
				// Der Weg wird zurückgegeben
				return openList.get(0).getWeg();
			}
			closeList.add(openList.get(0));
			openList.remove(0);
		}
		
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
	 * @param karte
	 *            the karte to set
	 */
	public void setKarte(Karte karte) {
		this.karte = karte;
	}

	private ArrayList<ListElement> sortList(ArrayList<ListElement> aList) {
		Collections.sort(aList, new Comparator<ListElement>() {
			@Override
			public int compare(ListElement element1, ListElement element2) {
				return element1.getFeld().getFvonx().compareTo(element2.getFeld().getFvonx());
			}
		});
		return aList;
	}

	private void printOpenList(ArrayList<ListElement> openList) {
		StringBuilder sb = new StringBuilder();

		sb.append("OpenList: ");
		for (ListElement feld : openList) {
			sb.append(feld.getFeld());
		}
		System.out.println(sb.toString());
	}

}
