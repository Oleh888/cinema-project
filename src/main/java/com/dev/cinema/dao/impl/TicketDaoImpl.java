package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.model.Ticket;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends GenericDaoImp<Ticket> implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);
    private final SessionFactory sessionFactory;

    public TicketDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        ticket = super.add(ticket);
        LOGGER.info(String.format("Ticket with id: %d was added to the DB.", ticket.getId()));
        return ticket;
    }

    @Override
    public Ticket getById(Long id) {
        return super.getById(id, Ticket.class);
    }
}
