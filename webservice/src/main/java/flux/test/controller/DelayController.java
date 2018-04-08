package flux.test.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class DelayController {

    @GetMapping("/blocking/{delay}")
    public String getResponse(@PathVariable int delay) throws InterruptedException {
        Thread.sleep(delay);
        return "Response";
    }

    @GetMapping("/reactive/{delay}")
    public Mono<String> getReactiveResponse(@PathVariable int delay){
        int i=0;
        return Mono.just("Response").delayElement(Duration.ofMillis(delay));
    }


}
