package by.bsuir.psp.utlik.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@AllArgsConstructor
public class JsonToUrlAuthenticationFilter implements Filter {

    private ObjectMapper objectMapper;

    private enum ExpectedParameter {
        USERNAME("username"), PASSWORD("password");

        private String name;

        ExpectedParameter(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private enum DefaultParameter {
        GRANT_TYPE("grant_type"), METHOD("_method");

        private String name;

        DefaultParameter(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        //do nothing
    }

    @Override
    public void destroy() {
        //do nothing
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        if (!(Objects.equals(((HttpServletRequest) request).getServletPath(), "/oauth/token")
                && Objects.equals(request.getContentType(), "application/json"))) {
            chain.doFilter(request, response);
            return;
        }

        @SuppressWarnings("unchecked")
        HashMap<String, String> jsonBody = objectMapper.readValue(request.getInputStream(), HashMap.class);
        HashMap<String, String[]> parameters = new HashMap<>();

        putExpectedParameters(parameters, jsonBody);
        putDefaultParameters(parameters, request);

        HttpServletRequest customRequest = new CustomServletRequestWrapper((HttpServletRequest) request, parameters);
        chain.doFilter(customRequest, response);
    }

    private void putExpectedParameters(final HashMap<String, String[]> parameters,
                                       final HashMap<String, String> jsonBody) {
        String[] val;
        for (ExpectedParameter expectedParameter : ExpectedParameter.values()) {
            val = new String[1];
            val[0] = jsonBody.get(expectedParameter.getName());
            parameters.put(expectedParameter.getName(), val);
        }
    }

    private void putDefaultParameters(final HashMap<String, String[]> parameters, final ServletRequest request) {
        String[] val = new String[1];
        val[0] = ((HttpServletRequest) request).getMethod();
        parameters.put(DefaultParameter.METHOD.getName(), val);

        val = new String[1];
        val[0] = AuthConstants.PASSWORD_GRANT_TYPE;
        parameters.put(DefaultParameter.GRANT_TYPE.getName(), val);
    }
}
