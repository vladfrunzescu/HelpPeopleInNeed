package socialnetwork.repository.file;

import socialnetwork.domain.Nevoie;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.Constants;
import socialnetwork.utils.My_Enum;

import java.time.LocalDateTime;
import java.util.List;

public class NevoieFile extends AbstractFileRepository<Long, Nevoie> {
    public NevoieFile(String fileName, Validator<Nevoie> validator) {
        super(fileName, validator);
    }

    @Override
    public Nevoie extractEntity(List<String> attributes) {
        Long ID = Long.parseLong(attributes.get(0));
        String first_string = attributes.get(1);
        String second_string = attributes.get(2);
        String string1 = attributes.get(6);
        Long first_Long = Long.parseLong(attributes.get(4));
        Long second_Long;

        if(!attributes.get(5).equals("")) {
            second_Long = Long.parseLong(attributes.get(5));
        }else{
            second_Long = null;
        }

        LocalDateTime first_date = LocalDateTime.parse(attributes.get(3), Constants.DATE_TIME_FORMATTER);


        Nevoie entity = new Nevoie(ID, first_string, second_string, first_date, first_Long, second_Long, string1);
        return entity;
    }

    @Override
    protected String createEntityAsString(Nevoie entity) {

        if(entity.getOmSalvator() !=null ) {

            return entity.getId() + ";" + entity.getTitlu() + ";" + entity.getDescriere() + ";" + entity.getDeadline().format(Constants.DATE_TIME_FORMATTER) + ";" + entity.getOmInNevoie() + ";" + entity.getOmSalvator() + ";" + entity.getStatus();
        }else
        {
            return entity.getId() + ";" + entity.getTitlu() + ";" + entity.getDescriere() + ";" + entity.getDeadline().format(Constants.DATE_TIME_FORMATTER) + ";" + entity.getOmInNevoie() + ";" + "" + ";" + entity.getStatus();

        }
    }
}
