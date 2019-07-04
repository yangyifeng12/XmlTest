package Until;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class XmlsUntil {

    private static final Logger logger = Logger.getLogger(XmlsUntil.class);
    public Map<String, String> getXmlsSql (String url,String fileNName){
        SAXReader reader = new SAXReader();
        String path =url+fileNName;
        File file = new File(path);
        Document document = null;
        Map<String, String> fields = new HashMap<>();
        try
        {
            document = reader.read(file);
            Element root = document.getRootElement();
            Iterator iter =root.elementIterator();
            while (iter.hasNext())
            {
                Element element = (Element) iter.next();
                String name =element.attributeValue("name");
                String type =element.attributeValue("type");
                String length =element.attributeValue("length");
                String scale=element.attributeValue("scale");
//                System.out.println("scale"+scale);
                String field=type+"("+length;
                if(scale==null||scale.equals("")){
                    field+=")";
                    fields.put(name,field);
                }else{
                    field+=","+scale+")";
                    fields.put(name,field);
                }
            }
        }
        catch (DocumentException e)
        {
            logger.error(e);
        }
        return fields;
    }

    public static void main(String[] args) {
        XmlsUntil util=new XmlsUntil();
        String url="E:\\softwares\\Idea\\XmlTest\\out\\production\\XmlTest\\";
        String fileName="sample.xml";
        Map<String, String> result = util.getXmlsSql(url,fileName);

        for(String aa:result.keySet()){
            System.out.println(aa+" "+result.get(aa));
        }
    }
}
