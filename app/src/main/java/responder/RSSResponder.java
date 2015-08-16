package responder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import model.RSSItem;

/**
 * Created by maxim on 15/08/2015.
 */
public class RSSResponder {
    private static final String mUrl = "http://diplomatie.belgium.be/nl/Diensten/Op_reis_in_het_buitenland/reisadviezen/rss_chron.jsp";
    private ArrayList<RSSItem> mItems;
    private XmlPullParserFactory xmlFactoryObject;

    //Lege constructor
    public RSSResponder() {
        mItems = new ArrayList<>();
    }

    public ArrayList<RSSItem> getItems() {
        return this.mItems;
    }
    public void setItems(ArrayList<RSSItem> mItems) {
        this.mItems = mItems;
    }

    //Vorm met de bekomen XML een RSSItem object en sla deze op in de lijst
    public void parseXMLAndStore(XmlPullParser myParser) {
        int event;
        String text = null;
        RSSItem newItem = null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equals("title")){
                            newItem = new RSSItem();
                            newItem.setTitle(text);
                        }
                        else if(name.equals("link")){
                            if(newItem != null) {
                                newItem.setLink(text);
                            }
                        }
                        else if(name.equals("description")){
                            newItem.setDescription(text);
                            this.mItems.add(newItem);
                        }
                        else{
                        }
                        break;
                }
                event = myParser.next();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fetchXML(){
        try {
            URL url = new URL(RSSResponder.mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            InputStream stream = conn.getInputStream();

            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();

            //myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myparser.setInput(stream, "UTF_8");

            parseXMLAndStore(myparser);
            stream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



}