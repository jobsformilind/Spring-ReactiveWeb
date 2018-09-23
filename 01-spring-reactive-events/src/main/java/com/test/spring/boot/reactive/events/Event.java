package com.test.spring.boot.reactive.events;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
	private long id;
	private Date when;

}
