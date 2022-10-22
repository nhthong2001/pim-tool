package vn.elca.training.util;

import vn.elca.training.model.ProjectStatus;

public class Converter {
    public static ProjectStatus status(String status) {
        switch (status) {
            case "New": return ProjectStatus.NEW;
            case "Planned": return ProjectStatus.PLA;
            case "In progress": return ProjectStatus.INP;
            case "Finished": return ProjectStatus.FIN;
            default: return ProjectStatus.NEW;
        }
    }
}
