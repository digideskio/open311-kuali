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

package org.kuali.mobility.dining.service;

import java.util.List;

import org.kuali.mobility.dining.entity.Menu;
import org.kuali.mobility.dining.entity.Place;

public interface DiningService {
	public List<Menu> getMenus(String location);
	
	public List<Place> getPlaces();
	
	public String getMenusJson( final String name, final String location );

}
