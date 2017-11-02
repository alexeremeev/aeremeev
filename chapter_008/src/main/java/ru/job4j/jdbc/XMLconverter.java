package ru.job4j.jdbc;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

/**
 * class XMLconverter - создание, преобразование XML файлов.
 */
public class XMLconverter {

    /**
     * Получаем из Entries XML файл.
     * @param entries Entries.
     * @param file выходной XML файл.
     */
    public void entryToXML(Entries entries, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entries, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Преобразуем XML файл с помощью XSL шаблона.
     * @param originalFile исходный файл.
     * @param xslFile файл XSL шаблона.
     * @param convertedFile пребразованный XML файл.
     */
    public void convertViaXSL(File originalFile, File xslFile, File convertedFile) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(xslFile);
            Transformer transformer = transformerFactory.newTransformer(xslt);
            Source original = new StreamSource(originalFile);
            transformer.transform(original, new StreamResult(convertedFile));
        } catch (TransformerConfigurationException tce) {
            tce.printStackTrace();
        } catch (TransformerException te) {
            te.printStackTrace();
        }
    }

    /**
     * Выполение функций над содержимым XNL файла с помошью XPath.
     * @param xml XML файл.
     * @param function функция для выполнения.
     * @return результат выполнения функции.
     */
    public String evaluateViaXPath(File xml, String function) {

        String result = "";

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xml);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            result = xPath.evaluate(function, document);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException saxe) {
            saxe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (XPathExpressionException xpee) {
            xpee.printStackTrace();
        }
        return result;
    }
}
