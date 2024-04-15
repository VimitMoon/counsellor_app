package vi.counsellor_app.service;

import org.springframework.stereotype.Service;
import vi.counsellor_app.entity.Counsellor;

@Service
public interface CounsellorService {


    // save counsellor ==> registration of counsellor

    public boolean saveCounsellor(Counsellor counsellor);


    // login // view data from dashboard
    public Counsellor getCounsellor(String email, String pwd);




}
