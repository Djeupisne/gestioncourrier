package tg.inseed.gestioncourrier.gestioncourrier.utilisateurs;
import java.util.Arrays;

public enum RoleUtilisateur {
    ADMIN("ADMIN", "ROLE_ADMIN"),
    DG("DG", "ROLE_DG"),
    DIRECTION("DIRECTION", "ROLE_DIRECTION"),
    SECRETARIAT("SECRETARIAT", "ROLE_SECRETARIAT"),
    DIVISION("DIVISION", "ROLE_DIVISION"),
    SERVICES("SERVICES", "ROLE_SERVICES");

    private final String code;
    private final String authority;

    RoleUtilisateur(String code, String authority) {
        this.code = code;
        this.authority = authority;
    }

    public String getCode() {
        return code;
    }

    public String getAuthority() {
        return authority;
    }

    public static RoleUtilisateur fromString(String role) {
        for (RoleUtilisateur r : RoleUtilisateur.values()) {
            if (r.getCode().equalsIgnoreCase(role) || r.name().equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Rôle invalide: " + role + ". Rôles valides: " + Arrays.toString(values()));
    }

    public static boolean isValidRole(String role) {
        try {
            fromString(role);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}