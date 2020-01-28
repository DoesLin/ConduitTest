package conduit.test.service;

import conduit.test.dao.impl.DaoArticle;

import java.util.List;

public interface IWebService {
    public abstract Object getById(long id);
    public abstract Object create(Object object);
    public abstract Object update(Object object);
    public abstract void delete(long id);
    public abstract List<DaoArticle> getAlls();
}
