package com.monese.api.banking.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author C.SajjadHussain
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {
	
	private static final long serialVersionUID = -6829087549856795851L;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer code;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String errorMessage;
	
	@JsonIgnore
	private Throwable error;
}
