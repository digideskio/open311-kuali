/**
 * Copyright 2011-2012 The Kuali Foundation Licensed under the
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

package org.kuali.mobility.open311.domain;

import java.io.Serializable;
import java.util.List;

import org.kuali.mobility.open311.entity.SubmissionAttribute;
import org.springframework.web.multipart.MultipartFile;

public class Incident implements Serializable {

	private static final long serialVersionUID = 1844528404920336947L;
        
	private Long id;
	
	private String newComment;
	
    private String userAgent;
    
    private String summary;
    
    private String email;
    
    private String affiliationStudent;
    
    private String affiliationFaculty;
    
    private String affiliationStaff;

    private String affiliationOther;
    
    private boolean contactMe;

    private MultipartFile file;

    private List<SubmissionAttribute> attachments;

    private List<SubmissionAttribute> comments;
    
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAffiliationStudent() {
		return affiliationStudent;
	}

	public void setAffiliationStudent(String affiliationStudent) {
		this.affiliationStudent = affiliationStudent;
	}

	public String getAffiliationFaculty() {
		return affiliationFaculty;
	}

	public void setAffiliationFaculty(String affiliationFaculty) {
		this.affiliationFaculty = affiliationFaculty;
	}

	public String getAffiliationStaff() {
		return affiliationStaff;
	}

	public void setAffiliationStaff(String affiliationStaff) {
		this.affiliationStaff = affiliationStaff;
	}

	public String getAffiliationOther() {
		return affiliationOther;
	}

	public void setAffiliationOther(String affiliationOther) {
		this.affiliationOther = affiliationOther;
	}

	public boolean isContactMe() {
		return contactMe;
	}

	public void setContactMe(boolean contactMe) {
		this.contactMe = contactMe;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SubmissionAttribute> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<SubmissionAttribute> attachments) {
		this.attachments = attachments;
	}

	public List<SubmissionAttribute> getComments() {
		return comments;
	}

	public void setComments(List<SubmissionAttribute> comments) {
		this.comments = comments;
	}

	public String getNewComment() {
		return newComment;
	}

	public void setNewComment(String newComment) {
		this.newComment = newComment;
	}
    
}
