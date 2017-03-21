package com.lidehang.data.collection.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.lidehang.core.util.MongoUtil;
import com.lidehang.data.collection.dao.CompanyDataDao;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

@Service
public class CompanyDataDaoImpl implements CompanyDataDao {

	@Override
	public void addData(String companyId,String serialNumber, List<Document> documents) {
		Set<String> set = new HashSet<>();		
//		List<Document> existed = getData(companyId);
		List<Document> existed = getData(companyId,serialNumber);
		for(Document doc:existed){
			set.add(doc.getString("sign"));
		}
		List<Document> addList = new ArrayList<>();
		for(Document doc:documents){
			if(!set.contains(doc.getString("sign"))){
				addList.add(doc);
			}
		}
		
		if (addList.size() > 0) {
			MongoCollection<Document> collection = MongoUtil.getDatabase().getCollection("c_" + companyId);
			collection.insertMany(addList);
		}
	}

	@Override
	public List<Document> getData(String companyId, String serialNumber) {
		MongoCollection<Document> collection = MongoUtil.getDatabase().getCollection("c_"+companyId);
		//Filters.eq("serialNumber", serialNumber)
		FindIterable<Document> findIterable = collection.find(Filters.eq("serialNumber", serialNumber));
		List<Document> list = new ArrayList<>();
		MongoCursor<Document> mongoCursor = findIterable.iterator();  
		while(mongoCursor.hasNext()){
			list.add(mongoCursor.next());
		}
		return list;
	}

	@Override
	public List<Document> getDataByType(String companyId, String type) {
		MongoCollection<Document> collection = MongoUtil.getDatabase().getCollection("c_"+companyId);
		//Filters.eq("serialNumber", serialNumber)
		String serialNumber1=null;
		String serialNumber2=null;
		if("国税".equals(type)){
			serialNumber1="10000";
			serialNumber2="11000";
		}
		FindIterable<Document> findIterable = collection.find(Filters.and(Filters.gt("serialNumber", serialNumber1),Filters.lt("serialNumber", serialNumber2)));
				//.lt("serialNumber", serialNumber));
		List<Document> list = new ArrayList<>();
		MongoCursor<Document> mongoCursor = findIterable.iterator();  
		while(mongoCursor.hasNext()){
			list.add(mongoCursor.next());
		}
		return list;
	}

}
