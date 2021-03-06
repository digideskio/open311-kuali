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

package org.kuali.mobility.configparams.dao;

import java.util.List;

import org.kuali.mobility.configparams.entity.ConfigParam;

/**
 * Interface for a contract for manipulating configuration parameters
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 *
 */
public interface ConfigParamDao {
	
	/**
	 * @param id the id of the ConfigParam to retrieve
	 * @return the ConfigParam matching the id
	 */
    public ConfigParam findConfigParamById(Long id);
    /**
     * @param name the name of the ConfigParam to retrieve
     * @return the ConfigParam matching the name
     */
    public ConfigParam findConfigParamByName(String name);
    /**
     * @param configParam the ConfigParam to save
     * @return the id of the saved ConfigParam
     */
    public Long saveConfigParam(ConfigParam configParam);
    /**
     * @return a list of all configuration parameters
     */
    public List<ConfigParam> findAllConfigParam();
    /**
     * @param id the id of the ConfigParam to delete
     */
    public void deleteConfigParamById(Long id);
    
}
