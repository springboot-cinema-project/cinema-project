package com.movie.controller;

import com.movie.domain.Coupons;
import com.movie.domain.Movies;
import com.movie.service.CouponService;
import com.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
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

        long result = movieService.insertMovie(movies);

        if(result > 0) {
            return "redirect:/admin/";
        } else {
            return "redirect:/admin/movie/create";
        }
    }

    @GetMapping("/movie/list")
    public String movieList(Model model) {
        model.addAttribute("content", "admin/movie/movie_list");
        model.addAttribute("title", "admin-movie-list");
        return "admin/layout/admin_base";
    }

    @GetMapping("/movie/set")
    public String movieSet(Model model) {
        model.addAttribute("content", "admin/movie/movie_set");
        model.addAttribute("title", "admin-movie-set");
        return "admin/layout/admin_base";
    }

    @GetMapping("/movie/update")
    public String movieUpdate(Model model) {
        model.addAttribute("content", "admin/movie/movie_update");
        model.addAttribute("title", "admin-movie-update");
        return "admin/layout/admin_base";
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
            return "redirect:/admin/";
        } else {
            return "redirect:/admin/movie/create";
        }
    }

    @GetMapping("/coupon/list")
    public String couponList(Model model) {

        List<Coupons> list = couponService.couponList();

        model.addAttribute("content", "admin/coupon/coupon_list");
        model.addAttribute("title", "admin-coupon-list");
        model.addAttribute("coupons", list);

        return "admin/layout/admin_base";
    }

    @GetMapping("/coupon/update")
    public String updateCoupon(@RequestParam("id") long id, Model model) {

        Coupons coupon = couponService.selectCoupon(id);

        model.addAttribute("content", "admin/coupon/coupon_update");
        model.addAttribute("title", "admin-coupon-list");
        model.addAttribute("coupon", coupon);

        return "admin/layout/admin_base";
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

    @GetMapping("/event/update")
    public String eventUpdate(Model model) {
        model.addAttribute("content", "admin/event/event_update");
        model.addAttribute("title", "admin-event-update");
        return "admin/layout/admin_base";
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
