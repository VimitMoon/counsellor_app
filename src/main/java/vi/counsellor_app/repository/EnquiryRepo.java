package vi.counsellor_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vi.counsellor_app.entity.Enquiry;

import java.util.List;

public interface EnquiryRepo extends JpaRepository<Enquiry,Integer> {

    @Query(
            value="select count(*) from Enquiry where counsellor_id =:id",
            nativeQuery = true
    )
    public Long getEnquiries(Integer id);

    @Query(
            value="select count(*) from Enquiry where counsellor_id =:counsellorId and status=:status",
            nativeQuery = true
    )
    public Long getEnquiries(Integer counsellorId, String status);

}
