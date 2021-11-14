package com.smn.el.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuditDetailsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @JsonInclude(JsonInclude.Include.NON_NULL) 
	private String createdBy;

    @JsonInclude(JsonInclude.Include.NON_NULL) 
	private Date createdDate;

	private String lastModifiedBy;

	private Date lastModifiedDate;
}
