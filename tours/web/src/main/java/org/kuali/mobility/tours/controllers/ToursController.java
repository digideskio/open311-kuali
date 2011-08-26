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

package org.kuali.mobility.tours.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.kuali.mobility.tours.entity.POI;
import org.kuali.mobility.tours.entity.Tour;
import org.kuali.mobility.tours.service.ToursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
@RequestMapping("/tours")
public class ToursController {
	
	@Autowired
    private ToursService toursService;
    public void setConfigParamService(ToursService toursService) {
        this.toursService = toursService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel) {
    	uiModel.addAttribute("tours", toursService.findAllTours());
    	return "tours/index";
    }
    
    @RequestMapping(value="edit", method = RequestMethod.GET)
    public String edit(Model uiModel) {
    	return "tours/edit";
    }
    
    @RequestMapping(value="edit/{tourId}", method = RequestMethod.GET)
    public String editTour(Model uiModel, @PathVariable("tourId") long tourId) {
    	Tour tour = toursService.findTourById(tourId);
    	String tourJson = JSONSerializer.toJSON(tour).toString();
    	uiModel.addAttribute("tourJson", tourJson);
    	return "tours/edit";
    }
    
    @RequestMapping(value="edit", method = RequestMethod.POST)
    public String save(@RequestParam("data") String postData,  Model uiModel) {
    	Tour tour = convertFromJson(postData);
    	toursService.saveTour(tour);
    	return "tours/index";
    }
    
    @RequestMapping(value="delete/{tourId}", method = RequestMethod.GET)
    public String delete(@PathVariable("tourId") Long tourId, Model uiModel) {
    	toursService.deleteTourById(tourId);
    	return index(uiModel);
    }
    
    @SuppressWarnings("unchecked")
	private Tour convertFromJson(String json) {
    	Tour tour = new Tour();
    	JSONObject tourJson = (JSONObject) JSONSerializer.toJSON(json);
    	
    	try {
    		tour.setTourId(tourJson.getLong("id"));
    	} catch (Exception e) {
    		tour.setTourId(null);
    	}
    	tour.setName(tourJson.getString("name"));
    	tour.setDescription(tourJson.getString("description"));
    	tour.setPath(tourJson.getString("path"));
    	try {
    		tour.setVersionNumber(tourJson.getLong("version"));
    	} catch (Exception e) {
    		tour.setVersionNumber(null);
    	}
    	
    	JSONArray pointsOfInterest = tourJson.getJSONArray("POIs");
		List<POI> POIs = new ArrayList<POI>();
		for (Iterator<JSONObject> iter = pointsOfInterest.iterator(); iter.hasNext();) {
			JSONObject pointOfInterest = iter.next();
			POI poi = new POI();
			
			try {
				poi.setPoiId(pointOfInterest.getLong("poiId"));
	    	} catch (Exception e) {
	    		poi.setPoiId(null);
	    	}
			
			poi.setName(pointOfInterest.getString("name"));
			try {
				poi.setLocationId(pointOfInterest.getString("id"));
	    	} catch (Exception e) {
	    		poi.setLocationId(null);
	    	}
			poi.setType(pointOfInterest.getString("type"));
			
			JSONObject location = pointOfInterest.getJSONObject("location");
			poi.setLatitude(location.getDouble("Oa"));
			poi.setLongitude(location.getDouble("Pa"));
			
			poi.setTourId(tour.getTourId());
			poi.setTour(tour);
			POIs.add(poi);
		}
    	tour.setPointsOfInterest(POIs);
    	
    	return tour;
    }
}
