package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToUserReservationsPageCommand implements Command {
    Logger logger = LogManager.getLogger(GoToUserReservationsPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        ReservationsService reservationsService = ServiceFactory.getInstance().getReservationsService();
        try {
            List<ReservationDto> reservations = reservationsService.getReservationsDtoByUserId((int)session.getAttribute(SessionAttribute.USER_ID));
            request.setAttribute(RequestAttribute.RESERVATIONS, reservations);
            return new CommandResult(Page.USER_RESERVATIONS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get user reservations DTO", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
