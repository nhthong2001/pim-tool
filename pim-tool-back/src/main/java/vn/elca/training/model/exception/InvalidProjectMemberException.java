package vn.elca.training.model.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class InvalidProjectMemberException extends Exception {
    private List<String> memberVisa = new ArrayList<>();


    public InvalidProjectMemberException(List<String> listVisa) {
        super(String.format("List member is not valid: %s", StringUtils.join(listVisa, ", ")));
        this.memberVisa = listVisa;
    }

    public InvalidProjectMemberException(String message, Throwable cause) {
        super(message, cause);
    }
}