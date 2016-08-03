package by.lostFinder.controllers;

import by.lostFinder.services.SimpleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.MappedSuperclass;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MappedSuperclass
public abstract class GenericController<E, S extends SimpleService<E>> implements Filter {

    protected S service;

    protected GenericController(S service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    private Page<E> getAll(Pageable pageable){
        return service.getAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private E getById(@PathVariable("id") String id){
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    private E save(@RequestBody E entity){
        return service.save(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private void delete(@PathVariable("id") String id){
        service.delete(id);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
