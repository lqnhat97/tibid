package org.tibid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto implements Serializable {

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private long id;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private long updatedDate;
}