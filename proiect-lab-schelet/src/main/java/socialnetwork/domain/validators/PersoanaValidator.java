package socialnetwork.domain.validators;

import socialnetwork.domain.Persoana;

public class PersoanaValidator implements Validator<Persoana> {
    public PersoanaValidator() {}

    @Override
    public void validate(Persoana entity) throws ValidationException {
        String errors = "";

        if(entity.getId() == null || entity.getId() < 0){
            errors += "Id invalid!\n";
        }

        if(entity.getNume() == null || entity.getNume().equals("")){
            errors += "Nume invalid!\n";
        }

        if(entity.getOras() == null){
            errors += "Oras invalid!\n";
        }

        if(entity.getPrenume() == null || entity.getPrenume().equals("")){
            errors += "Prenume invalid!\n";
        }

        if(entity.getParola() == null || entity.getParola().equals("")){
            errors += "Parola invalida!\n";
        }

        if(entity.getStrada() == null || entity.getStrada().equals("")){
            errors += "Strada invalida!\n";
        }

        if(entity.getNumarStrada() == null || entity.getNumarStrada().equals("")){
            errors += "Nr strada invalida!\n";
        }

        if(entity.getTelefon() == null || entity.getTelefon().equals("")){
            errors += "Telefon invalid!\n";
        }

        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}
