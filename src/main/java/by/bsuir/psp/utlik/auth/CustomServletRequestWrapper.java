package by.bsuir.psp.utlik.auth;

import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;

public class CustomServletRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String[]> params;

    /**
     * Constructs CustomServletRequestWrapper with map of parameters.
     *
     * @param request the request that was parsed into parameters, must not be {@literal null}.
     * @param params the map of parameters, must not be {@literal null}.
     * @throws IllegalArgumentException if {@code request} or {@code params} is {@literal null}
     */
    public CustomServletRequestWrapper(HttpServletRequest request, Map<String, String[]> params) {
        super(request);
        Assert.notNull(params);
        this.params = params;
    }

    @Override
    public String getParameter(final String name) {
        if (this.params.containsKey(name)) {
            return this.params.get(name)[0];
        }
        return "";
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return new Enumerator<>(params.keySet());
    }

    @Override
    public String[] getParameterValues(final String name) {
        return params.get(name);
    }
}
