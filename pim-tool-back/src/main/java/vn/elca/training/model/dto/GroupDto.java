package vn.elca.training.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GroupDto {
    public Long id;
    public Long leader_id;
    public String leader_visa;

}
