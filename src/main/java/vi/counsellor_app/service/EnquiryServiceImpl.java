package vi.counsellor_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import vi.counsellor_app.dto.Dashboard;
import vi.counsellor_app.entity.Counsellor;
import vi.counsellor_app.entity.Enquiry;
import vi.counsellor_app.repository.CounsellorRepo;
import vi.counsellor_app.repository.EnquiryRepo;

import java.util.List;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private EnquiryRepo enquiryRepo;

    @Autowired
    private CounsellorRepo counsellorRepo;

    // get dashboard info ie total enq, enrolled enq, open enq and lost enqs
    @Override
    public Dashboard getDashboardInfo(Integer counsellorId) {

       Long totalEnq = enquiryRepo.getEnquiries(counsellorId);
       Long openCnt = enquiryRepo.getEnquiries(counsellorId,"new");
       Long lostCnt = enquiryRepo.getEnquiries(counsellorId,"lost");
       Long enrolledCnt = enquiryRepo.getEnquiries(counsellorId,"enrolled");

       Dashboard d = new Dashboard();
        d.setTotalEnqs(totalEnq);
        d.setLostEnqs(lostCnt);
        d.setEnrolledEnqs(enrolledCnt);
        d.setOpenEnqs(openCnt);

        return d;
    }

    // save enquiry and link it with the counsellor id who is adding the enquiry
    @Override
    public boolean addEnquiry(Enquiry enq, Integer counsellorId) {

        Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
        enq.setCounsellor(counsellor);  // association mapping

        Enquiry savedEnq = enquiryRepo.save(enq);
        return savedEnq.getEnqId() != null;
    }

    // get filtered enquiries
    @Override
    public List<Enquiry> getEnquiries(Enquiry enquiry, Integer counsellorId) {

        //Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();

        Counsellor counsellor = new Counsellor();
        counsellor.setCounsellorId(counsellorId);

        // new object created for adding the filtered values to the filtered query parameter object

        Enquiry searchCriteria = new Enquiry();
        searchCriteria.setCounsellor(counsellor);

        if(null!=enquiry.getCourse() && !"".equals(enquiry.getCourse()))
        {
            searchCriteria.setCourse(enquiry.getCourse());
        }
        if(null!=enquiry.getStatus() && !"".equals(enquiry.getStatus()))
        {
            searchCriteria.setStatus(enquiry.getStatus());
        }
        if(null!=enquiry.getMode() && !"".equals(enquiry.getMode()))
        {
            searchCriteria.setMode(enquiry.getMode());
        }


        // dynamic query creation => whatever data present in the object according to that query will be created
        Example<Enquiry> of  = Example.of(searchCriteria);
        return enquiryRepo.findAll(of);

    }

    // edit the enquiry
    @Override
    public Enquiry getEnquiry(Integer enqId) {
        return enquiryRepo.findById(enqId).orElseThrow();
    }
}
