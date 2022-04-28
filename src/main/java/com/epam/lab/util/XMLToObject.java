package com.epam.lab.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.epam.lab.model.FilterRozetka;
import org.xml.sax.Attributes;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLToObject {

    private static ArrayList<FilterRozetka> filterRozetka = new ArrayList<>();
    private static int currentIndexOfArray;

    public XMLToObject() throws ParserConfigurationException, SAXException, IOException {
        currentIndexOfArray = 0;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File("src/main/resources/testData.xml"), handler);
    }

    public Object[][] testDataMassive() {
        if(currentIndexOfArray>filterRozetka.size()) { return null;}
        FilterRozetka filter = filterRozetka.get(currentIndexOfArray);
        currentIndexOfArray++;
        return new Object[][]{{filter.getProduct(), filter.getBrand(), filter.getSum()}};
    }

    private static class XMLHandler extends DefaultHandler{

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("filterRozetka")) {
                String product = attributes.getValue("product");
                String brand = attributes.getValue("brand");
                String sum = attributes.getValue("sum");
                filterRozetka.add(new FilterRozetka(product, brand, sum));
            }
        }
    }
}

