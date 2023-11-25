// Пробовал несколько вариантов парсера, этот сначала выдавал ошибку,
// но проблема была просто в лишнем пробеле в XML файле
// А прошлый запускался исправно, но не выполнял парсинг, поэтому оставил этот


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLParser {

  private static final String FILENAME ="C:\\Edu\\Java_3_1semestr\\XMLParser14\\src\\PurchaseOrder.xml";

  public static void main(String[] args) {

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      try {

          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          // Парсинг
          DocumentBuilder db = dbf.newDocumentBuilder();

          Document doc = db.parse(new File(FILENAME));

          doc.getDocumentElement().normalize();

          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
          System.out.println("------");

          NodeList list = doc.getElementsByTagName("staff");

          for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  String id = element.getAttribute("id");

                  String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                  String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                  String nickname = element.getElementsByTagName("nickname").item(0).getTextContent();

                  NodeList salaryNodeList = element.getElementsByTagName("salary");
                  String salary = salaryNodeList.item(0).getTextContent();

                  String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                  System.out.println("Current Element :" + node.getNodeName());
                  System.out.println("Staff Id : " + id);
                  System.out.println("First Name : " + firstname);
                  System.out.println("Last Name : " + lastname);
                  System.out.println("Nick Name : " + nickname);
                  System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);

              }
          }

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }

  }

}
