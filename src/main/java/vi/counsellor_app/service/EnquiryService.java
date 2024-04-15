package vi.counsellor_app.service;

import org.springframework.stereotype.Service;
import vi.counsellor_app.dto.Dashboard;
import vi.counsellor_app.entity.Enquiry;

import java.util.List;

@Service
public interface EnquiryService {

    // logic for services methods of the application or functionalities

    // get dashboard info based on counsellor id
    public Dashboard getDashboardInfo(Integer counsellorId);

    // add new enquiry with the help of enquiry object
    public boolean addEnquiry(Enquiry enq,Integer counsellorId);

    // getting filtered enquiries from the database with counsellor id and enquiry filter
    // query by example is used and the filter based objects are retrieved
    public List<Enquiry>  getEnquiries(Enquiry enq, Integer counsellorId);

    // edit the specific enquiry based on the enquiry id
    public Enquiry getEnquiry(Integer enqId);

}
