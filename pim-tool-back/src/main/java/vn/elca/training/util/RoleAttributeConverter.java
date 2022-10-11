package vn.elca.training.util;

import vn.elca.training.model.enumType.RoleEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class RoleAttributeConverter implements AttributeConverter<RoleEnum, String> {
    @Override
    public String convertToDatabaseColumn(RoleEnum attribute) {
        if (attribute == null)
            return null;

        switch (attribute) {
            case GROUP_LEADER:
                return "Group leader";

            case PROJECT_LEADER:
                return "Project leader";

            case DEVELOPER:
                return "Developer";

            case QUALITY_AGENT:
                return "Quality Agent";

            default:
                throw new IllegalArgumentException(attribute + " not supported.");
        }
    }

    @Override
    public RoleEnum convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case "Group leader":
                return RoleEnum.GROUP_LEADER;

            case "Project leader":
                return RoleEnum.PROJECT_LEADER;

            case "Developer":
                return RoleEnum.DEVELOPER;

            case "Quality Agent":
                return RoleEnum.QUALITY_AGENT;

            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
