package validator;

import entities.Nota;
import entities.Tema;
import structuraAn.StructuraAn;

public class ValidatorNota implements Validator<Nota> {
    private StructuraAn structuraAn=new StructuraAn();
    @Override
    public void validate(Nota entity) throws ValidationException {
        String errors="";
        int sc=structuraAn.getSaptamanaData(entity.getData());
        Tema t=(Tema)entity.getId().values().toArray()[0];
        if(sc<t.getStartWeek()) errors+="Nota trebuie acordata dupa de start week\n";
        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}
