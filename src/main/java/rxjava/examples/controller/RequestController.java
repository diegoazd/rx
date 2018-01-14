package rxjava.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rxjava.examples.tasks.Reactive;
import rxjava.examples.tasks.SlowTasks;

@RestController
@RequestMapping("/rx")
public class RequestController {
    @Autowired
    EventService eventService;

    @GetMapping("blocking")
    public String tradiotionalSlow() {
        new SlowTasks().process();
        return "done";
    }

    @GetMapping("rx")
    public String nonBlocking() {
        new Reactive().process();
        return "done";
    }

    @GetMapping("subscribe")
    public String subscribe() {
        eventService.subscriber();
        return "done";
    }
}
