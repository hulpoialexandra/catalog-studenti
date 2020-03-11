package validator;

import entities.Student;

public class ValidatorStudent implements Validator<Student>{
    @Override
    public void validate(Student entity) throws ValidationException {
        if(entity.getGrupa()<0) throw new ValidationException("Grupa invalida\n");
    }
}
