package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CancelReservationCommand implements Command {
    private final Logger logger = LogManager.getLogger(CancelReservationCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ReservationsService reservationsService = ServiceFactory.getInstance().getReservationsService();

        try {
            int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));
            int reservationId = Integer.parseInt(request.getParameter(RequestParameter.RESERVATION_ID));
            String redirectCommand = request.getParameter(RequestParameter.REDIRECT_COMMAND);

            Reservation reservation = reservationsService.getByReservationId(reservationId);

            String command = "?command=" + redirectCommand +
                             "&user-id=" + reservation.getUserId();
            session.setAttribute(SessionAttribute.URL, "/controller" + command + "&page=" + page);
            session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);

            reservationsService.cancelReservation(reservationId);

            return new CommandResult("/controller" + command + "&page=" + page, RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to cancel reservation.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
