package rxjava.examples.controller;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/foo")
@Api
public class FooController {


    @GetMapping
    public String expensiveGet() throws InterruptedException {
        Thread.sleep(1500l);
        return "Finishing aaa";
    }

    @GetMapping("/newThreads")
    public String expensiveNotThread() throws InterruptedException {
        Observable.fromCallable(() -> {
            Thread.sleep(1500l);
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println);

        return "Finishing rx";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/rx")
    public String expensiveGetRx() throws InterruptedException {
        Observable.fromCallable(() -> {
            Thread.sleep(1500l);
            return "Done";
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println);

        return "Finishing rx";
    }

    @GetMapping("/auth")
    Map<String, Object> getRole() {
        Collection<GrantedAuthority> authorities =
                (Collection<GrantedAuthority>)  SecurityContextHolder.getContext().getAuthentication().getAuthorities(); ;

        List<String> roles = new ArrayList<>();

        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }
        return Collections.singletonMap("role", roles);
    }

}
