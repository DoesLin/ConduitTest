package conduit.test.service;

import conduit.test.repository.dao.DaoArticle;

import java.util.List;

public interface IWebService {
    public abstract Object getById(long id) throws Exception;

    public abstract Object create(Object object) throws Exception;

    public abstract Object update(Object object) throws Exception;

    public abstract void delete(long id) throws Exception;

    public abstract List<? extends Object> getAlls() throws Exception;
}
