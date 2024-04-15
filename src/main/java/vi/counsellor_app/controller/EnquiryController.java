package vi.counsellor_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vi.counsellor_app.entity.Enquiry;
import vi.counsellor_app.repository.CounsellorRepo;
import vi.counsellor_app.repository.EnquiryRepo;
import vi.counsellor_app.service.EnquiryService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class EnquiryController {

 @Autowired
 EnquiryService enquiryService;

 @Autowired
 EnquiryRepo enquiryRepo;

 @Autowired
 CounsellorRepo counsellorRepo;

    // add enq - page display

 @GetMapping("/enquiry")
 public String addEnquiry(Enquiry enq, Model model) {
  model.addAttribute("enq", new Enquiry()) ; // used for form binding sending Enquiry object for form binding

    return "addEnq";

   }

   @PostMapping("/enquiry")
    // save enq
 public  String saveEnquiry(Enquiry enq, HttpServletRequest req, Model model) {

  HttpSession session = req.getSession(false); // DON'T CREATE THE NEW SESSION OBJECT , GET THE ALREADY CREATED SESSION

  Integer cid = (Integer) session.getAttribute("cid");

  boolean status = enquiryService.addEnquiry(enq,cid);
  if(status) {
   model.addAttribute("smsg","Enquiry Saved Successfully..");
  } else {
   model.addAttribute("emsg","Not able to save the enquiry..");
  }

  return "addEnq";
 }


    // view enqs

 @GetMapping("/enquiries")
 public  String getEnquiries(HttpServletRequest req, Model model) {

  HttpSession session = req.getSession(false);
  Integer cid = (Integer) session.getAttribute("cid");

  // passion empty enquiry object to get all the enquiries

  List<Enquiry> list = enquiryService.getEnquiries(new Enquiry(),cid);
  model.addAttribute("enqs",list);

  // sending empty object to the ui(using model scope) to map the filter binding objects
  model.addAttribute("enq",new Enquiry());

  return "viewEnquiries";
 }

    // filter enqs

 @PostMapping("/filter-enqs")
public String filterEnqs(@ModelAttribute("enq") Enquiry enq, HttpServletRequest req, Model model) {

  HttpSession session = req.getSession(false);
  Integer cid = (Integer) session.getAttribute("cid");

  List<Enquiry> list = enquiryService.getEnquiries(enq, cid);
  model.addAttribute("enqs",list);


  return "viewEnquiries";
}

    // edit and update enq
 @GetMapping("/edit")
 public String editEnquiry(@RequestParam("enqId") Integer enqId, Model model) {

  Enquiry enquiry = enquiryService.getEnquiry(enqId);

  model.addAttribute("enq",enquiry);


  return "addEnq";
 }

 }
