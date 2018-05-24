package com.cpochard;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MainClass {

	public static void main(String[] args) {

		//Cr�ation d'une fabrique � parseur
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//On enleve la lecture des espaces entre les balises
		factory.setIgnoringElementContentWhitespace(true);
		
		//Pr�paration de l'objet qui va contenir notre document XML
		Document fichierXML = null;
		
		try {
			//Instanciation du parseur
			DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				//Passage du document
				fichierXML = builder.parse("armes.xml");
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		//R�cup�ration de la racine
		Node root = fichierXML.getDocumentElement();
		//Affiche du type de noeud de la racine
		System.out.println(root.getNodeName());
		
		//Deplacement jusqu'� un �l�ment pr�cis (ici Dague du noeud nom)
		Node element = root.getFirstChild().getNextSibling().getFirstChild();
		System.out.println(element.getTextContent());
		
		System.out.println(recupererDegatsAvecNom ("Lance"));
	}
	
	//Methode qui retourne les degats d'une arme en fonction du nom
	public static int recupererDegatsAvecNom(String nom) {
		int degatsArme = 0;
		Document fichierXML = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				fichierXML = builder.parse("armes.xml");
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Node root = fichierXML.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		for (int i = 0 ; i<nodes.getLength() ; i++) {
			Node n = nodes.item(i); //Permet de r�cuperer les enfants de la racine donc arme
			if (n.getFirstChild().getTextContent().equals(nom)) {
				Node element = n.getFirstChild().getNextSibling().getNextSibling();
				degatsArme = Integer.parseInt(element.getTextContent());
			}
		}
		return degatsArme;
	}

}
