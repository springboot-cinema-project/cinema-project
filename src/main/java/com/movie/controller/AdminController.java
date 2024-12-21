package com.movie.controller;

import com.movie.domain.Coupons;
import com.movie.domain.Events;
import com.movie.domain.Movies;
import com.movie.domain.Schedules;
import com.movie.service.CouponService;
import com.movie.service.EventService;
import com.movie.service.MovieService;
import com.movie.service.ScheduleService;
import com.movie.util.MovieSchedulesWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final MovieService movieService;
    private final CouponService couponService;
    private final EventService eventService;
    private final ScheduleService scheduleService;

    @GetMapping({"", "/"})
    public String admin(Model model) {
        model.addAttribute("content", "admin/index");
        model.addAttribute("title", "admin");
        return "admin/layout/admin_base";
    }

    @GetMapping("/movie/create")
    public String movieCreate(Model model) {
        model.addAttribute("content", "admin/movie/movie_create");
        model.addAttribute("title", "admin-movie-create");
        return "admin/layout/admin_base";
    }

    @PostMapping("/movie/create")
    public String insertMovie(Movies movies) {

        long id = movieService.insertMovie(movies);

        if(id > 0) {
            return "redirect:/admin/movie/set?id=" + id;
        } else {
            return "redirect:/admin/movie/create";
        }
    }

    @GetMapping("/movie/list")
    public String movieManageList(Model model) {

        List<Movies> movies = movieService.movieManageList();

        model.addAttribute("content", "admin/movie/movie_list");
        model.addAttribute("title", "admin-movie-list");
        model.addAttribute("movies", movies);

        return "admin/layout/admin_base";
    }

    @GetMapping("/movie/set")
    public String movieSet(@RequestParam("id") long id, Model model) {

        Movies movies = movieService.movieDetail(id);

        model.addAttribute("content", "admin/movie/movie_set");
        model.addAttribute("title", "admin-movie-set");
        model.addAttribute("movie", movies);

        return "admin/layout/admin_base";
    }

    @PostMapping("/movie/set")
    public String scheduleSet(@ModelAttribute MovieSchedulesWrapper schedulesWrapper) {

        List<Schedules> schedules = schedulesWrapper.getSchedules();

        schedules.forEach(schedule -> {
            System.out.println("Received Schedule: " + schedule);
        });

        scheduleService.insertScheduleWithSeat(schedules);

        return "redirect:/admin/movie/list";

    }

    @GetMapping("/movie/update")
    public String movieManage(@RequestParam("id") long id, Model model) {

        Movies movie = movieService.movieDetail(id);

        model.addAttribute("content", "admin/movie/movie_update");
        model.addAttribute("title", "admin-movie-update");
        model.addAttribute("movie", movie);

        return "admin/layout/admin_base";
    }

    @PostMapping("/movie/update")

    public String movieUpdate(Movies movies) {

        long result = movieService.updateMovie(movies);

        if(result > 0) {
            return "redirect:/admin/movie/list";
        } else {

            long id = movies.getId();

            return "redirect:/admin/movie/update";
        }
    }

    @PostMapping("/movie/delete")
    @ResponseBody
    public String deleteMovie(@RequestParam("id") long id) {

        long result = movieService.deleteMovie(id);

        if(result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/coupon/create")
    public String couponCreate(Model model) {

        model.addAttribute("content", "admin/coupon/coupon_create");
        model.addAttribute("title", "admin-coupon-create");
        return "admin/layout/admin_base";
    }

    @PostMapping("/coupon/create")
    public String insertCoupon(Coupons coupons) {

        long result = couponService.insertCoupon(coupons);

        if(result > 0) {
            return "redirect:/admin/coupon/list";
        } else {
            return "redirect:/admin/coupon/create";
        }
    }

    @GetMapping("/coupon/list")
    public String couponList(Model model) {

        List<Coupons> coupons = couponService.couponList();

        model.addAttribute("content", "admin/coupon/coupon_list");
        model.addAttribute("title", "admin-coupon-list");
        model.addAttribute("coupons", coupons);

        return "admin/layout/admin_base";
    }

    @GetMapping("/coupon/update")
    public String couponManage(@RequestParam("id") long id, Model model) {

        Coupons coupon = couponService.couponDetail(id);

        model.addAttribute("content", "admin/coupon/coupon_update");
        model.addAttribute("title", "admin-coupon-list");
        model.addAttribute("coupon", coupon);

        return "admin/layout/admin_base";
    }

    @PostMapping("/coupon/update")
    public String updateCoupon(Coupons coupons) {

        long result = couponService.updateCoupon(coupons);

        if(result > 0) {
            return "redirect:/admin/coupon/list";
        } else {

            long id = coupons.getId();

            return "redirect:/admin/coupon/update?id=" + id;
        }
    }

    @PostMapping("/coupon/delete")
    @ResponseBody
    public String deleteCoupon(@RequestParam("id") long id) {

        long result = couponService.deleteCoupon(id);

        if(result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/event/create")
    public String eventCreate(Model model) {

        List<Coupons> coupons = couponService.couponList();

        model.addAttribute("content", "admin/event/event_create");
        model.addAttribute("title", "admin-event-create");
        model.addAttribute("coupons", coupons);

        return "admin/layout/admin_base";
    }

    @PostMapping("/event/create")
    public String insertEvent(Events events) {

        long result = eventService.insertEvent(events);

        if(result > 0) {
            return "redirect:/admin/event/list";
        } else {
            return "redirect:/admin/event/create";
        }

    }

    @GetMapping("/event/list")
    public String eventList(Model model) {

        List<Events> events = eventService.eventManageList();

        model.addAttribute("content", "admin/event/event_list");
        model.addAttribute("title", "admin-event-list");
        model.addAttribute("events", events);

        return "admin/layout/admin_base";
    }

    @GetMapping("/event/update")
    public String eventDetail(@RequestParam("id") long id, Model model) {

        Events events = eventService.eventDetail(id);
        List<Coupons> coupons = couponService.couponList();

        model.addAttribute("content", "admin/event/event_update");
        model.addAttribute("title", "admin-event-update");
        model.addAttribute("event", events);
        model.addAttribute("coupons", coupons);

        return "admin/layout/admin_base";
    }

    @PostMapping("/event/update")
    public String updateEvent(Events events) {

        long result = eventService.updateEvent(events);

        if(result > 0) {
            return "redirect:/admin/event/list";
        } else {

            long id = events.getId();

            return "redirect:/admin/event/update?id=" + id;
        }

    }

    @PostMapping("/event/delete")
    @ResponseBody
    public String deleteEvent(@RequestParam("id") long id, Model model) {

        long result = eventService.deleteEvent(id);

        if(result > 0) {
            return "success";
        } else {
            return "fail";
        }

    }

    @GetMapping("/users")
    public String users(Model model) {

        model.addAttribute("content", "admin/users/users");
        model.addAttribute("title", "admin-users");
        return "admin/layout/admin_base";
    }

    @GetMapping("/qna")
    public String qna(Model model) {
        model.addAttribute("content", "admin/qna/qna");
        model.addAttribute("title", "admin-qna");
        return "admin/layout/admin_base";
    }
}
