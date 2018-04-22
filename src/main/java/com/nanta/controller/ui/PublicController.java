package com.nanta.controller.ui;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nanta.base.UiPath;
import com.nanta.dto.PostDto;
import com.nanta.entity.Page;
import com.nanta.exception.PageNotFoundException;
import com.nanta.service.BlogService;
import com.nanta.service.PageService;
import com.nanta.validator.Validator;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PublicController {
  @Autowired
  private PageService pageService;
  @Autowired
  private BlogService blogService;
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
  private final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

  private ModelAndView generateSEOPage(Page page) {
    ModelAndView model = new ModelAndView();
    model.addObject("url", page.getUrl());
    model.addObject("description", page.getDescription());
    model.addObject("keywords", page.getKeywords());
    model.addObject("robots", page.getRobots());
    return model;
  }

  @RequestMapping(value = UiPath.HOME, method = RequestMethod.GET)
  public ModelAndView dashboardPage(HttpServletRequest request, ModelAndView model)
      throws Exception {
    log.warn(request.getRequestURL().toString());
    this.pageService.accessPage(request.getRequestURL().toString());
    Page page = this.pageService.findByUrl(request.getRequestURL().toString());
    if (Validator.isAvailable(page)) {
      model = generateSEOPage(page);
    }
    model.setViewName("/home");
    return model;
  }

  @RequestMapping(value = UiPath.GALLERY, method = RequestMethod.GET)
  public ModelAndView galleryPage(HttpServletRequest request, ModelAndView model) throws Exception {
    log.warn(request.getRequestURL().toString());
    this.pageService.accessPage(request.getRequestURL().toString());
    Page page = this.pageService.findByUrl(request.getRequestURL().toString());
    if (Validator.isAvailable(page)) {
      model = generateSEOPage(page);
    }
    model.setViewName("/gallery");
    return model;
  }

  @RequestMapping(value = UiPath.ABOUT, method = RequestMethod.GET)
  public ModelAndView aboutPage(HttpServletRequest request, ModelAndView model) throws Exception {
    log.warn(request.getRequestURL().toString());
    this.pageService.accessPage(request.getRequestURL().toString());
    Page page = this.pageService.findByUrl(request.getRequestURL().toString());
    if (Validator.isAvailable(page)) {
      model = generateSEOPage(page);
    }
    model.setViewName("/about");
    return model;
  }

  @RequestMapping(value = UiPath.BLOG, method = RequestMethod.GET)
  public ModelAndView blogPage(HttpServletRequest request, ModelAndView model) throws Exception {
    log.warn(request.getRequestURL().toString());
    this.pageService.accessPage(request.getRequestURL().toString());
    Page page = this.pageService.findByUrl(request.getRequestURL().toString());
    if (Validator.isAvailable(page)) {
      model = generateSEOPage(page);
    }
    model.setViewName("/blog");
    return model;
  }

  @RequestMapping(value = UiPath.BLOG + "/{url}", method = RequestMethod.GET)
  public ModelAndView postPage(@PathVariable String url, HttpServletRequest request,
      ModelAndView model) throws Exception {
    log.warn(request.getRequestURL().toString());
    this.pageService.accessPage(request.getRequestURL().toString());
    Page page = this.pageService.findByUrl(request.getRequestURL().toString());
    PostDto postDto = this.blogService.findByUrl(url);

    if (Validator.isAvailable(postDto)) {
      model.addObject("createdBy", postDto.getCreatedBy());
      model.addObject("createdDate", this.dateFormat.format(postDto.getCreatedDate()));
      model.addObject("year", this.yearFormat.format(postDto.getCreatedDate()));
      model.addObject("image", postDto.getImage());
      model.addObject("post", postDto.getPost());
      model.addObject("title", postDto.getTitle());
      if (Validator.isAvailable(page)) {
        model = generateSEOPage(page);
      }
    } else {
      throw new PageNotFoundException();
    }

    model.setViewName("/post");
    return model;
  }
}
