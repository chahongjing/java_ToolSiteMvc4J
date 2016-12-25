package com.zjy.baseframework;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XmlHelper {

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
