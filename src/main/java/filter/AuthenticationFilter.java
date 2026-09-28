package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private List<String> excludedRequests;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedRequests = new ArrayList<>();

        excludedRequests.add("/login");
        excludedRequests.add("/register");
        excludedRequests.add(".js");
        excludedRequests.add(".css");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        boolean loggedIn = session != null &&
                session.getAttribute("userLogin") != null;

        String userRequest = request.getRequestURI();

        if (loggedIn || isValidRequest(userRequest)) {
            chain.doFilter(request, response);
        } else {
            System.out.println("Invalid Request");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    private boolean isValidRequest(String request) {
        for (String excludedRequest : excludedRequests) {
            if (request.endsWith(excludedRequest)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        // cleanup nếu cần
    }
}