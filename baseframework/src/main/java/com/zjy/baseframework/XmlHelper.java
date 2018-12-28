package com.zjy.baseframework;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XmlHelper {

    private XmlHelper() {}

    public static <T> void toXml(T obj, String path) {
//        @XmlType(propOrder = {"id", "name", "activities", "transitions"})
//        @XmlAccessorType(XmlAccessType.FIELD)
//        @XmlRootElement(name = "wp")

//        @XmlElement(name = "choiceInteraction")
//        @XmlAttribute(name = "Name")
//        @XmlElementWrapper(name = "Activities") // list节点外层包含节点
//        @XmlValue
        JAXBContext context;
        try (FileWriter sw = new FileWriter(path)) {
            context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.marshal(obj, System.out);
            marshaller.marshal(obj, sw);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
    public static <T> T toObject(Class<T> clazz, String path) {
        JAXBContext context = null;
        try (FileInputStream fis = new FileInputStream(path)){
            context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            return (T)unmarshaller.unmarshal(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String serialize(Object o) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        serialize(stream, o);
        return stream.toString();
    }

    public static Object deserialize(String value) {
        ByteArrayInputStream stream = new ByteArrayInputStream(value.getBytes());
        return deserialize(stream);
    }

    public static void serializeToFile(Object o, String filePath) throws IOException {
        FileOutputStream stream = new FileOutputStream(filePath);
        serialize(stream, o);
        stream.flush();
        stream.close();
    }

    public static Object deserializeFromFile(String filePath) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(filePath);
        return deserialize(stream);
    }

    private static void serialize(OutputStream stream, Object o) {
        XMLEncoder xe = new XMLEncoder(stream, "UTF-8", true, 0);
        xe.writeObject(o);
        xe.close();
    }

    private static Object deserialize(InputStream stream) {
        XMLDecoder xe = new XMLDecoder(stream);
        Object o = xe.readObject();
        xe.close();
        return o;
    }

	/*
     * SerialableObject o = new SerialableObject(); o.setId(2);
	 * o.setName("asdfasdf"); o.setValue(21314);
	 * 
	 * SerialableObject o2 = new SerialableObject(); o2.setId(24);
	 * o2.setName("曾军毅"); o2.setValue(73);
	 * 
	 * List<SerialableObject> list = new ArrayList<SerialableObject>();
	 * list.add(o); list.add(o2);
	 * 
	 * String jsonString = JSON.toJSONString(o);
	 * 
	 * System.out.println(jsonString);
	 * System.out.println(JSON.toJSONString(list));
	 * 
	 * SerialableObject o3 = new SerialableObject(); o3 =
	 * JSON.parseObject(jsonString, SerialableObject.class);
	 */
}
