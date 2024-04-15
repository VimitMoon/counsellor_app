package vi.counsellor_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vi.counsellor_app.entity.Counsellor;
import vi.counsellor_app.repository.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {

    @Autowired
    CounsellorRepo counsellorRepo;
    @Override
    public Counsellor getCounsellor(String email, String pwd) {

        return counsellorRepo.findByEmailAndPwd(email,pwd);
    }

    @Override
    public boolean saveCounsellor(Counsellor counsellor) {

        Counsellor findByEmail = counsellorRepo.findByEmail(counsellor.getEmail());
        if (findByEmail != null) {
            return false;
        } else {
            Counsellor savedCounsellor = counsellorRepo.save(counsellor);
            return savedCounsellor.getCounsellorId() != null;
        }
    }
}
