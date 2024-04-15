package vi.counsellor_app.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Counsellor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer counsellorId;

    private String name;

    private  String email;

    private String pwd;

    private Long phno;

    @CreationTimestamp
    private LocalDate createdDate;

    @UpdateTimestamp
    private LocalDate updatedDate;

    @OneToMany(mappedBy = "counsellor",cascade = CascadeType.ALL)
    private List<Enquiry> enquiries;

    public Integer getCounsellorId() {
        return counsellorId;
    }

    public void setCounsellorId(Integer counsellorId) {
        this.counsellorId = counsellorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getPhno() {
        return phno;
    }

    public void setPhno(Long phno) {
        this.phno = phno;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(List<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }
}
