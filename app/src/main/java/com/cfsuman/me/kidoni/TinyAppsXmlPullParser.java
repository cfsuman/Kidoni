package com.cfsuman.me.kidoni;

import android.content.Context;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class TinyAppsXmlPullParser {
    private static final String TINYAPP = "tinyapp";
    private static final String TINYAPP_NAME = "name";
    private static final String TINYAPP_ACTIVITY = "activity";
    private static final String TINYAPP_IAMGE = "image";
    private static final String TINYAPP_DESCRIPTION = "description";
    private static final String TINYAPP_ENABLED = "enabled";

    private TinyApp currentTinyApp = null;
    private String currentTag = null;

    // Initialize a new array list of student
    List<TinyApp> tinyapps = new ArrayList<TinyApp>();

    public List<TinyApp> parseXMLData(Context context){
        try{
            // Initialize a new XmlPullParserFactory instance
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            // Get the input stream from XML file's data
            InputStream stream = context.getResources().openRawResource(R.raw.tinyapps);

            // Specify the input stream for the xml pull parser
            parser.setInput(stream,null);

            // Get the vent type from XMLPullParser
            int eventType = parser.getEventType();

            // Loop through the xml data
            while(eventType!=XmlPullParser.END_DOCUMENT){ // Loop until reach the document end
                if(eventType == XmlPullParser.START_TAG){
                    if(parser.getName().equals(TINYAPP)){
                        currentTinyApp = new TinyApp();
                        tinyapps.add(currentTinyApp);
                    }else {
                        currentTag = parser.getName();
                        //Toast.makeText(context,parser.getName(),Toast.LENGTH_SHORT).show();
                    }
                }else if(eventType == XmlPullParser.END_TAG){
                    currentTag = null;
                }else if(eventType == XmlPullParser.TEXT){
                    if(currentTag!=null){
                        //Toast.makeText(context,currentTag + " || "+ parser.getText(),Toast.LENGTH_SHORT).show();
                        if(currentTag.equals(TINYAPP_NAME)){
                            currentTinyApp.setName(parser.getText());
                        }else if(currentTag.equals(TINYAPP_ACTIVITY)){
                            currentTinyApp.setActivity(parser.getText());
                        }else if(currentTag.equals(TINYAPP_IAMGE)){
                            currentTinyApp.setImage(parser.getText());
                        }else if(currentTag.equals(TINYAPP_DESCRIPTION)){
                            currentTinyApp.setDescription(parser.getText());
                        }else if(currentTag.equals(TINYAPP_ENABLED)){
                            currentTinyApp.setEnabled(Boolean.parseBoolean(parser.getText()));
                        }
                    }
                }
                eventType = parser.next();
            }
        }catch(XmlPullParserException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        // Return the student list
        return tinyapps;
    }
}
