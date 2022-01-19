package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditPersonalDataCommand implements Command {
    private final Logger logger = LogManager.getLogger(EditPersonalDataCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();

        String name = request.getParameter(RequestParameter.FIRST_NAME);
        String surname = request.getParameter(RequestParameter.SECOND_NAME);
        String phoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);

        int id = (int) session.getAttribute(SessionAttribute.USER_ID);

        session.setAttribute(SessionAttribute.USER_NAME, name);
        session.setAttribute(SessionAttribute.USER_SURNAME, surname);
        session.setAttribute(SessionAttribute.USER_PHONE_NUMBER, phoneNumber);
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-profile-page");

        try {
            userService.editPersonalData(id, name, surname, phoneNumber);

            return new CommandResult("/controller?command=goto-profile-page", RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to edit personal data.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
