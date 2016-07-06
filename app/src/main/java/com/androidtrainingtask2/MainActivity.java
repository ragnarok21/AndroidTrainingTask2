package com.androidtrainingtask2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                System.out.println("Hello World");
                getListData();
            }
        });
    }

    private void getListData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getListDataResponse(fetchNewsReport());
            }
        });
        thread.start();
    }

    private void getListDataResponse(NewsResponse newsResponse) {
        ListView listview = (ListView)findViewById(R.id.listView);
        MyListViewAdapter adapter = new MyListViewAdapter(this);

        listview.setAdapter(adapter);
        adapter.setData(newsResponse.list);
    }

    private NewsResponse fetchNewsReport() {
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("http://www.feedforall.com/sample.xml").openStream());
           // NodeList nodes = doc.getElementsByTagName("item");

/*
            for (int i = 0; i <nodes.getLength() ; i++) {
                Element element = (Element) nodes.item(i);
                NodeList title = element.getElementsByTagName("title");
                Element line = (Element) title.item(0);
                System.out.println("Title: " + getCharacterDataFromElement(line));

                NodeList description = element.getElementsByTagName("description");
                line = (Element) description.item(0);
                System.out.println("Description: " + getCharacterDataFromElement(line));
            }*/

            /*StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            JSONObject jsonObj = XML.toJSONObject(xml);
            String a = sw.toString();*/
            String a = doc.toString();
           // JSONObject json


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData().toString();
        }
        return "";
    }


}
