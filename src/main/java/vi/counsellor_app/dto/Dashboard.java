package vi.counsellor_app.dto;

public class Dashboard {

    private Long totalEnqs;

    private Long openEnqs;

    private Long enrolledEnqs;

    private Long lostEnqs;

    public Long getTotalEnqs() {
        return totalEnqs;
    }

    public void setTotalEnqs(Long totalEnqs) {
        this.totalEnqs = totalEnqs;
    }

    public Long getOpenEnqs() {
        return openEnqs;
    }

    public void setOpenEnqs(Long openEnqs) {
        this.openEnqs = openEnqs;
    }

    public Long getEnrolledEnqs() {
        return enrolledEnqs;
    }

    public void setEnrolledEnqs(Long enrolledEnqs) {
        this.enrolledEnqs = enrolledEnqs;
    }

    public Long getLostEnqs() {
        return lostEnqs;
    }

    public void setLostEnqs(Long lostEnqs) {
        this.lostEnqs = lostEnqs;
    }
}
