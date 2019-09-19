package com.oaf.loanservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaf.loanservice.domain.SeedUpload;
import com.oaf.loanservice.service.CustomerService;
import com.oaf.loanservice.service.CustomerSummaryService;
import com.oaf.loanservice.service.SeasonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class SeedController {

    private final CustomerService customerService;
    private final SeasonService seasonService;
    private final CustomerSummaryService customerSummaryService;

    public SeedController(CustomerService customerService, SeasonService seasonService,
                          CustomerSummaryService customerSummaryService) {
        this.customerService = customerService;
        this.seasonService = seasonService;
        this.customerSummaryService = customerSummaryService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        System.out.println(getClass() + "- Spring boot is working! Index page is invoked.");
        return "index";
    }

    @RequestMapping(value = "/uploadSeed", method = RequestMethod.GET)
    public ModelAndView welcome() {
        System.out.println(getClass() + "- Welcome page is invoked.");

        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", "Welcome to OAF");
        mav.setViewName("uploadSeed");
        return mav;
    }

    @RequestMapping(value = "seed/upload", method = RequestMethod.POST) // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {


        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        SeedUpload seedUpload = new ObjectMapper().readValue(file.getInputStream(), SeedUpload.class);
        customerService.save(seedUpload.getCustomers());
        seasonService.save(seedUpload.getSeasons());
        customerSummaryService.save(seedUpload.getCustomerSummaries());

        redirectAttributes.addFlashAttribute("seasons", seedUpload.getSeasons());
        redirectAttributes.addFlashAttribute("customers", seedUpload.getCustomers());
        redirectAttributes.addFlashAttribute("summaries", seedUpload.getCustomerSummaries());
        redirectAttributes.addFlashAttribute("repayments", seedUpload.getRepaymentUploads());

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");

        return "redirect:/uploadStatus";
    }

    @RequestMapping(value = "/uploadStatus", method = RequestMethod.GET)
    public String uploadStatus() {
        return "uploadStatus";
    }

}
