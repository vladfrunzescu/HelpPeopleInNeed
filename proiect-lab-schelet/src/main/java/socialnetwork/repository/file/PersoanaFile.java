package socialnetwork.repository.file;

import socialnetwork.domain.Persoana;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.Constants;
import socialnetwork.utils.My_Enum;
import socialnetwork.utils.Type;

import java.time.LocalDateTime;
import java.util.List;

public class PersoanaFile extends AbstractFileRepository<Long, Persoana> {
    public PersoanaFile(String fileName, Validator<Persoana> validator) {
        super(fileName, validator);
    }

    @Override
    public Persoana extractEntity(List<String> attributes) {
        Long ID = Long.parseLong(attributes.get(0));
        Type first_myenum = Type.valueOf(attributes.get(5));
        String nume = attributes.get(1);
        String prenume = attributes.get(2);
        String username = attributes.get(3);
        String parola = attributes.get(4);
        String strada = attributes.get(6);
        String numarStrada = attributes.get(7);
        String telefon = attributes.get(8);

        Persoana entity = new Persoana(ID, nume, prenume, username, parola, first_myenum, strada, telefon, numarStrada);
        return entity;
    }

    @Override
    protected String createEntityAsString(Persoana entity) {
        return entity.getId()+";"+entity.getNume()+";"+entity.getPrenume()+";"+entity.getUsername()+";"+entity.getParola()+";"+entity.getOras().toString()+";"+entity.getStrada()+";"+entity.getNumarStrada()+";"+entity.getTelefon();
    }
}
