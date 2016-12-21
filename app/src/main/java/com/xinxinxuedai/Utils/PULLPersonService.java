//package com.xinxinxuedai.Utils;
//
///**
// * Created by Administrator 于萌萌
// * 创建日期: 14:03 . 2016年12月21日
// * 描述:
// * <p>
// * <p>
// * 备注:
// */
//
//import android.util.Xml;
//
//import com.xinxinxuedai.bean.FuyouChongzhiHuiDiao;
//
//import org.xmlpull.v1.XmlPullParser;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 采用Pull解析XML内容
// */
//public class PULLPersonService {
//
////    /**
////     * 使用pull技术生成xml文件
////     * @param persons
////     * @param writer
////     * @throws Throwable
////     */
////    public static void save(List<Person> persons, Writer writer) throws Throwable{
////        XmlSerializer serializer = Xml.newSerializer();
////        serializer.setOutput(writer);
////        serializer.startDocument("UTF-8", true);
////
////        serializer.startTag(null, "persons");
////        for(Person person : persons){
////            serializer.startTag(null, "person");
////            serializer.attribute(null, "id", person.getId().toString());
////
////            serializer.startTag(null, "name");
////            serializer.text(person.getName());
////            serializer.endTag(null, "name");
////
////            serializer.startTag(null, "age");
////            serializer.text(person.getAge().toString());
////            serializer.endTag(null, "age");
////
////            serializer.endTag(null, "person");
////        }
////        serializer.endTag(null, "persons");
////        serializer.endDocument();
////        writer.flush();
////        writer.close();
////    }
//
//    /**
//     * 使用pull技术解析xml
//     * @param inStream
//     * @return
//     * @throws Throwable
//     */
//    public static List<FuyouChongzhiHuiDiao> getPersons(InputStream inStream) throws Throwable{
//        List<FuyouChongzhiHuiDiao> persons = null;
//        FuyouChongzhiHuiDiao person = null;
//        XmlPullParser parser = Xml.newPullParser();
//        parser.setInput(inStream, "UTF-8");
//        int eventType = parser.getEventType();//产生第一个事件
//        while(eventType!=XmlPullParser.END_DOCUMENT){//只要不是文档结束事件
//            switch (eventType) {
//                case XmlPullParser.START_DOCUMENT:
//                    persons = new ArrayList<FuyouChongzhiHuiDiao>();
//                    break;
//
//                case XmlPullParser.START_TAG:
//                    String name = parser.getName();//获取解析器当前指向的元素的名称
//                    if("RESPONSE".equals(name)){
//                        person = new FuyouChongzhiHuiDiao();
//                        person.set(new Integer(parser.getAttributeValue(0)));
//                    }
//                    if(person!=null){
//                        if("name".equals(name)){
//                            person.setName(parser.nextText());//获取解析器当前指向元素的下一个文本节点的值
//                        }
//                        if("age".equals(name)){
//                            person.setAge(new Short(parser.nextText()));
//                        }
//                    }
//                    break;
//                case XmlPullParser.END_TAG:
//                    if("person".equals(parser.getName())){
//                        persons.add(person);
//                        person = null;
//                    }
//                    break;
//            }
//            eventType = parser.next();
//        }
//        return persons;
//    }
//}
