package com.example.assignment_androidnc.news

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

class XMLParser {
    companion object{
        fun getDocument(xml:String):Document{
            val builderFactory= DocumentBuilderFactory.newInstance()
            val documentBuilder = builderFactory.newDocumentBuilder()
            val inputSource = InputSource(StringReader(xml))
            inputSource.encoding = "UTF-8"
            return documentBuilder.parse(inputSource)
        }
        fun getNodeValue(element: Element,tag:String):String{
            val nodeList = element.getElementsByTagName(tag).item(0).childNodes
            return nodeList.item(0).nodeValue
        }
    }
}