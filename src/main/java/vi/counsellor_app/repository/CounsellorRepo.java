package vi.counsellor_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vi.counsellor_app.entity.Counsellor;

import java.util.List;

public interface CounsellorRepo extends JpaRepository<Counsellor,Integer> {

    public Counsellor findByEmail(String email);

    public  Counsellor findByEmailAndPwd(String email, String pwd);

}
