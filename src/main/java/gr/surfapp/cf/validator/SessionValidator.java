package gr.surfapp.cf.validator;

import gr.surfapp.cf.dto.BaseSessionDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for validating {@link BaseSessionDTO} and its subclasses.
 * <p>
 * This class checks the length of various fields such as surfspots, surfboards, conditions, and opinions,
 * ensuring they fall within predefined limits.
 *
 * @param <T> a type that extends {@link BaseSessionDTO}
 */

public class SessionValidator<T> {

    private SessionValidator() {

    }



    public static <T extends BaseSessionDTO> Map<String, String> validate(T dto) {
        Map<String, String> errors = new HashMap<>();
        if (dto.getSurfspots().length() < 2 || dto.getSurfspots().length() > 32) {
            errors.put("surfspots", "Surfspots must be between 2 and 32 characters");
        }
        if (dto.getSurfboards().length() < 2 || dto.getSurfspots().length() > 32) {
            errors.put("surfboards", "Surfboards must be between 2 and  32 characters");
        }
        if (dto.getConditions().length() < 2 || dto.getSurfspots().length() > 128) {
            errors.put("conditions", "Conditions must be between 2 and 32 characters");
        }
        if (dto.getOpinions().length() < 2 || dto.getSurfspots().length() > 128) {
            errors.put("Opinions", "Opinions must be between 2 and  32 characters");
        }
        return errors;
    }
}