package validator;

import entities.Tema;

public class ValidatorTema implements Validator<Tema> {
    @Override
    public void validate(Tema entity) throws ValidationException {
        String errors="";
        if(entity.getId()<0)errors+="Id invlid\n";
        if(entity.getStartWeek()<1||entity.getStartWeek()>14) errors+="Start week invalid\n";
        if(entity.getDeadlineWeek()<1||entity.getDeadlineWeek()>14) errors+="Deadline week invalid\n";
        if(entity.getDeadlineWeek()<=entity.getStartWeek()) errors+="Deadline week invalid\n";
        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}
