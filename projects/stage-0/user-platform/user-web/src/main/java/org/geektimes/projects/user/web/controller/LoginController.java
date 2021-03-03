package org.geektimes.projects.user.web.controller;

import org.apache.commons.lang.StringUtils;
import org.geektimes.infrastructure.SpiContainer;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

/**
 *
 *
 * @author YuI
 * @date 2021/3/4 0:05 
 * @since v1-SNAPSHOT
 **/
@Path("")
public class LoginController implements PageController {

    @GET
    @Path("/login")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");

        if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(password)) {
            UserService service = SpiContainer.getDefaultObject(UserService.class);

            User user = new User();
            user.setId(LocalDateTime.now().getLong(ChronoField.NANO_OF_SECOND));
            user.setName("YuI");
            user.setEmail(email);
            user.setPassword(password);
            user.setPhoneNumber("111");

            if (service.register(user)) {
                return "register.jsp";
            }
        }

        return "login-form.jsp";
    }
}
