/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.kuali.mobility.classifieds.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.mobility.classifieds.entity.Ad;
import org.kuali.mobility.classifieds.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.iu.es.espd.oauth.OAuth2LegService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Service
public class ClassifiedsServiceImpl implements ClassifiedsService {

	@Autowired
	private OAuth2LegService classifiedsOAuthService;

	private String classifiedsURL;

	public List<Category> getCategories(String userId, String campus) throws Exception {
		ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/categoryView/" + campus, "application/json");
		String json = IOUtils.toString(entity.getBody(), "UTF-8");
		List<Category> categories = new JSONDeserializer<List<Category>>().use(null, ArrayList.class).use("values", Category.class).deserialize(json);
		return categories;
	}

	public List<Ad> getAdsByCategoryId(String userId, String campus, Long categoryId) throws Exception {
		ResponseEntity<InputStream> entity = null;
		if (categoryId != null) {
			entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/category/" + categoryId, "application/json");
		} else {
			entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/ads", "application/json");
		}
		String json = IOUtils.toString(entity.getBody(), "UTF-8");
		List<Ad> ads = new JSONDeserializer<List<Ad>>().use(null, ArrayList.class).use("values", Ad.class).deserialize(json);
		return ads;
	}

	public List<Ad> getUsersAds(String userId) throws Exception {
		ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/usersAds", "application/json");
		String json = IOUtils.toString(entity.getBody(), "UTF-8");
		List<Ad> ads = new JSONDeserializer<List<Ad>>().use(null, ArrayList.class).use("values", Ad.class).deserialize(json);
		return ads;
	}

	public Ad getAd(String userId, Long adId) throws Exception {
		ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/ad" + (adId != null ? "/" + adId : ""), "application/json");
		String json = IOUtils.toString(entity.getBody(), "UTF-8");
		Ad ad = new JSONDeserializer<Ad>().deserialize(json, Ad.class);
		return ad;
	}

	public String getPolicy(String userId) throws Exception {
		ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/policy", "application/json");
		String json = IOUtils.toString(entity.getBody(), "UTF-8");
		return json;
	}
	

	public List<Ad> getWatchList(String userId) throws Exception {
		ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/markAds", "application/json");
		String json = IOUtils.toString(entity.getBody(), "UTF-8");
		List<Ad> ads = new JSONDeserializer<List<Ad>>().use(null, ArrayList.class).use("values", Ad.class).deserialize(json);
		return ads;
	}

	public Ad saveAd(String userId, Ad ad) throws Exception {
		ad.setIpAddress("temp ip");
		String sendJson = new JSONSerializer().exclude("class").deepSerialize(ad);
		ResponseEntity<InputStream> entity = null;
		if (ad.getAdId() != null && ad.getLockingNumber() != null) {
			entity = classifiedsOAuthService.oAuthPostRequest(userId, classifiedsURL + "/ad/" + ad.getAdId(), "application/json", sendJson);
		} else {
			entity = classifiedsOAuthService.oAuthPutRequest(userId, classifiedsURL + "/ad", "application/json", sendJson);
		}
		String json = IOUtils.toString(entity.getBody(), "UTF-8");

		int responseCode = entity.getStatusCode().value();
		Ad returnedAd = null;
		if (responseCode >= 200 && responseCode < 300) {
			returnedAd = new Ad();
		} else if (responseCode >= 400) {
			returnedAd = new JSONDeserializer<Ad>().deserialize(json, Ad.class);
		}
		returnedAd.setResponseCode(responseCode);
		return returnedAd;
	}

	public Ad getAdById(String userId, String campus, Long adId) throws Exception {
		ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/adView/" + adId, "application/json");
		String json = IOUtils.toString(entity.getBody(), "UTF-8");
		Ad ad = new JSONDeserializer<Ad>().deserialize(json, Ad.class);
		return ad;
	}

	public List<Ad> getSearchResults(String userId, String campus, String search) throws Exception {
		ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthPostRequest(userId, classifiedsURL + "/search", "application/json", search);
		String json = IOUtils.toString(entity.getBody(), "UTF-8");
		List<Ad> ads = new JSONDeserializer<List<Ad>>().use(null, ArrayList.class).use("values", Ad.class).deserialize(json);
		return ads;
	}

	public void saveCampus(String userId, String campus) throws Exception {
		ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthPostRequest(userId, classifiedsURL + "/saveCampus", "application/json", campus);
	}

	public String getClassifiedsURL() {
		return classifiedsURL;
	}

	public void setClassifiedsURL(String classifiedsURL) {
		this.classifiedsURL = classifiedsURL;
	}
}