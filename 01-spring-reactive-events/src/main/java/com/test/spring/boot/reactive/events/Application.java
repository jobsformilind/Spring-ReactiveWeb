package com.test.spring.boot.reactive.events;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/events/{id}")
	public Mono<Event> getbyId(@PathVariable long id) {
		return Mono.just(new Event(id, new Date()));
	}
	
	@GetMapping(value="/events", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Event> getbyStream() {
		Flux<Event> events = Flux.fromStream(Stream.generate(()-> new Event(System.currentTimeMillis(), new Date())));
		Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(5));
		return Flux.zip(events, durationFlux).map(t->t.getT1());
	}

}
