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

package org.kuali.mobility.shared.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.service.AdminService;
import org.kuali.mobility.configparams.entity.ConfigParam;
import org.kuali.mobility.configparams.service.ConfigParamService;
import org.kuali.mobility.emergencyinfo.entity.EmergencyInfo;
import org.kuali.mobility.emergencyinfo.service.EmergencyInfoService;
import org.kuali.mobility.news.service.NewsService;
import org.kuali.mobility.shared.Wrapper;
import org.springframework.context.ApplicationContext;

public abstract class BootstrapListener implements ServletContextListener {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BootstrapListener.class);

	public void contextInitialized(ServletContextEvent event) {		
		LOG.info("BootstrapListener started initializing...");

		ApplicationContext ctx = org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

		Wrapper useBootstrapping = (Wrapper) ctx.getBean("useBootstrappingFlag");

		if ("true".equals(useBootstrapping.getValue())) {
			AdminService adminService = (AdminService) ctx.getBean("AdminService");
	
			HomeScreen home = bootstrapHomeScreenTools(event, adminService);
			if (home == null) {
				return;
			}
			
			adminService.saveHomeScreen(home);
			
			ConfigParamService configParamService = (ConfigParamService) ctx.getBean("ConfigParamService");
			NewsService newsService = (NewsService) ctx.getBean("newsService");
			EmergencyInfoService emergencyInfoService = (EmergencyInfoService) ctx.getBean("EmergencyInfoService");
			
			EmergencyInfo ei = new EmergencyInfo();
			ei.setCampus("UA");
			ei.setLink("1-812-555-1234");
			ei.setTitle("Police");
			ei.setType("PHONE");
			emergencyInfoService.saveEmergencyInfo(ei);
	
			ei = new EmergencyInfo();
			ei.setCampus("BL");
			ei.setLink("1-812-555-1234");
			ei.setTitle("Police");
			ei.setType("PHONE");
			emergencyInfoService.saveEmergencyInfo(ei);
	
			ei = new EmergencyInfo();
			ei.setCampus("IN");
			ei.setLink("1-812-555-1234");
			ei.setTitle("Police");
			ei.setType("PHONE");
			emergencyInfoService.saveEmergencyInfo(ei);
	
			ei = new EmergencyInfo();
			ei.setCampus("CO");
			ei.setLink("1-812-555-1234");
			ei.setTitle("Police");
			ei.setType("PHONE");
			emergencyInfoService.saveEmergencyInfo(ei);
	
			ei = new EmergencyInfo();
			ei.setCampus("EA");
			ei.setLink("1-812-555-1234");
			ei.setTitle("Police");
			ei.setType("PHONE");
			emergencyInfoService.saveEmergencyInfo(ei);
	
			ei = new EmergencyInfo();
			ei.setCampus("KO");
			ei.setLink("1-812-555-1234");
			ei.setTitle("Police");
			ei.setType("PHONE");
			emergencyInfoService.saveEmergencyInfo(ei);
	
			ei = new EmergencyInfo();
			ei.setCampus("NW");
			ei.setLink("1-812-555-1234");
			ei.setTitle("Police");
			ei.setType("PHONE");
			emergencyInfoService.saveEmergencyInfo(ei);
	
			ei = new EmergencyInfo();
			ei.setCampus("SB");
			ei.setLink("1-812-555-1234");
			ei.setTitle("Police");
			ei.setType("PHONE");
			emergencyInfoService.saveEmergencyInfo(ei);
	
			ei = new EmergencyInfo();
			ei.setCampus("SE");
			ei.setLink("1-812-555-1234");
			ei.setTitle("Police");
			ei.setType("PHONE");
			emergencyInfoService.saveEmergencyInfo(ei);
	
			ConfigParam param = new ConfigParam();
			param.setName("Admin.Group.Name");
			param.setValue("KME-ADMINISTRATORS");
			configParamService.saveConfigParam(param);
	
			param = new ConfigParam();
			param.setName("Backdoor.Group.Name");
			param.setValue("KME-BACKDOOR");
			configParamService.saveConfigParam(param);
	
			param = new ConfigParam();
			param.setName("Sakai.Url.Base");
			param.setValue("https://regression.oncourse.iu.edu/oauthdirect/");
			configParamService.saveConfigParam(param);
	
			param = new ConfigParam();
			param.setName("Alerts.CacheUpdate.Minute");
			param.setValue("5");
			configParamService.saveConfigParam(param);
			
			param = new ConfigParam();
			param.setName("News.CacheUpdate.Minute");
			param.setValue("5");
			configParamService.saveConfigParam(param);
			
			param = new ConfigParam();
			param.setName("News.Sample.Size");
			param.setValue("3");
			configParamService.saveConfigParam(param);
	
			param = new ConfigParam();
			param.setName("CAMPUS_STATUS_XML_URL");
			param.setValue("https://test.uisapp2.iu.edu/my2-unt/DataExport.do?__p_dispatch__=campusStatus&campus=");
			configParamService.saveConfigParam(param);
					
			param = new ConfigParam();
			param.setName("Food.Url.SE");
			param.setValue("http://gus.ius.edu/dining-services/feed/?format=xml");
			configParamService.saveConfigParam(param);
			
	//		NewsSource newsSource = new NewsSourceDBImpl();
	//		newsSource.setId( Long.valueOf( (long)4 ) );
	//		newsSource.setActive(true);
	//		newsSource.setOrder(4);
	//		newsSource.setUrl("http://rss.cnn.com/rss/cnn_allpolitics.rss");
	//		LOG.debug( "Creating news source for "+newsSource.getUrl() );
	//		newsService.saveNewsSource(newsSource);
	//		
	//		newsSource = new NewsSourceDBImpl();
	//		newsSource.setId( Long.valueOf( (long)3 ) );
	//		newsSource.setActive(true);
	//		newsSource.setOrder(3);
	//		newsSource.setUrl("http://rss.cnn.com/rss/cnn_tech.rss");
	//		LOG.debug( "Creating news source for "+newsSource.getUrl() );
	//		newsService.saveNewsSource(newsSource);
	//		
	//		newsSource = new NewsSourceDBImpl();
	//		newsSource.setId( Long.valueOf( (long)0 ) );
	//		newsSource.setActive(true);
	//		newsSource.setOrder(0);
	//		newsSource.setUrl("http://feeds.bbci.co.uk/news/rss.xml");
	//		LOG.debug( "Creating news source for "+newsSource.getUrl() );
	//		newsService.saveNewsSource(newsSource);
	//		
	//		newsSource = new NewsSourceDBImpl();
	//		newsSource.setId( Long.valueOf( (long)1 ) );
	//		newsSource.setActive(true);
	//		newsSource.setOrder(1);
	//		newsSource.setUrl("http://rss.cnn.com/rss/cnn_world.rss");
	//		LOG.debug( "Creating news source for "+newsSource.getUrl() );
	//		newsService.saveNewsSource(newsSource);
	//		
	//		newsSource = new NewsSourceDBImpl();
	//		newsSource.setId( Long.valueOf( (long)2 ) );
	//		newsSource.setActive(true);
	//		newsSource.setOrder(2);
	//		newsSource.setUrl("http://rss.cnn.com/rss/cnn_health.rss");
	//		LOG.debug( "Creating news source for "+newsSource.getUrl() );
	//		newsService.saveNewsSource(newsSource);					
			
			LOG.info("Count: " + adminService.getAllHomeScreens().size());
			
			LOG.info("BootstrapListener finished initializing.");
		}
	}

	public abstract HomeScreen bootstrapHomeScreenTools(ServletContextEvent event, AdminService adminService);

	public void contextDestroyed(ServletContextEvent event) {}

}
