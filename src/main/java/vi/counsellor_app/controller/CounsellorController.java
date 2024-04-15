package vi.counsellor_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vi.counsellor_app.dto.Dashboard;
import vi.counsellor_app.entity.Counsellor;
import vi.counsellor_app.service.CounsellorService;
import vi.counsellor_app.service.EnquiryService;

@Controller
public class CounsellorController {

    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private CounsellorService counsellorService;

    @GetMapping("/logout")
    public String logout(HttpServletRequest req,Model model) {
        HttpSession session = req.getSession(false); // get current session create new session = false
        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("counsellor", new Counsellor());
                return "registerView";
    }

    @PostMapping("/register")
    public  String handleRegister(Counsellor c, Model model) {
        boolean status = counsellorService.saveCounsellor(c);
        if(status) {
            model.addAttribute("smsg","Successfully saved..");
        }
        else {
            model.addAttribute("emsg","Failed to save..");

        }
        return "registerView";
    }

    @GetMapping("/")
    public String login(Model model) {
        // form binding object
        model.addAttribute("counsellor", new Counsellor());
        return "index";
    }

    @PostMapping("/login")
    public String handleLogin(Counsellor counsellor, HttpServletRequest req, Model model) {
        Counsellor c = counsellorService.getCounsellor(counsellor.getEmail(), counsellor.getPwd());

        if (c == null) {
            model.addAttribute("emsg", "Invalid Credentials, please enter valid credentials..");

            return "index";
        } else {

            // set counsellor_id in session
            HttpSession session = req.getSession(true); // always new session is created
            session.setAttribute("cid", c.getCounsellorId()); // saving counsellor_id of logged-in user

            Dashboard dbinfo = enquiryService.getDashboardInfo(c.getCounsellorId());
            model.addAttribute("dashboard", dbinfo);
            return "dashboard";
        }
    }
    @GetMapping("/dashboard")
    public String buildDashboard(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true); // always new session is created
        Integer cid = (Integer) session.getAttribute("cid");
        Dashboard dbinfo = enquiryService.getDashboardInfo(cid);
        model.addAttribute("dashboard", dbinfo);
        return "dashboard";
    }
}