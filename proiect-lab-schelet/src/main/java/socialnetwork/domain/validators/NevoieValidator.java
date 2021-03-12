package socialnetwork.domain.validators;

import socialnetwork.domain.Nevoie;

public class NevoieValidator implements Validator<Nevoie> {
    public NevoieValidator() {}

    @Override
    public void validate(Nevoie entity) throws ValidationException {
        String errors = "";

        if(entity.getId() == null || entity.getId() < 0){
            errors += "Id invalid!\n";
        }

        if(entity.getTitlu() == null || entity.getTitlu().equals("")){
            errors += "Titlu invalid!\n";
        }

        if(entity.getDescriere() == null || entity.getDescriere().equals("")){
            errors += "Descriere invalida!\n";
        }

        if(entity.getOmInNevoie() == null || entity.getOmInNevoie() < 0){
            errors += "Om in nevoie invalid!\n";
        }


        if(entity.getStatus() == null || entity.getTitlu().equals("")){
            errors += "Status invalid!\n";
        }

        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}
