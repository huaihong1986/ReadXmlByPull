package com.leequer.Service;


import android.test.InstrumentationTestCase;
import android.util.Log;

import com.leequer.Demo.Person;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Test extends InstrumentationTestCase {
	private String PERSONSTRING = "ObjectPerson";
	public void testReadXml() throws Exception
	{//��װ����
        InputStream inputStream=getInstrumentation().getTargetContext().getAssets().open("new_file.xml");


        Log.v("abc", ""+inputStream);
        List<Person> personsList = ReadXmlByPullService.ReadXmlByPull(inputStream);
		Log.v("abc", personsList.get(0).getName());

		for (Iterator iterator = personsList.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			Log.v(PERSONSTRING, person.toString());
		}
		
	}
}
