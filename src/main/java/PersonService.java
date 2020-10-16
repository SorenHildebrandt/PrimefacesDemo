import dao.PersonDao;

import javax.inject.Inject;

public class PersonService {

    //injected i PersonAdminBean
    //Dao skal injectes i personservice

    @Inject
    private transient PersonDao personDao;


}
