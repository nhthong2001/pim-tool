package vn.elca.training.model.dto;
public class GroupDto {
    private Long id;
    private Long leaderId;
    private String leaderVisa;

    public GroupDto() {
    }

    public GroupDto(Long id, Long leaderId, String leaderVisa) {
        this.id = id;
        this.leaderId = leaderId;
        this.leaderVisa = leaderVisa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderVisa() {
        return leaderVisa;
    }

    public void setLeaderVisa(String leaderVisa) {
        this.leaderVisa = leaderVisa;
    }
}
