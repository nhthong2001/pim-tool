package vn.elca.training.model;

public enum ProjectStatus {
    NEW {
        @Override
        public String toString() {
            return "New";
        }
    },
    PLA {
        @Override
        public String toString() {
            return "Planned";
        }
    },
    INP {
        @Override
        public String toString() {
            return "In progress";
        }
    },
    FIN {
        @Override
        public String toString() {
            return "Finished";
        }
    },

}
