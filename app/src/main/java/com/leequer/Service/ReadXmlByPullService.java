package com.leequer.Service;

import android.util.Xml;

import com.leequer.Demo.Person;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * ʹ��pull����xml
 * 2010-04-26
 * @author leequer
 *
 */
public class ReadXmlByPullService {
	
	public static List<Person> ReadXmlByPull (InputStream inputStream)throws Exception
	{
		List <Person> personList = null;
		/**
		 * android�������ṩ��xml �����õ�xmlpull������
		 */
		XmlPullParser xmlpull = Xml.newPullParser();
		/**
		 * ������������ �趨���뷽ʽ
		 * 
		 */
		xmlpull.setInput(inputStream, "utf-8");
		/**
		 * pull����xml�� ��������
		 *     ��ȡ��xml��������������0 START_DOCUMENT;
			   ��ȡ��xml�Ľ��������1 END_DOCUMENT ;
			   ��ȡ��xml�Ŀ�ʼ��ǩ��������2 START_TAG
			   ��ȡ��xml�Ľ����ǩ��������3 END_TAG
			   ��ȡ��xml���ı���������4 TEXT
		 */
		int eventCode = xmlpull.getEventType();
		/**
		 * ֻҪ����¼����صĲ���1 ���Ǿ�һֱ��ȡxml�ļ�
		 */
		Person person = null;
		while(eventCode!=XmlPullParser.END_DOCUMENT)
		{
			
			switch (eventCode)
			{
				case XmlPullParser.START_DOCUMENT:
				{//��ʼ�ĵ� new����
					personList =new  ArrayList<Person>();
					break;
				}
				case XmlPullParser.START_TAG:
				{
					if("person".equals(xmlpull.getName()))
					{
						person =  new Person();
						person.setId(xmlpull.getAttributeValue(0));
					}else if (person!=null)
					{
						if(("name".equals(xmlpull.getName())))
						{
							/**
							 * ���ǰԪ�ص���һ��Ԫ�����ı��ڵ� �Ϳ���ֱ����nextText()����������õ��ı��ڵ������
							 */
							person.setName(xmlpull.nextText());
						}else if ("age".equals(xmlpull.getName()))
						{
							/**
							 * ���ǰԪ�ص���һ��Ԫ�����ı��ڵ� �Ϳ���ֱ����nextText()����������õ��ı��ڵ������
							 */
							person.setAge(xmlpull.nextText());
						}
					}
					break;
				}
					
				case XmlPullParser.END_TAG:
				{ 
					if("person".equals(xmlpull.getName())&&person!=null)
					{
						personList.add(person);
						person = null;
					}
					break;
				}
			}
			
			eventCode = xmlpull.next();//û�н���xml�ļ����Ƶ��¸����н���
			
			
		}
		
		return personList;
	}
}
